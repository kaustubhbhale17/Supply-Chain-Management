package com.supplychainmanagment.inventory.repository;

import com.supplychainmanagment.inventory.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product,String> {
}
