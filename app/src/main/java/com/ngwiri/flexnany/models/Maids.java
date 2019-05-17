package com.ngwiri.flexnany;

public class Maids {

    private String mName;
    private String mEmail;
    private String mMsisdn;
    private String mDescription;
    private String mAddress;
    private String mRating;
    private String mServices;
    private String mExperience;
    private String mAge;
    private String mStatus;


    public Maids(String Name, String Email, String Msisdn, String Description, String Address, String Rating, String Services, String Experience, String Age, String Status) {

        this.mName = Name;
        this.mEmail = Email;
        this.mMsisdn = Msisdn;
        this.mDescription = Description;
        this.mAddress = Address;
        this.mRating = Rating;
        this.mServices = Services;
        this.mExperience = Experience;
        this.mAge = Age;
        this.mStatus = Status;
    }


    public String getmName() {
        return mName;
    }

    public String getmEmail() {
        return mEmail;
    }

    public String getmMsisdn() {
        return mMsisdn;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmAddress() {
        return mAddress;
    }

    public String getmRating() {
        return mRating;
    }

    public String getmServices() {
        return mServices;
    }

    public String getmExperience() {
        return mExperience;
    }


    public String getmAge() {
        return mAge;
    }

    public String getmStatus() {
        return mStatus;
    }
}
