package com.example.readingisgoodapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "orderDetailsId", nullable = false)
    private int orderDetailsId;
    private int oid;
    private int pid;
    private double price;
    private int qty;

    public OrderDetails() {
    }

    public OrderDetails(int orderDetailsId, int oid, int pid, double price, int qty) {
        this.orderDetailsId = orderDetailsId;
        this.oid = oid;
        this.pid = pid;
        this.price = price;
        this.qty = qty;
    }

    public int orderDetailsId() {
        return orderDetailsId;
    }

    public void setOrderDetailsId(int odid) {
        this.orderDetailsId = odid;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "OrderDetails{" + "orderDetailsId=" + orderDetailsId + ", oid=" + oid + ", pid=" + pid + ", price=" + price + ", qty=" + qty + '}';
    }
}
