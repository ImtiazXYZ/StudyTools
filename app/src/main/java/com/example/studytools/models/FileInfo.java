
package com.example.studytools.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class FileInfo implements Parcelable {

    @SerializedName("id")
    private int id;
    @SerializedName("user_id")
    private int userid;
    @SerializedName("fileType")
    private int fileType;
    @SerializedName("username")
    private String username;
    private String filename;
    @SerializedName("description")
    private String description;
    @SerializedName("questions")
    private int questions;
    @SerializedName("courseid")
    private int courseid;
    @SerializedName("duration")
    private int duration;

    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("mobile")
    private String mobile;
    @SerializedName("password")
    private String password;


    public FileInfo() {

    }

    public FileInfo(int id, String name, String email, String mobile, String username, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.username = username;
        this.password = password;
    }

    public FileInfo(String description, int userid, int fileType, int questions) {
        this.description = description;
        this.userid = userid;
        this.fileType = fileType;
        this.questions = questions;
    }

    public FileInfo(String description, int userid, int fileType, int questions, int courseid, int duration) {
        this.userid = userid;
        this.fileType = fileType;
        this.description = description;
        this.questions = questions;
        this.courseid = courseid;
        this.duration = duration;
    }

    public FileInfo(int id, String username, String filename, String description) {
        this.id = id;
        this.username = username;
        this.filename = filename;
        this.description = description;
    }

    public FileInfo(int id, String username, String filename, String description, int questions) {
        this.id = id;
        this.username = username;
        this.filename = filename;
        this.description = description;
        this.questions = questions;
    }

    public final static Creator<FileInfo> CREATOR = new Creator<FileInfo>() {

        @SuppressWarnings({
                "unchecked"
        })
        public FileInfo createFromParcel(Parcel in) {
            FileInfo instance = new FileInfo();
            instance.description = ((String) in.readValue((String.class.getClassLoader())));
            instance.username = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public FileInfo[] newArray(int size) {
            return (new FileInfo[size]);
        }

    };


    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(description);
        dest.writeValue(username);
    }

    public int describeContents() {
        return 0;
    }

    public int getId() {
        return id;
    }

    public int getUserid() {
        return userid;
    }

    public String getUsername() {
        return username;
    }

    public String getFilename() {
        return filename;
    }

    public String getDescription() {
        return description;
    }

    public int getFileType() {
        return fileType;
    }

    public int getQuestions() {
        return questions;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getPassword() {
        return password;
    }
}
