package com.example.milk.model;

import androidx.room.ColumnInfo;

import com.google.gson.annotations.Expose;

public class Latest {

    @ColumnInfo
@Expose
    private Integer clientId;
    @ColumnInfo
@Expose
    private Integer unitPrice;
    @ColumnInfo
@Expose
    private Integer total;
    @ColumnInfo
@Expose
    private Integer quantity;
    @ColumnInfo
@Expose
    private Integer productId;
    @ColumnInfo
@Expose
    private String dateSale;
    @ColumnInfo
@Expose
    private String custCode;
    @ColumnInfo
@Expose
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

    public Latest(){

    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getDateSale() {
        return dateSale;
    }

    public void setDateSale(String dateSale) {
        this.dateSale = dateSale;
    }

    public String getCustCode() {
        return custCode;
    }

    public void setCustCode(String custCode) {
        this.custCode = custCode;
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
