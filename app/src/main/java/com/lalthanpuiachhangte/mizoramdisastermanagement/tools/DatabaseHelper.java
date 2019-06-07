package com.lalthanpuiachhangte.mizoramdisastermanagement.tools;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.lalthanpuiachhangte.mizoramdisastermanagement.Entity.Incident;

import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "NotificationDatabase5";

    //DATABASE NAME
    private static final String TABLE_NOTIFICATION = "notification";

    public static final String KEY_serialNumber = "serialNumber";
    public static final String KEY_disasterType = "disasterType";
    public static final String KEY_locality = "locality";
    public static final String KEY_landmarks = "landmarks";
    public static final String KEY_disastersDetails = "disastersDetails";
    public static final String KEY_details = "details";
    public static final String KEY_disasterTypeId = "disasterTypeId";
    public static final String KEY_district = "district";
    public static final String KEY_inLocation = "inLocation";
    public static final String KEY_lat = "lat";
    public static final String KEY_lng = "lng";
    public static final String KEY_location = "location";
    public static final String KEY_username = "username";
    public static final String KEY_phone = "phone";
    public static final String KEY_reportOn = "reportOn";
    public static final String KEY_status = "status";
    public static final String KEY_userId = "userId";
    public static final String KEY_officerContact = "officerContact";
    public static final String KEY_officerId = "officerId";
    public static final String KEY_officerName = "officerName";
    public static final String KEY_zoneId = "zoneId";

    public static final String CREATE_NOTIFICATION_TABLE = "CREATE TABLE "
            + TABLE_NOTIFICATION
            + "("
            + KEY_serialNumber + " INTEGER PRIMARY KEY,"
            + KEY_disasterType + " TEXT,"
            + KEY_locality + " TEXT,"
            + KEY_landmarks + " TEXT,"
            + KEY_disastersDetails + " TEXT,"
            + KEY_details + " TEXT,"
            + KEY_disasterTypeId + " TEXT,"
            + KEY_district + " TEXT,"
            + KEY_inLocation + " TEXT,"
            + KEY_lat + " TEXT,"
            + KEY_lng + " TEXT,"
            + KEY_location + " TEXT,"
            + KEY_username + " TEXT,"
            + KEY_phone + " TEXT,"
            + KEY_reportOn + " TEXT,"
            + KEY_status + " TEXT,"
            + KEY_userId + " TEXT,"
            + KEY_officerContact + " TEXT,"
            + KEY_officerId + " TEXT,"
            + KEY_officerName + " TEXT,"
            + KEY_zoneId + " TEXT"
            +")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_NOTIFICATION_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTIFICATION);
        onCreate(db);

    }

    //CREATE NOTIFICATION
    public void insertNotification (Incident mIncident) {
        SQLiteDatabase db = this.getWritableDatabase();

       // mIncident.setUsername("lelele");
        ContentValues values = new ContentValues();

        values.put(KEY_serialNumber ,mIncident.getSerialNumber());
        values.put(KEY_disasterType ,mIncident.getDisasterType());
        values.put(KEY_locality ,mIncident.getLocality());
        values.put(KEY_landmarks ,mIncident.getLandmarks());
        values.put(KEY_disastersDetails ,mIncident.getDisastersDetails());
        values.put(KEY_details ,mIncident.getDetails());
        values.put(KEY_disasterTypeId ,mIncident.getDisasterTypeId());
        values.put(KEY_district ,mIncident.getDistrict());
        values.put(KEY_inLocation ,mIncident.getInLocation());
        values.put(KEY_lat ,mIncident.getLat());
        values.put(KEY_lng ,mIncident.getLng());
        values.put(KEY_location ,mIncident.getLocation());
        values.put(KEY_username ,mIncident.getUsername());
        values.put(KEY_phone ,mIncident.getPhone());
        values.put(KEY_reportOn ,mIncident.getReportOn());
        values.put(KEY_status ,mIncident.getStatus());
        values.put(KEY_userId ,mIncident.getUserId());
        values.put(KEY_officerContact ,mIncident.getOfficerContact());
        values.put(KEY_officerId ,mIncident.getOfficerId());
        values.put(KEY_officerName ,mIncident.getOfficerName());
        values.put(KEY_zoneId ,mIncident.getZoneId());

        db.insert(TABLE_NOTIFICATION,null, values);
      //  db.close();
        Log.d(TAG,"MAY be insert");
    }

/*
    public Contacts getContact(int id){
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.query(TABLE_CONTACT, new String[]{KEY_CONTACTID,KEY_EMPNAME,KEY_DESIGANTION},
                KEY_CONTACTID+"=?", new String[]{String.valueOf(id)},
                null,null,null);

        if (cursor!=null)
            cursor.moveToFirst();

        Contacts contacts=new Contacts();
        contacts.setContactId((Integer.parseInt(cursor.getString(0))));
        contacts.setEmpName(cursor.getString(1));
        contacts.setDesignation(cursor.getString(2));
        contacts.setMobile(cursor.getString(3));
        contacts.setLandlineOffice(cursor.getString(4));
        contacts.setLandlineRes(cursor.getString(5));
        contacts.setFax(cursor.getString(6));
        contacts.setEmail(cursor.getString(7));
        contacts.setDept_id(cursor.getString(8));

        return contacts;
    }*/


    //GET ALL NOTIFICATION
    public List<Incident> getAllNotification() {

        List <Incident> notifiationList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String selectAll = "SELECT * FROM "+ TABLE_NOTIFICATION;

        Cursor cursor = db.rawQuery(selectAll, null);

        if (cursor.moveToFirst()) {
            do {
                Incident mIncident =new Incident();
                mIncident.setSerialNumber(Integer.parseInt(cursor.getString(0)));
                mIncident.setDisasterType(cursor.getString(1));
                mIncident.setLocality(cursor.getString(2));
                mIncident.setLandmarks(cursor.getString(3));
                mIncident.setDisastersDetails(cursor.getString(4));
                mIncident.setDetails(cursor.getString(5));
                mIncident.setDisasterTypeId(cursor.getString(6));
                mIncident.setDistrict(cursor.getString(7));
                mIncident.setInLocation(cursor.getString(8));
                mIncident.setLat(cursor.getString(9));
                mIncident.setLng(cursor.getString(10));
                mIncident.setLocation(cursor.getString(11));
                mIncident.setUsername(cursor.getString(12));
                mIncident.setPhone(cursor.getString(13));
                mIncident.setReportOn(cursor.getString(14));
                mIncident.setStatus(cursor.getString(15));
                mIncident.setUserId(cursor.getString(16));
                mIncident.setOfficerContact(cursor.getString(17));
                mIncident.setOfficerId(cursor.getString(18));
                mIncident.setOfficerName(cursor.getString(19));
                mIncident.setZoneId(cursor.getString(20));

                notifiationList.add(mIncident);

            }while (cursor.moveToNext());
        }

        return notifiationList;
    }

    //CODE TO UPDATE A SINGLE ROW
    public int updateNotification(Incident mIncident) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_serialNumber ,mIncident.getSerialNumber());
        values.put(KEY_disasterType ,mIncident.getDisasterType());
        values.put(KEY_locality ,mIncident.getLocality());
        values.put(KEY_landmarks ,mIncident.getLandmarks());
        values.put(KEY_disastersDetails ,mIncident.getDisastersDetails());
        values.put(KEY_details ,mIncident.getDetails());
        values.put(KEY_disasterTypeId ,mIncident.getDisasterTypeId());
        values.put(KEY_district ,mIncident.getDistrict());
        values.put(KEY_inLocation ,mIncident.getInLocation());
        values.put(KEY_lat ,mIncident.getLat());
        values.put(KEY_lng ,mIncident.getLng());
        values.put(KEY_location ,mIncident.getLocation());
        values.put(KEY_username ,mIncident.getUsername());
        values.put(KEY_phone ,mIncident.getPhone());
        values.put(KEY_reportOn ,mIncident.getReportOn());
        values.put(KEY_status ,mIncident.getStatus());
        values.put(KEY_userId ,mIncident.getUserId());
        values.put(KEY_officerContact ,mIncident.getOfficerContact());
        values.put(KEY_officerId ,mIncident.getOfficerId());
        values.put(KEY_officerName ,mIncident.getOfficerName());
        values.put(KEY_zoneId ,mIncident.getZoneId());

        return db.update(TABLE_NOTIFICATION,values,KEY_serialNumber + " = ?",
                new String[] {String.valueOf(mIncident.getSerialNumber())});

    }



}
