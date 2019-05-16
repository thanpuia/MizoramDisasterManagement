package com.lalthanpuiachhangte.mizoramdisastermanagement.Entity;

public class Relief {

    int serial_number;
    String details;
    String district;
    String landmarks;
    String lat;
    String lng;
    String locality;
    String material;
    String material_id;
    String username;
    String phone;
    String quantity;
    String requestOn;
    String status;
    String user_id;

    String officerName;
    String officerId;
    String officerContact;
    String zoneId;

    public Relief() {
    }

    public Relief(int serial_number, String details, String district, String landmarks, String lat, String lng, String locality, String material, String material_id, String username, String phone, String quantity, String requestOn, String status, String user_id, String officerName, String officerId, String officerContact, String zoneId) {
        this.serial_number = serial_number;
        this.details = details;
        this.district = district;
        this.landmarks = landmarks;
        this.lat = lat;
        this.lng = lng;
        this.locality = locality;
        this.material = material;
        this.material_id = material_id;
        this.username = username;
        this.phone = phone;
        this.quantity = quantity;
        this.requestOn = requestOn;
        this.status = status;
        this.user_id = user_id;
        this.officerName = officerName;
        this.officerId = officerId;
        this.officerContact = officerContact;
        this.zoneId = zoneId;
    }

    public int getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(int serial_number) {
        this.serial_number = serial_number;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getLandmarks() {
        return landmarks;
    }

    public void setLandmarks(String landmarks) {
        this.landmarks = landmarks;
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

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(String material_id) {
        this.material_id = material_id;
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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getRequestOn() {
        return requestOn;
    }

    public void setRequestOn(String requestOn) {
        this.requestOn = requestOn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getOfficerName() {
        return officerName;
    }

    public void setOfficerName(String officerName) {
        this.officerName = officerName;
    }

    public String getOfficerId() {
        return officerId;
    }

    public void setOfficerId(String officerId) {
        this.officerId = officerId;
    }

    public String getOfficerContact() {
        return officerContact;
    }

    public void setOfficerContact(String officerContact) {
        this.officerContact = officerContact;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }
}
