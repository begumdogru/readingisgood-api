package com.example.readingisgoodapi.controller;


import com.example.readingisgoodapi.dao.CustomerDao;
import com.example.readingisgoodapi.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CustomerController {

    @Autowired
    private CustomerDao customerDao;

    @GetMapping("/customer")
    public ResponseEntity<?> getCustomer() {
        List<Customer> customers = customerDao.getAllCustomer();
        Map<String, String> response = new HashMap<>();
        response.put("customers", customers.toString());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/addCustomer")
    public ResponseEntity<?> saveCustomer(@RequestParam Long cid,@RequestParam String cname,
                                          @RequestParam String phone) {
        Customer customer = new Customer();
        customer.setCid(cid);
        customer.setCname(cname);
        customer.setPhone(phone);
        boolean status = customerDao.saveCustomer(customer);
        Map<String, String> response = new HashMap<>();
        if (status) {
            response.put("sm", "Customer Info Saved Successfully");
        } else {
            response.put("em", "Customer Info Not Saved");
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/editCustomer/{id}")
    public ResponseEntity<?> editCustomer(@PathVariable("id") int id) {
        Customer customer = customerDao.getCustomer(id);
        Map<String, Object> response = new HashMap<>();
        response.put("customer", customer);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/updateCustomer")
    public ResponseEntity<?> updateCustomer(@RequestParam Long cid,
                                            @RequestParam String cname,
                                            @RequestParam String phone) {
        Customer customer = new Customer();
        customer.setCid(cid);
        customer.setCname(cname);
        customer.setPhone(phone);
        boolean status = customerDao.updateCustomer(customer);
        Map<String, String> response = new HashMap<>();
        if (status) {
            response.put("sm", "Customer Info Update Successfully");
        } else {
            response.put("em", "Customer Info Not Update");
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/deleteCustomer/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") int id) {
        boolean status = customerDao.deleteCustomer(id);
        Map<String, String> response = new HashMap<>();
        if (status) {
            response.put("sm", "Customer Info Deleted Successfully");
        } else {
            response.put("em", "Customer Info Not Deleted");
        }
        return ResponseEntity.ok(response);
    }

}