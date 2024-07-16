package com.example.demo.Order;

import com.example.demo.User.UserEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;
    @Column(name = "date")
    private String date;
    @Column(name = "amount")
    private Double amount;
    @Column(name = "payment_method")
    private String paymentMethod;
    @Column(name = "status")
    private String status;
    @Column(name = "product_name")
    private String productName;
    private String useremail;

    public OrderEntity(UserEntity user, String date, Double amount, String paymentMethod, String status, String productName) {
        this.user = user;
        this.date = date;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.productName = productName;
        this.useremail = user.getEmail();
    }

    public OrderEntity() {
        this.useremail = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "id=" + id +
                ", user=" + user +
                ", date='" + date + '\'' +
                ", amount=" + amount +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", status='" + status + '\'' +
                ", productName='" + productName + '\'' +
                '}';
    }
}