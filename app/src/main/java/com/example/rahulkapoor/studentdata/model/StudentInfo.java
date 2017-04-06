package com.example.rahulkapoor.studentdata.model;


import android.os.Parcel;
import android.os.Parcelable;


/**
 * Sets all the data entered in the form field in currently passed object
 */
public class StudentInfo implements Parcelable {


    public static final Creator<StudentInfo> CREATOR = new Creator<StudentInfo>() {
        @Override
        public StudentInfo createFromParcel(final Parcel in) {
            return new StudentInfo(in);
        }

        @Override
        public StudentInfo[] newArray(final int size) {
            return new StudentInfo[size];
        }
    };
    private String mname;
    private String mschool;
    private String mrollno;
    private String mEmail;

    /**
     *
     * @param name name is set
     * @param school school is set
     * @param rollno rollno is set
     * @param email email is set
     */
    public StudentInfo(final String name, final String school, final String rollno, final String email) {
        this.mname = name;
        this.mschool = school;
        this.mrollno = rollno;
        this.mEmail = email;

    }

    /**
     *
     * @param in parcel in;
     */
    protected StudentInfo(final Parcel in) {
        mname = in.readString();
        mschool = in.readString();
        mrollno = in.readString();
        mEmail = in.readString();
    }

    /**
     *
     * @return returns name
     */
    public String getName() {
        return mname;
    }

    /**
     *
     * @return returns school;
     */
    public String getSchool() {
        return mschool;
    }

    /**
     *
     * @return returns rollno;
     */
    public String getRollno() {
        return mrollno;
    }

    /**
     *
     * @return returns email;
     */
    public String getEmail() {
        return mEmail;
    }


    @Override
    public int describeContents() {
        return 0;

    }


    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeString(mname);
        dest.writeString(mschool);
        dest.writeString(mrollno);
        dest.writeString(mEmail);
    }
}
