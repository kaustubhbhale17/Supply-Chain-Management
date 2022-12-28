package com.supplychainmanagement.order.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order {
    @Id
    private String orderNumber;
    private String retailerName;
    private String orderDescription;
    private List<Product> cartItems;
    private String orderStatus;
    private double orderBill;
}
