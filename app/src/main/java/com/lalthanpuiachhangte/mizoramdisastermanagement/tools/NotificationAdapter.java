package com.lalthanpuiachhangte.mizoramdisastermanagement.tools;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lalthanpuiachhangte.mizoramdisastermanagement.Entity.Incident;
import com.lalthanpuiachhangte.mizoramdisastermanagement.R;

import java.util.ArrayList;
import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyHolder> {
  public static ArrayList<Incident> allIncident;

    final String TAG = "TAG" ;
    public NotificationAdapter(){

    }
    public NotificationAdapter(ArrayList<Incident> Incidentdd){

        allIncident = Incidentdd;
//
    }
    public static void addNotify(ArrayList<Incident> Incidentdd){

        allIncident = Incidentdd;
//
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
        myHolder.status.setText("Status"+ allIncident.get(i).getStatus());
        myHolder.officer.setText("Zone Officer: "+ allIncident.get(i).getOfficerName());

        Log.d(TAG,"Reading all notifications");

    }

    @Override
    public int getItemCount() {
        return allIncident.size();
    }

    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView type, date, details, address, status, officer;
        DatabaseHelper db;
        List<Incident> allIncident;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            this. db = new DatabaseHelper(itemView.getContext());
            this.type = itemView.findViewById(R.id.type);
            this.date = itemView.findViewById(R.id.date);
            this.details = itemView.findViewById(R.id.details);
            this.address = itemView.findViewById(R.id.address);
            this.status= itemView.findViewById(R.id.status);
            this.officer = itemView.findViewById(R.id.officer);

            this.allIncident = null;



        }

        @Override
        public void onClick(View v) {

        }
    }
}
