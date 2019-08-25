package com.example.milk.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;

@Entity
public class Details {
    @PrimaryKey(autoGenerate = true)
    private int keyId;
    @ColumnInfo
    @Expose
    private String title;
    @ColumnInfo
    @Expose
    private long code;
    @ColumnInfo
    @Expose
    private int unitPrice;
    @ColumnInfo
    @Expose
    private int total;
    @ColumnInfo
    @Expose
    private int quantity;
    @ColumnInfo
    @Expose
    private int productId;
    @ColumnInfo
    @Expose
    private int balance;
    @ColumnInfo
    @Expose
    private int paid;
    @ColumnInfo
    @Expose
    private String type;
    @ColumnInfo
    @Expose
    private String dateSale;
    @ColumnInfo
    @Expose
    private int mrp;
    @ColumnInfo
    @Expose
    private int sellingPrice;

    public Details() {

    }

    @Ignore
    public Details(String title, long code, int unitPrice, int total, int quantity, int productId,
                   int balance, int paid, String type, String dateSale, int mrp, int sellingPrice) {
        this.title = title;
        this.code = code;
        this.unitPrice = unitPrice;
        this.total = total;
        this.quantity = quantity;
        this.productId = productId;
        this.balance = balance;
        this.paid = paid;
        this.type = type;
        this.dateSale = dateSale;
        this.mrp = mrp;
        this.sellingPrice = sellingPrice;
    }

    public int getKeyId() {
        return keyId;
    }

    public void setKeyId(int keyId) {
        this.keyId = keyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDateSale() {
        return dateSale;
    }

    public void setDateSale(String dateSale) {
        this.dateSale = dateSale;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getPaid() {
        return paid;
    }

    public void setPaid(int paid) {
        this.paid = paid;
    }

    public int getMrp() {
        return mrp;
    }

    public void setMrp(int mrp) {
        this.mrp = mrp;
    }

    public int getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(int sellingPrice) {
        this.sellingPrice = sellingPrice;
    }
}
