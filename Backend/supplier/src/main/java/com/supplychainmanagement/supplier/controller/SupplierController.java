package com.supplychainmanagement.supplier.controller;

import com.supplychainmanagement.supplier.entity.Supplier;
import com.supplychainmanagement.supplier.service.SupplierService;
import com.supplychainmanagement.supplier.template.LoginTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supplier")
@CrossOrigin(origins = "*")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;


    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;

    }

    @PostMapping("/add")
    public Supplier saveSupplier(@RequestBody Supplier supplier){
        return supplierService.saveSupplier(supplier);
    }

    @GetMapping("/findall")
    public List<Supplier> getAllSupplier(){
        return supplierService.getAllSupplier();
    }

    @GetMapping("/find/{id}")
    public Supplier getSupplierById(@PathVariable("id") String supplierId){
        return supplierService.getSupplierById(supplierId);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteSupplierById(@PathVariable("id") String supplierId){
        return supplierService.deleteSupplierById(supplierId);
    }

    @PutMapping("/update/{id}")
    public Supplier updateSupplierById(@PathVariable("id") String supplierId,@RequestBody Supplier supplier){
        return supplierService.updatesupplierById(supplierId,supplier);
    }

    @PostMapping("/signin")
    public Supplier supplierLogin(@RequestBody Supplier supplier){
        return supplierService.supplierLogin(supplier);
    }

    @GetMapping("/findByEmail/{id}")
    public Supplier getByEmail(@PathVariable("id") String supplierEmail){
        return supplierService.findByEmail(supplierEmail);
    }

    @PutMapping("/updateByEmail/{id}")
    public Supplier updateByEmail(@PathVariable("id") String supplierEmail,@RequestBody Supplier supplier){
        return supplierService.updateByEmail(supplierEmail,supplier);
    }
}
