package com.example.readingisgoodapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "cid", nullable = false)
    private Long cid;

    private String cname;
    private String phone;


    public Customer() {
    }

    public Customer(Long cid, String cname, String phone) {
        this.cid = cid;
        this.cname = cname;
        this.phone = phone;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Customer{" + "cid=" + cid + ", cname=" + cname + ", phone=" + phone + '}';
    }

}
