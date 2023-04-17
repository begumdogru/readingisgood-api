package com.example.readingisgoodapi.controller;

import com.example.readingisgoodapi.dao.ProductDao;
import com.example.readingisgoodapi.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class ProductController {
    @Autowired
    ProductDao productDao;

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> getAllProduct() {
        List<Product> products = productDao.getAllProduct();
        return ResponseEntity.ok(products);
    }
    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    public ResponseEntity<String> saveProduct(@RequestBody Product product) {
        boolean status = productDao.saveProduct(product);
        if (status) {
            return ResponseEntity.ok("Product Info Saved Successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Product Info Not Saved");
        }
    }

    @RequestMapping(value = "/editProduct/{id}", method = RequestMethod.GET)
    public ResponseEntity<Product> editProduct(@PathVariable("id") int id) {
        Product product = productDao.getProduct(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/updateProduct", method = RequestMethod.PUT)
    public ResponseEntity<String> updateProduct(@RequestBody Product product) {
        Product existingProduct = productDao.getProduct(product.getPid());
        if (existingProduct == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        String updatedPname = product.getPname();
        Double updatedPrice = product.getPrice();
        Integer updatedQty = product.getQty();

        if (updatedPname != null && !updatedPname.isEmpty()) {
            existingProduct.setPname(updatedPname);
        }
        if (updatedPrice != null) {
            Double currentPrice = existingProduct.getPrice();
            if (currentPrice != null) {
                existingProduct.setPrice(updatedPrice);
            }
        }
        if (updatedQty != null) {
            Integer currentQty = existingProduct.getQty();
            if (currentQty != null) {
                existingProduct.setQty(updatedQty);
            }
        }

        boolean status = productDao.updateProduct(existingProduct);
        if (status) {
            return ResponseEntity.ok("Product Info Updated Successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Product Info Not Updated");
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") int id) {
        boolean status = productDao.deleteProduct(id);
        if (status) {
            return ResponseEntity.ok("Product Deleted Successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Product Not Delete");
        }
    }
}