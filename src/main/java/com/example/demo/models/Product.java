package com.example.demo.models;

import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private double price;
    private int quantity;
    private Date expiDate;

    public Product(int id, String name, double price, int quantity) {
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public Date getExpiDate() {
        return expiDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setExpiDate(Date expiDate) {
        this.expiDate = expiDate;
    }

    public Product() {
    }
    public Product(int id, double price, int quantity, Date expiDate,String name) {
        this.expiDate=expiDate;
        this.id=id;
        this.name = name;
        this.price=price;
        this.quantity =quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", expiDate=" + expiDate +
                '}';
    }

}
