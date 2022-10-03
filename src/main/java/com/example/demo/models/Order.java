package com.example.demo.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    private int idGest;
    private String productName;
    private double orderQuantity;
    private int statusOrder = 0;

    private Date dateOrder;

    public Order() {
    }

    public Order(int id, int idGest, String productName, double orderQuantity, Date dateOrder) {
        this.id = id;
        this.idGest = idGest;
        this.productName=productName;
        this.orderQuantity=orderQuantity;
        this.dateOrder=dateOrder;
        this.statusOrder=statusOrder;

    }

    public int getOrderStatus() {
        return statusOrder;
    }

    public void setOrderStatus(int statusOrder) {
        this.statusOrder = statusOrder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdGest() {
        return idGest;
    }

    public void setIdGest(int idGest) {
        this.idGest = idGest;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(double orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", idGest=" + idGest +
                ", productName='" + productName + '\'' +
                ", orderQuantity=" + orderQuantity +
                ", dateOrder=" + dateOrder +
                '}';
    }
}
