package com.example.milk.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Details {
    @Ignore
    private int clientId;
    @Ignore
    private int id;
    @ColumnInfo
    private String createdBy;
    @ColumnInfo
    private String creationDate;
    @ColumnInfo
    private String lastModifiedBy;
    @ColumnInfo
    private String lastModifiedDate;
    @ColumnInfo
    private String title;
    @ColumnInfo
    private long code;
    @ColumnInfo
    private int unitPrice;
    @ColumnInfo
    private int total;
    @ColumnInfo
    private int quantity;
    @NonNull
    @PrimaryKey
    private int productId;
    @ColumnInfo
    private int balance;
    @ColumnInfo
    private int paid;
    @ColumnInfo
    private String type;
    @ColumnInfo
    private String dateSale;
    @ColumnInfo
    private int mrp;
    @ColumnInfo
    private int sellingPrice;

    public Details(){

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

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
