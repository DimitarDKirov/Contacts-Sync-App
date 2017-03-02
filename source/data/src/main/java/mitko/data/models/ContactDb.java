package mitko.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by dimki on 01.03.2017 г..
 */

public class ContactDb {
    @SerializedName("_id")
    private String id;
    private String name;
    private String phoneNumber;
    private String notes;
    private String company;
    private String createdBy;
    private Date createdAt;

    public ContactDb() {
    }

    public ContactDb(String id, String name, String phoneNumber, String notes, String company, String createdBy, Date createdAt) {
        this();
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.notes = notes;
        this.company = company;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }

    public ContactDb(String name, String phoneNumber, String company) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.company = company;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
