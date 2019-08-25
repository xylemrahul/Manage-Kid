package com.example.milk.model;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;

@Entity
public class Product implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int keyId;
    @ColumnInfo
@Expose
    private int clientId;
    @ColumnInfo
@Expose
    private int mrp;
    @ColumnInfo
@Expose
    private int sellingPrice;
    @ColumnInfo
@Expose
    private int unitPrice;
    @ColumnInfo
@Expose
    private int quantity;
    @ColumnInfo
@Expose
    private String code;
    @ColumnInfo
@Expose
    private String title;
    @ColumnInfo
@Expose
    private int id;
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

    protected Product(Parcel in) {
        clientId = in.readInt();
        mrp = in.readInt();
        sellingPrice = in.readInt();
        unitPrice = in.readInt();
        quantity = in.readInt();
        code = in.readString();
        title = in.readString();
        id = in.readInt();
        createdBy = in.readString();
        creationDate = in.readString();
        lastModifiedBy = in.readString();
        lastModifiedDate = in.readString();
    }

    public Product() {
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public int getKeyId() {
        return keyId;
    }

    public void setKeyId(int keyId) {
        this.keyId = keyId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getMrp() {
        return mrp;
    }

    public void setMrp(Integer mrp) {
        this.mrp = mrp;
    }

    public Integer getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Integer sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Integer getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(clientId);
        parcel.writeInt(mrp);
        parcel.writeInt(sellingPrice);
        parcel.writeInt(unitPrice);
        parcel.writeInt(quantity);
        parcel.writeString(code);
        parcel.writeString(title);
        parcel.writeInt(id);
        parcel.writeString(createdBy);
        parcel.writeString(creationDate);
        parcel.writeString(lastModifiedBy);
        parcel.writeString(lastModifiedDate);
    }
}
