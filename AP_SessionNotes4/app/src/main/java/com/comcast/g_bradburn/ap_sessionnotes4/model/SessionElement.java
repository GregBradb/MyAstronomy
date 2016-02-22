package com.comcast.g_bradburn.ap_sessionnotes4.model;

/**
 * Created by Greg on 2/16/2016.
 */
public class SessionElement {
    private long id;
    private String sessionName;
    private String locationID;
//    public static final String FILE_NAME_KEY = "file_name";

    private String targetID;
    private String imageType;
    private String timeStamp;
    private String cameraID;
    private String cameraTimeZone;
    private String cameraDST_Set;
    private String cameraMirrorLockup;
    private Long exposureTime_ms;
    private Long iso;
    private String androidID;
    private String androidTimeZone;
    private String androidDST_Set;
    private String momentaryInterference;   // combine Vibration/bump and Meteor
    private String latchedInterference;     // combine Cloud, Flashlight, Car Lights, Airplane, Satellite and Other

    public long getId() {
        return id;
    }

    public void setId(long id_in){
        this.id = id_in;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName (String ssnNameIn) {
        this.sessionName = ssnNameIn;
    }

    public String getLocationID() {
        return locationID;
    }

    public void setLocationID(String locIdIn) {
        this.locationID = locIdIn;
    }

    public String getTargetID() {
        return targetID;
    }

    public void setTargetID(String targIdIn) {
        this.targetID = targIdIn;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imgTypeIn) {
        this.imageType = imgTypeIn;
    }

    public String getCameraID() {
        return cameraID;
    }

    public void setCameraID(String cmraIdIn) {
        this.cameraID = cmraIdIn;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String tmStmpIn) {
        this.timeStamp = tmStmpIn;
    }

    public String getCameraTimeZone() {
        return cameraTimeZone;
    }

    public void setCameraTimeZone(String cmraTZ_In) {
        this.cameraTimeZone = cmraTZ_In;
    }

    public String getCameraDST_Set() {
        return cameraDST_Set;
    }

    public void setCameraDST_Set(String cmraDST_In) {
        this.cameraDST_Set = cmraDST_In;
    }

    public String getCameraMirrorLockup() {
        return cameraMirrorLockup;
    }

    public void setCameraMirrorLockup(String cmraMrrLkup_In) {
        this.cameraMirrorLockup = cmraMrrLkup_In;
    }

    public Long getExposureTime_ms() {
        return exposureTime_ms;
    }

    public void setExposureTime_ms(Long expTm_in) {
        this.exposureTime_ms = expTm_in;
    }

    public Long getIso() {
        return iso;
    }

    public void setIso(Long isoIn) {
        this.iso = isoIn;
    }

    public String getAndroidID() {
        return androidID;
    }

    public void setAndroidID(String andrdIdIn) {
        this.androidID = andrdIdIn;
    }

    public String getAndroidTimeZone() {
        return androidTimeZone;
    }

    public void setAndroidTimeZone(String andrdTZ_In) {
        this.androidTimeZone = andrdTZ_In;
    }

    public String getAndroidDST_Set() {
        return androidDST_Set;
    }

    public void setAndroidDST_Set(String andrdDST_In) {
        this.androidDST_Set = andrdDST_In;
    }

    public String getMomentaryInterference() {
        return momentaryInterference;
    }

    public void setMomentaryInterference(String mmntryIntIn) {
        this.momentaryInterference = mmntryIntIn;
    }

    public String getLatchedInterference() {
        return latchedInterference;
    }

    public void setLatchedInterference(String ltchdIntIn) {
        this.latchedInterference = ltchdIntIn;
    }

    @Override
    public String toString() {
        String temp_cameraDST = "Camera DST NOT Set",
                temp_mirrorLockup = "Camera Mirror NOT Locked Up",
                temp_androidDST = "Android DST NOT Set";
        if (cameraDST_Set.equals("true")) temp_cameraDST = "Camera DST IS Set";
        if (cameraMirrorLockup.equals("true")) temp_mirrorLockup = "Camera Mirror IS Locked Up";
        if (androidDST_Set.equals("true")) temp_androidDST = "Android DST IS Set";

        String retString = id + ", " +
                targetID + ", " +
                imageType + ", " +
                cameraID + ", " +
                cameraTimeZone + ", " +
                temp_cameraDST + ", " +
                temp_mirrorLockup + ", " +
                exposureTime_ms + ", " +
                iso + ", " +
                androidID + ", " +
                androidTimeZone + ", " +
                temp_androidDST + ", " +
                momentaryInterference + ", " +
                latchedInterference;
        return retString;
    }

//    private String momentaryInterference;   // combine Vibration/bump and Meteor
//    private String latchedInterference;     // combine Cloud, Flashlight, Car Lights, Airplane, Satellite and Other
}
