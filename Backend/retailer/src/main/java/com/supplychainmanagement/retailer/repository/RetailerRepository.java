package com.supplychainmanagement.retailer.repository;

import com.supplychainmanagement.retailer.entity.Retailer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RetailerRepository extends MongoRepository<Retailer,String> {
    Retailer getRetailerByRetailerEmail(String retailerEmail);
}
