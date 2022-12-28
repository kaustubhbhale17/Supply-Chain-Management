package com.supplychainmanagement.supplier.repository;

import com.supplychainmanagement.supplier.entity.Supplier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends MongoRepository<Supplier,String> {

    Supplier getSupplierBySupplierEmail(String supplierEmail);


}
