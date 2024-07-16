package com.example.demo.Admin;

import jakarta.persistence.*;

@Entity
@Table(name = "Admin")
public class AdminEntity {
    @Id
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;

    public AdminEntity(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public AdminEntity() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AdminEntity{" +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
