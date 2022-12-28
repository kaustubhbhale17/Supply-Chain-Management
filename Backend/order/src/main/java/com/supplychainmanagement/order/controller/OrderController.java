package com.supplychainmanagement.order.controller;

import com.supplychainmanagement.order.entity.Order;
import com.supplychainmanagement.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "*")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/placeorder")
    public Order placeOrder(@RequestBody Order order){
        return orderService.placeOrder(order);
    }

    @GetMapping("/findallorders")
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/getorderbyid/{id}")
    public Order getOrderById(@PathVariable("id") String orderId){
        return orderService.getOrderById(orderId);
    }

    @PutMapping("/update/{id}")
    public Order updateOrderById(@PathVariable("id")String orderId,@RequestBody Order order){
        return orderService.updateOrderById(orderId,order);
    }

    //to the reject order
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteOrderById(@PathVariable("id") String orderId){
        return orderService.deleteOrderById(orderId);
    }

    //get order by retailer name
    @GetMapping("/getorderbyretailername/{name}")
    public List<Order> getOrderByRetailerName(@PathVariable("name") String retailerName){
        return orderService.getOrderByRetailerName(retailerName);
    }
}
