package com.example.milk.model;

public class Details {

    private String title;
    private String code;
    private String unitPrice;
    private String total;
    private String quantity;
    private String productId;
    private String balance;
    private String paid;
    private String type;
    private String dateSale;
    private String mrp;
    private String sellingPrice;

    public Details(String title, String code, String unitPrice, String total, String quantity, String productId,
                   String balance, String paid, String type, String dateSale, String mrp, String sellingPrice) {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
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

    public String getMrp() {
        return mrp;
    }

    public void setMrp(String mrp) {
        this.mrp = mrp;
    }

    public String getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(String sellingPrice) {
        this.sellingPrice = sellingPrice;
    }
}
