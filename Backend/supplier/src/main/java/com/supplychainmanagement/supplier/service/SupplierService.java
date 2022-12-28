package com.supplychainmanagement.supplier.service;

import com.supplychainmanagement.supplier.entity.Supplier;
import com.supplychainmanagement.supplier.repository.SupplierRepository;
import com.supplychainmanagement.supplier.template.LoginTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public Supplier saveSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public List<Supplier> getAllSupplier() {
        return supplierRepository.findAll();
    }

    public Supplier getSupplierById(String supplierId) {
        List<Supplier> supplierList = getAllSupplier();
        for(Supplier supplier : supplierList){
            if(supplier.getSupplierId().equals(supplierId)){
                return supplier;
            }
        }
        return null;
    }

    public ResponseEntity<HttpStatus> deleteSupplierById(String supplierId) {
        List<Supplier> supplierList = getAllSupplier();
        for (Supplier supplier : supplierList) {

            if(supplier.getSupplierId().equals(supplierId)){
                supplierRepository.delete(supplier);
                return new ResponseEntity<>(HttpStatus.OK);
            }

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);}


    public Supplier updatesupplierById(String supplierId,  Supplier supplier) {
        Supplier _supplier = getSupplierById(supplierId);
        System.out.print(_supplier);
        if(_supplier!=null){
            _supplier.setSupplierName(supplier.getSupplierName());
            _supplier.setSupplierEmail(supplier.getSupplierEmail());
            _supplier.setSupplierPassword(supplier.getSupplierPassword());

            supplierRepository.save(_supplier);
            return _supplier;
        }
        return null;
    }


    public Supplier supplierLogin(Supplier supplier) {
        List<Supplier> suppliers = getAllSupplier();
        for(Supplier supplier1 : suppliers){
            if(supplier1.getSupplierEmail().equals(supplier.getSupplierEmail()) &&
            supplier1.getSupplierPassword().equals(supplier.getSupplierPassword())){
                return supplier1;
            }
        }
        return null;
    }

    public Supplier findByEmail(String supplierEmail) {
        List<Supplier> suppliers = getAllSupplier();
        for(Supplier supplier:suppliers){
            if(supplier.getSupplierEmail().equals(supplierEmail)){
                return supplier;
            }
        }
        return null;
    }

    public Supplier updateByEmail(String supplierEmail, Supplier supplier) {
        Supplier _supplier = findByEmail(supplierEmail);
        if(_supplier!=null){
            _supplier.setSupplierName(supplier.getSupplierName());
            _supplier.setSupplierEmail(supplier.getSupplierEmail());
            _supplier.setSupplierPassword(supplier.getSupplierPassword());

            supplierRepository.save(_supplier);
            return _supplier;
        }
        return null;
    }
}


