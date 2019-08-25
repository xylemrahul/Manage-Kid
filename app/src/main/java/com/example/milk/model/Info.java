package com.example.milk.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;

@Entity
public class Info {

    @ColumnInfo
@Expose
    private Integer clientId;
    @ColumnInfo
@Expose
    private String contact;
    @ColumnInfo
@Expose
    private long code;
    @ColumnInfo
@Expose
    private String name;
    @ColumnInfo
@Expose
    private String adress;
    @PrimaryKey
    @NonNull
    private Integer id;
    @ColumnInfo
@Expose
    private String createdBy;
    @ColumnInfo
@Expose
    private String creationDate;
    @ColumnInfo
@Expose
    private String lastModifiedBy;
    @ColumnInfo
@Expose
    private String lastModifiedDate;

    public Info(){

    }

    public Info(long code, String name, String phn, String add) {
        this.code = code;
        this.name = name;
        this.contact = phn;
        this.adress = add;

    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

}
