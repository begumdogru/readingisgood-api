package com.example.readingisgoodapi.restcontroller;

import com.example.readingisgoodapi.dao.OrderDetailsDao;
import com.example.readingisgoodapi.model.OrderDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orderdetails")
public class OrderDetailsRestController {

    @Autowired
    private OrderDetailsDao orderDetailsDao;

    @RequestMapping(value = "/getAllOrderDetails", method = RequestMethod.GET, headers = "Accept=application/json")
    public List<OrderDetails> getAllOrderDetails(){
        return orderDetailsDao.getAllOrderDetails();
    }


    @RequestMapping(value = "/getOdDetailsByOID/{oid}", method = RequestMethod.GET, headers = "Accept=application/json")
    public List<OrderDetails> getOdDetailsByOID(@PathVariable("oid") String oid){
        return orderDetailsDao.getAllOrderDetailsByOrderID(Integer.parseInt(oid));
    }



    @RequestMapping(value = "/orderdetail", method = RequestMethod.POST)
    public OrderDetails saveOrderDetails(@RequestBody OrderDetails orderDetails) {
        orderDetailsDao.saveOrderDetails(orderDetails);
        return orderDetails;
    }

}
