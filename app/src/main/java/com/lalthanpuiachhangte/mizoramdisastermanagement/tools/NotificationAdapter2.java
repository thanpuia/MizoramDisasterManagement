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
import com.lalthanpuiachhangte.mizoramdisastermanagement.Entity.Relief;
import com.lalthanpuiachhangte.mizoramdisastermanagement.MainActivity;
import com.lalthanpuiachhangte.mizoramdisastermanagement.R;

import java.math.RoundingMode;
import java.util.ArrayList;

public class NotificationAdapter2  extends RecyclerView.Adapter<NotificationAdapter2.MyHolder>  {
    public static ArrayList<Relief> allRelief;
    public static String ROLE;
    public final static String OFFICER ="OFFICER";

    final String TAG = "TAG" ;

    public NotificationAdapter2() {
    }

    public NotificationAdapter2(ArrayList<Relief> mAllRelief, String mROLE) {
        allRelief = mAllRelief;
        ROLE = mROLE;
    }

    @NonNull
    @Override
    public NotificationAdapter2.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.notification2_model,viewGroup,false);
        MyHolder myHolder = new MyHolder(v);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter2.MyHolder myHolder, int i) {

        myHolder.materialReq.setText("Material: "+ allRelief.get(i).getMaterial());
        myHolder.quantity.setText("Quantity: " +allRelief.get(i).getQuantity());
        myHolder.date.setText(""+ allRelief.get(i).getRequestOn());
        myHolder.details.setText("Details: "+ allRelief.get(i).getDetails());
        myHolder.address.setText("Address: "+ allRelief.get(i).getLocality());
        myHolder.officer.setText("Zone Officer: "+ allRelief.get(i).getOfficerName());


        Log.i("TAG", "Serial: "+ allRelief.get(i).getserialNumber());
        String statusss="";
        try{
            statusss = allRelief.get(i).getStatus();
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
        }else              myHolder.status.setTextColor(Color.rgb(204, 41, 0));

        myHolder.status.setText("Status: "+ allRelief.get(i).getStatus());

        Log.d(TAG,"Reading all notifications");


    }

    @Override
    public int getItemCount() {
        return allRelief.size();
    }

    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView materialReq, quantity,date, details, address, status, officer;
        //DATABASE KA HMAN NA HO HI CU DELETE HRILH LONAG
        //TUTTORIAL A TAN KA HMAN LEH NAN
        //DatabaseHelper db;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            //   this. db = new DatabaseHelper(itemView.getContext());
            this.materialReq = itemView.findViewById(R.id.materialRequired);
            this.quantity = itemView.findViewById(R.id.quantity);
            this.date = itemView.findViewById(R.id.date);
            this.details = itemView.findViewById(R.id.details);
            this.address = itemView.findViewById(R.id.address);
            this.status= itemView.findViewById(R.id.status);
            this.officer = itemView.findViewById(R.id.officer);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getLayoutPosition();

                    if(ROLE.equals(OFFICER))
                        dialog(v,position);
                }
            });
        }

        @Override
        public void onClick(View v) {
//
//            int position = getLayoutPosition();
//
//            if(ROLE.equals(OFFICER))
//                dialog(v,position);
        }

        public void dialog(final View view, final int position){

            android.app.AlertDialog.Builder builder;

            builder = new android.app.AlertDialog.Builder(view.getContext());


            //get the incident details
            // name : address : phone
            String senderDetails = "Sender Name: "+ allRelief.get(position).getUsername()+"\n"
                    + "Sender Address: "+ allRelief.get(position).getLocality()+ "\n"
                    + "Sender Phone: "+ allRelief.get(position).getPhone()+ "\n"
                    + "Disaster Type: ";
            builder.setMessage(senderDetails);
            builder.setPositiveButton("seen", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //Toast.makeText(v.getContext(),"Your Order is sent, wait for confirmation",Toast.LENGTH_LONG).show();
                    //set changes to the server status
                    String url = MainActivity.ipAddress+ "/statusChange/relief/"+allRelief.get(position).getserialNumber() + "/seen"  ;

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
                    String url = MainActivity.ipAddress+ "/statusChange/relief/"+allRelief.get(position).getserialNumber() + "/notSeen" ;
                    Log.i("TAG","Serial Number: "+allRelief.get(position).getUsername());
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
                    String url = MainActivity.ipAddress+ "/statusChange/relief/"+allRelief.get(position).getserialNumber()+ "/resolved";

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
    }
}
