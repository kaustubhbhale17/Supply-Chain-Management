package com.supplychainmanagement.order.service;


import com.supplychainmanagement.order.entity.Order;
import com.supplychainmanagement.order.entity.Product;
import com.supplychainmanagement.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {


    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    public OrderService(OrderRepository orderRepository, RestTemplate restTemplate) {
        this.orderRepository = orderRepository;
        this.restTemplate = restTemplate;
    }


    public Order placeOrder(Order order) {
        List<Product> products = new ArrayList<>();


        for(Product p : order.getCartItems()){
            Product product = restTemplate.getForObject("http://localhost:3003/inventory/findbyname/"+p.getProductName(),Product.class);
            System.out.println(product);
            int qty =p.getProductQuantity();
            product.setProductQuantity(qty);
            System.out.println(product);
            products.add(product);
        }
        order.setCartItems(products);
        order.setOrderStatus("Your request is under process.Please check after some time!");
        order.setOrderBill(0.0);
        return orderRepository.save(order);
    }


    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(String orderId) {

        List<Order> orders = getAllOrders();
        for(Order order: orders){
            if(order.getOrderNumber().equals(orderId)){
                System.out.println("Order Found! ");
                return order;
            }
        }
        System.out.println("No Order Found! ");

        return null;

    }

    public Order updateOrderById(String orderId, Order order) {
        Order orderData = getOrderById(orderId);
        if(orderData!=null){
            orderData.setOrderStatus(order.getOrderStatus());
            orderData.setOrderBill(order.getOrderBill());

            orderRepository.save(orderData);
            return order;
        }

        System.out.println("Cannot Update! No order found");
        return null;
    }

    public ResponseEntity<HttpStatus> deleteOrderById(String orderId) {
        List<Order> orders = getAllOrders();
        for(Order order : orders){
            if(order.getOrderNumber().equals(orderId)){
                orderRepository.delete(order);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public List<Order> getOrderByRetailerName(String retailerName) {
        List<Order> orders = getAllOrders();
        List<Order> retailerOrders = new ArrayList<>();
        for(Order order: orders){
            if(order.getRetailerName().equals(retailerName)){
                System.out.println("Order Found! ");
                retailerOrders.add(order);
            }
        }

        return retailerOrders;
    }
}
