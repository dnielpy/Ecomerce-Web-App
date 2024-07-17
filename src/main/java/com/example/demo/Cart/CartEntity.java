package com.example.demo.Cart;

import com.example.demo.User.UserEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cart")
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private UserEntity user;
    @ElementCollection
    private List<Long> products;
    @Column(name = "username", insertable = false, updatable = false)
    private final String username;

    public CartEntity(UserEntity user, List<Long> products) {
        this.user = user;
        this.products = products;
        this.username = user.getUsername();
    }

    public CartEntity(UserEntity user) {
        this.user = user;
        this.username = user.getUsername();
    }

    public CartEntity() {
        this.username = null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public List<Long> getProducts() {
        return products;
    }

    public void setProducts(List<Long> products) {
        this.products = products;
    }
}
