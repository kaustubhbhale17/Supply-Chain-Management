package com.supplychainmanagement.supplier.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Supplier {
    @Id
    @Indexed(unique = true)
    private String supplierId;
    private String supplierName;
    private String supplierEmail;
    private String supplierPassword;

}
