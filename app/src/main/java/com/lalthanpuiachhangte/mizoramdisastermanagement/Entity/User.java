package com.lalthanpuiachhangte.mizoramdisastermanagement.Entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class User implements Parcelable {

    int    serialNumber;
    String altContactName;
    String altContactNo;
    String createdAt;
    String district;
    String email;
    String emergencyContactName;
    String emergencyContactNo;
    String lastLogin;
    String lat;
    String lng;
    String locality;
    String username;
    String phoneNo;
    String userRole;
    String updateAt;
    String volunteer;
    String password;
    String designation;

    public User() {
    }

    public User(int serialNumber, String altContactName, String altContactNo, String createdAt, String district, String email, String emergencyContactName, String emergencyContactNo, String lastLogin, String lat, String lng, String locality, String username, String phoneNo, String userRole, String updateAt, String volunteer, String password, String designation) {
        this.serialNumber = serialNumber;
        this.altContactName = altContactName;
        this.altContactNo = altContactNo;
        this.createdAt = createdAt;
        this.district = district;
        this.email = email;
        this.emergencyContactName = emergencyContactName;
        this.emergencyContactNo = emergencyContactNo;
        this.lastLogin = lastLogin;
        this.lat = lat;
        this.lng = lng;
        this.locality = locality;
        this.username = username;
        this.phoneNo = phoneNo;
        this.userRole = userRole;
        this.updateAt = updateAt;
        this.volunteer = volunteer;
        this.password = password;
        this.designation = designation;
    }

    protected User(Parcel in) {
        serialNumber = in.readInt();
        altContactName = in.readString();
        altContactNo = in.readString();
        createdAt = in.readString();
        district = in.readString();
        email = in.readString();
        emergencyContactName = in.readString();
        emergencyContactNo = in.readString();
        lastLogin = in.readString();
        lat = in.readString();
        lng = in.readString();
        locality = in.readString();
        username = in.readString();
        phoneNo = in.readString();
        userRole = in.readString();
        updateAt = in.readString();
        volunteer = in.readString();
        password = in.readString();
        designation = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getAltContactName() {
        return altContactName;
    }

    public void setAltContactName(String altContactName) {
        this.altContactName = altContactName;
    }

    public String getAltContactNo() {
        return altContactNo;
    }

    public void setAltContactNo(String altContactNo) {
        this.altContactNo = altContactNo;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmergencyContactName() {
        return emergencyContactName;
    }

    public void setEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }

    public String getEmergencyContactNo() {
        return emergencyContactNo;
    }

    public void setEmergencyContactNo(String emergencyContactNo) {
        this.emergencyContactNo = emergencyContactNo;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
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

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public String getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(String volunteer) {
        this.volunteer = volunteer;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(serialNumber);
        dest.writeString(altContactName);
        dest.writeString(altContactNo);
        dest.writeString(createdAt);
        dest.writeString(district);
        dest.writeString(email);
        dest.writeString(emergencyContactName);
        dest.writeString(emergencyContactNo);
        dest.writeString(lastLogin);
        dest.writeString(lat);
        dest.writeString(lng);
        dest.writeString(locality);
        dest.writeString(username);
        dest.writeString(phoneNo);
        dest.writeString(userRole);
        dest.writeString(updateAt);
        dest.writeString(volunteer);
        dest.writeString(password);
        dest.writeString(designation);
    }
}
