package com.lalthanpuiachhangte.mizoramdisastermanagement.Entity;

import android.os.Parcel;
import android.os.Parcelable;

public class GlobalNotification implements Parcelable {

    int serialNumber;
    String name;
    String designation;
    String subject;
    String body;
    String reportOn;
    String extra;

    public GlobalNotification() {
    }

    public GlobalNotification(int serialNumber, String name, String designation, String subject, String body, String reportOn, String extra) {
        this.serialNumber = serialNumber;
        this.name = name;
        this.designation = designation;
        this.subject = subject;
        this.body = body;
        this.reportOn = reportOn;
        this.extra = extra;
    }

    protected GlobalNotification(Parcel in) {
        serialNumber = in.readInt();
        name = in.readString();
        designation = in.readString();
        subject = in.readString();
        body = in.readString();
        reportOn = in.readString();
        extra = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(serialNumber);
        dest.writeString(name);
        dest.writeString(designation);
        dest.writeString(subject);
        dest.writeString(body);
        dest.writeString(reportOn);
        dest.writeString(extra);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<GlobalNotification> CREATOR = new Creator<GlobalNotification>() {
        @Override
        public GlobalNotification createFromParcel(Parcel in) {
            return new GlobalNotification(in);
        }

        @Override
        public GlobalNotification[] newArray(int size) {
            return new GlobalNotification[size];
        }
    };

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getReportOn() {
        return reportOn;
    }

    public void setReportOn(String reportOn) {
        this.reportOn = reportOn;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }


}


