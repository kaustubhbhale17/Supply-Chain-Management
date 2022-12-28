package com.supplychainmanagement.retailer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Retailer {
    @Id
    @Indexed(unique = true)
    private String retailerId;
    private String retailerName;
    private String retailerCompany;
    private String retailerEmail;
    private String retailerPassword;
    private String retailerContactNumber;
}
