package com.supplychainmanagement.retailer.controller;


import com.supplychainmanagement.retailer.entity.Retailer;
import com.supplychainmanagement.retailer.service.RetailerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/retailer")
@CrossOrigin(origins = "*")
public class RetailerController {
    @Autowired
    private RetailerService retailerService;

    @PostMapping("/add")
    public Retailer saveRetailer(@RequestBody Retailer retailer) {
        return retailerService.saveRetailer(retailer);
    }
    @GetMapping("/findall")
    public List<Retailer> getAllRetailers(){ return retailerService.getAllRetailers(); }
    @GetMapping("/find/{id}")
    public Retailer getRetailerWithRetailerId(@PathVariable("id") String retailerId){ return retailerService.getRetailerWithRetailerId(retailerId); }
    @PutMapping("/updateRetailer/{id}")
    public Retailer updateRetailer(@PathVariable("id") String retailerId, @RequestBody Retailer retailer){
        return retailerService.updateRetailer(retailerId,retailer);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteRetailerWithId(@PathVariable("id") String retailerId){
        return retailerService.deleteRetailerWithId(retailerId);
    }
    @PostMapping("/signin")
    public Retailer signIn(@RequestBody Retailer retailer){
        return retailerService.signIn(retailer);
    }

    @GetMapping("/findByEmail/{id}")
    public Retailer findByEmail(@PathVariable("id") String email){
        return retailerService.findByEmail(email);
    }

    @PutMapping("/updateByEmail/{id}")
    public Retailer updateByEmail(@PathVariable("id") String retailerEmail,@RequestBody Retailer retailer){
        return retailerService.updateByEmail(retailerEmail,retailer);
    }

}
