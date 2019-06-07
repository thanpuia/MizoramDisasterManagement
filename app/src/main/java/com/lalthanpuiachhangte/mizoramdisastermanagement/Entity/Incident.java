package com.lalthanpuiachhangte.mizoramdisastermanagement.Entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Incident implements Parcelable {

    int serialNumber;
    String disasterType;
    String locality;
    String landmarks;
    String disastersDetails;
    String details;
    String disasterTypeId;
    String district;
    String inLocation;
    String lat;
    String lng;
    String location;
    String username;
    String phone;
    String reportOn;
    String status;
    String userId;

    String officerContact;
    String officerId;
    String officerName;
    String zoneId;

    public Incident() {
    }

    public Incident(int serialNumber, String disasterType, String locality, String landmarks, String disasterDetails, String details, String disasterTypeId, String district, String inLocation, String lat, String lng, String location, String username, String phone, String reportOn, String status, String userId, String officerContact, String officerId, String officerName, String zoneId) {
        this.serialNumber = serialNumber;
        this.disasterType = disasterType;
        this.locality = locality;
        this.landmarks = landmarks;
        this.disastersDetails = disasterDetails;
        this.details = details;
        this.disasterTypeId = disasterTypeId;
        this.district = district;
        this.inLocation = inLocation;
        this.lat = lat;
        this.lng = lng;
        this.location = location;
        this.username = username;
        this.phone = phone;
        this.reportOn = reportOn;
        this.status = status;
        this.userId = userId;
        this.officerContact = officerContact;
        this.officerId = officerId;
        this.officerName = officerName;
        this.zoneId = zoneId;
    }

    protected Incident(Parcel in) {
        serialNumber = in.readInt();
        disasterType = in.readString();
        locality = in.readString();
        landmarks = in.readString();
        disastersDetails = in.readString();
        details = in.readString();
        disasterTypeId = in.readString();
        district = in.readString();
        inLocation = in.readString();
        lat = in.readString();
        lng = in.readString();
        location = in.readString();
        username = in.readString();
        phone = in.readString();
        reportOn = in.readString();
        status = in.readString();
        userId = in.readString();
        officerContact = in.readString();
        officerId = in.readString();
        officerName = in.readString();
        zoneId = in.readString();
    }

    public static final Creator<Incident> CREATOR = new Creator<Incident>() {
        @Override
        public Incident createFromParcel(Parcel in) {
            return new Incident(in);
        }

        @Override
        public Incident[] newArray(int size) {
            return new Incident[size];
        }
    };

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getDisasterType() {
        return disasterType;
    }

    public void setDisasterType(String disasterType) {
        this.disasterType = disasterType;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getLandmarks() {
        return landmarks;
    }

    public void setLandmarks(String landmarks) {
        this.landmarks = landmarks;
    }

    public String getDisastersDetails() {
        return disastersDetails;
    }

    public void setDisastersDetails(String disasterDetails) {
        this.disastersDetails = disasterDetails;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDisasterTypeId() {
        return disasterTypeId;
    }

    public void setDisasterTypeId(String disasterTypeId) {
        this.disasterTypeId = disasterTypeId;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getInLocation() {
        return inLocation;
    }

    public void setInLocation(String inLocation) {
        this.inLocation = inLocation;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReportOn() {
        return reportOn;
    }

    public void setReportOn(String reportOn) {
        this.reportOn = reportOn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOfficerContact() {
        return officerContact;
    }

    public void setOfficerContact(String officerContact) {
        this.officerContact = officerContact;
    }

    public String getOfficerId() {
        return officerId;
    }

    public void setOfficerId(String officerId) {
        this.officerId = officerId;
    }

    public String getOfficerName() {
        return officerName;
    }

    public void setOfficerName(String officerName) {
        this.officerName = officerName;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(serialNumber);
        dest.writeString(disasterType);
        dest.writeString(locality);
        dest.writeString(landmarks);
        dest.writeString(disastersDetails);
        dest.writeString(details);
        dest.writeString(disasterTypeId);
        dest.writeString(district);
        dest.writeString(inLocation);
        dest.writeString(lat);
        dest.writeString(lng);
        dest.writeString(location);
        dest.writeString(username);
        dest.writeString(phone);
        dest.writeString(reportOn);
        dest.writeString(status);
        dest.writeString(userId);
        dest.writeString(officerContact);
        dest.writeString(officerId);
        dest.writeString(officerName);
        dest.writeString(zoneId);
    }
}
