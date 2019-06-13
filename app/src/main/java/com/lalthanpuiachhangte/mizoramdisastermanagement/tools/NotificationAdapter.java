package com.lalthanpuiachhangte.mizoramdisastermanagement.tools;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.lalthanpuiachhangte.mizoramdisastermanagement.Entity.Incident;
import com.lalthanpuiachhangte.mizoramdisastermanagement.MainActivity;
import com.lalthanpuiachhangte.mizoramdisastermanagement.R;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyHolder> {

    public static ArrayList<Incident> allIncident;
    public static String ROLE;
    public final static String OFFICER ="OFFICER";

    final String TAG = "TAG" ;
    public NotificationAdapter(){

    }
    public NotificationAdapter(ArrayList<Incident> Incidentdd, String mROLE){

        allIncident = Incidentdd;
        ROLE = mROLE;

    }

    @NonNull
    @Override
    public NotificationAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.notification_model,viewGroup,false);
        MyHolder holder = new MyHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.MyHolder myHolder, int i) {

        myHolder.type.setText("Type: "+ allIncident.get(i).getDisasterType());
        myHolder.date.setText(""+ allIncident.get(i).getReportOn());
        myHolder.details.setText("Details: "+ allIncident.get(i).getDisastersDetails());
        myHolder.address.setText("Address: "+ allIncident.get(i).getLocality());
        myHolder.officer.setText("Zone Officer: "+ allIncident.get(i).getOfficerName());

        String statusss="";
        try{
             statusss = allIncident.get(i).getStatus();
             if(statusss.equals(null))
                 statusss = "notSeen";

        }catch (Exception e){
            statusss = "not Seen";
        }

        if(statusss.equals("seen")){
            myHolder.status.setTextColor(Color.rgb(255, 153, 0));
        }else if(statusss.equals("notSeen")){
            myHolder.status.setTextColor(Color.rgb(204, 41, 0));
        }else if(  statusss.equals("resolved")){
            myHolder.status.setTextColor(Color.rgb(51, 153, 51));
        }else myHolder.status.setTextColor(Color.rgb(204, 41, 0));

        myHolder.status.setText("Status: "+ allIncident.get(i).getStatus());

        Log.d(TAG,"Reading all notifications");

    }

    @Override
    public int getItemCount() {
        return allIncident.size();
    }

    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView type, date, details, address, status, officer;
        //DATABASE KA HMAN NA HO HI CU DELETE HRILH LONAG
        //TUTTORIAL A TAN KA HMAN LEH NAN
        //DatabaseHelper db;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
         //   this. db = new DatabaseHelper(itemView.getContext());
            this.type = itemView.findViewById(R.id.type);
            this.date = itemView.findViewById(R.id.date);
            this.details = itemView.findViewById(R.id.details);
            this.address = itemView.findViewById(R.id.address);
            this.status= itemView.findViewById(R.id.status);
            this.officer = itemView.findViewById(R.id.officer);

           // this.allIncident = null;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getLayoutPosition();
                    // Toast.makeText(v.getContext(), "position = " + getLayoutPosition(), Toast.LENGTH_SHORT).show();

                    // WE CREATE THIS SO THAT THE CITIZEN WILL NOT BE ABLE TO CLICK ON THE NOTIFICATION
                    if(ROLE.equals(OFFICER))
                        dialog(v,position);


                }
            });


        }

        public void dialog(final View view, final int position){

            android.app.AlertDialog.Builder builder;

            builder = new android.app.AlertDialog.Builder(view.getContext());


            //get the incident details
            // name : address : phone
            String senderDetails = "Sender Name: "+ allIncident.get(position).getUsername()+"\n"
                    + "Sender Address: "+ allIncident.get(position).getLocality()+ "\n"
                    + "Sender Phone: "+ allIncident.get(position).getPhone()+ "\n"
                    + "Disaster Type: "+ allIncident.get(position).getDisasterType();
            builder.setMessage(senderDetails);
            builder.setPositiveButton("seen", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //Toast.makeText(v.getContext(),"Your Order is sent, wait for confirmation",Toast.LENGTH_LONG).show();
                    //set changes to the server status
                   String url = MainActivity.ipAddress+ "/statusChange/incident/"+allIncident.get(position).getSerialNumber() + "/seen"  ;

                    Ion.with(view.getContext())
                            .load(url)
                            .as(new TypeToken<ArrayList<Incident>>(){})
                            .setCallback(new FutureCallback<ArrayList<Incident>>() {
                                @Override
                                public void onCompleted(Exception e, ArrayList<Incident> result) {
                                    //Intent intent = new Intent(view.getContext(),NotificationActivity.class);
                                    //notifyDataSetChanged();
                                }
                            });
                }

            });
            builder.setNegativeButton("not seen", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    String url = MainActivity.ipAddress+ "/statusChange/incident/"+allIncident.get(position).getSerialNumber() + "/notSeen" ;

                    Ion.with(view.getContext())
                            .load(url)
                            .as(new TypeToken<ArrayList<Incident>>(){})
                            .setCallback(new FutureCallback<ArrayList<Incident>>() {
                                @Override
                                public void onCompleted(Exception e, ArrayList<Incident> result) {
                                    //Intent intent = new Intent(view.getContext(),NotificationActivity.class);
                                    //notifyDataSetChanged();
                                }
                            });
                }

            });

            builder.setNeutralButton("resolved", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String url = MainActivity.ipAddress+ "/statusChange/incident/"+allIncident.get(position).getSerialNumber()+ "/resolved";

                    Ion.with(view.getContext())
                            .load(url)
                            .as(new TypeToken<ArrayList<Incident>>(){})
                            .setCallback(new FutureCallback<ArrayList<Incident>>() {
                                @Override
                                public void onCompleted(Exception e, ArrayList<Incident> result) {
                                    //Intent intent = new Intent(view.getContext(),NotificationActivity.class);
                                    //notifyDataSetChanged();
                                }
                            });
                }
            });




            AlertDialog dialog = builder.create();
            dialog.show();
        }

        @Override
        public void onClick(View v) { }
    }
}
