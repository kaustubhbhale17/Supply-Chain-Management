package com.supplychainmanagment.inventory.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {
    @Id
    private String productId;
    private String productName;
    private String productType;
    private int productQuantity;
    private double productPrice;
}
