package com.example.demo.Order;

public class OrderDTO {
    private String userEmail;
    private double amount;
    private String date;
    private String paymentMethod;
    private String status;
    private String productName;

    public OrderDTO(String userEmail, double amount, String date, String paymentMethod, String status, String productName) {
        this.userEmail = userEmail;
        this.amount = amount;
        this.date = date;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.productName = productName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public String getProductName() {
        return productName;
    }
}