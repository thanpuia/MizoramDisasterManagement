package com.lalthanpuiachhangte.mizoramdisastermanagement.Entity;

public class Officer {

    String zoneId;
    String officerContact;
    String officerDesignation;
    String officerEmail;
    String officerZone;
    String officerName;
    String officerId;
    String officerLocality;


    public Officer() {
    }

    public Officer(String zoneId, String officerContact, String officerDesignation, String officerEmail, String officerZone, String officerName, String officerId, String officerLocality) {
        this.zoneId = zoneId;
        this.officerContact = officerContact;
        this.officerDesignation = officerDesignation;
        this.officerEmail = officerEmail;
        this.officerZone = officerZone;
        this.officerName = officerName;
        this.officerId = officerId;
        this.officerLocality = officerLocality;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public String getOfficerContact() {
        return officerContact;
    }

    public void setOfficerContact(String officerContact) {
        this.officerContact = officerContact;
    }

    public String getOfficerDesignation() {
        return officerDesignation;
    }

    public void setOfficerDesignation(String officerDesignation) {
        this.officerDesignation = officerDesignation;
    }

    public String getOfficerEmail() {
        return officerEmail;
    }

    public void setOfficerEmail(String officerEmail) {
        this.officerEmail = officerEmail;
    }

    public String getOfficerZone() {
        return officerZone;
    }

    public void setOfficerZone(String officerZone) {
        this.officerZone = officerZone;
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

    public String getOfficerLocality() {
        return officerLocality;
    }

    public void setOfficerLocality(String officerLocality) {
        this.officerLocality = officerLocality;
    }
}
