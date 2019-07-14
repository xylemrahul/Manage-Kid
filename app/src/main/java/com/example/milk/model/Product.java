package com.example.milk.model;


import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {

    private int clientId;
    private int mrp;
    private int sellingPrice;
    private int unitPrice;
    private int quantity;
    private String code;
    private String title;
    private int id;
    private String createdBy;
    private String creationDate;
    private String lastModifiedBy;
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
