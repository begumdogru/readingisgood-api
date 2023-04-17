package com.example.readingisgoodapi.controller;
import com.example.readingisgoodapi.dao.CustomerDao;
import com.example.readingisgoodapi.dao.OrderDao;
import com.example.readingisgoodapi.dao.OrderDetailsDao;
import com.example.readingisgoodapi.dao.ProductDao;
import com.example.readingisgoodapi.messages.ErrorResponse;
import com.example.readingisgoodapi.messages.SuccessResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class OrderController {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderDetailsDao orderDetailsDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private CustomerDao customerDao;

    @GetMapping("/")
    public ResponseEntity<?> indexPage(HttpServletRequest request) {
        try {
            SuccessResponse successResponse = new SuccessResponse();
            successResponse.setMessage("Success");
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("orderNo", orderDao.getOrderNo());
            responseData.put("products", productDao.getAllProduct());
            responseData.put("customers", customerDao.getAllCustomer());
            successResponse.setData(responseData);

            return ResponseEntity.ok(successResponse);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setError("Error");
            errorResponse.setMessage(e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}