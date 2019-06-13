package com.lalthanpuiachhangte.mizoramdisastermanagement.tools;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lalthanpuiachhangte.mizoramdisastermanagement.Entity.GlobalNotification;
import com.lalthanpuiachhangte.mizoramdisastermanagement.R;

import java.util.ArrayList;

public class NotificationGlobalAdapter extends RecyclerView.Adapter<NotificationGlobalAdapter.MyHolder> {


    public static ArrayList<GlobalNotification> globalNotificationArrayList;
    public NotificationGlobalAdapter() {
    }
    public NotificationGlobalAdapter(ArrayList<GlobalNotification> mGlobalNotificationArrayList) {
        globalNotificationArrayList = mGlobalNotificationArrayList;
    }

    @NonNull
    @Override
    public NotificationGlobalAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.notification_global_model, viewGroup, false);
        MyHolder holder = new MyHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationGlobalAdapter.MyHolder myHolder, int i) {
        myHolder.name.setText("Name: "+ globalNotificationArrayList.get(i).getName());
        myHolder.designation.setText("Designation: " +globalNotificationArrayList.get(i).getDesignation());
        myHolder.subject.setText("Subject: "+ globalNotificationArrayList.get(i).getSubject());
        myHolder.body.setText("Body: "+ globalNotificationArrayList.get(i).getBody());
        myHolder.currentTime.setText( ""+ globalNotificationArrayList.get(i).getReportOn());
        myHolder.extra.setText("Extra: "+ globalNotificationArrayList.get(i).getExtra());
    }

    @Override
    public int getItemCount() {
        return globalNotificationArrayList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name, designation, subject, body, currentTime, extra;


        public MyHolder(@NonNull View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.senderG);
            this.designation = itemView.findViewById(R.id.designationG);
            this.subject = itemView.findViewById(R.id.subjectG);
            this.body = itemView.findViewById(R.id.bodyG);
            this.currentTime = itemView.findViewById(R.id.dateG);
            this.extra = itemView.findViewById(R.id.extraG);
        }

        @Override
        public void onClick(View v) {

        }
    }
}


//
//    @NonNull
//    @Override
//    public NotificationAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.notification_global_model,viewGroup,false);
//        GlobalNotification.MyHolder holder = new GlobalNotification.MyHolder(v);
//        return holder;    }
//
//
//
//    @Override
//    public void onBindViewHolder(@NonNull NotificationAdapter.MyHolder myHolder, int i) {
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return 0;
//    }
//
//    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
//
//        TextView name, designation, subject, body, currentTime, extra;
//
//
//        public MyHolder(@NonNull View itemView) {
//            super(itemView);
//            this.name = itemView.findViewById(R.id.senderG);
//            this.designation = itemView.findViewById(R.id.designationG);
//            this.subject = itemView.findViewById(R.id.subjectG);
//            this.body = itemView.findViewById(R.id.bodyG);
//            this.currentTime= itemView.findViewById(R.id.dateG);
//            this.extra = itemView.findViewById(R.id.extraG);
//        }
//
//        @Override
//        public void onClick(View v) {
//
//        }
//    }

