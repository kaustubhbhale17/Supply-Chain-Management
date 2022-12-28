package com.supplychainmanagement.retailer.service;


import com.supplychainmanagement.retailer.entity.Retailer;
import com.supplychainmanagement.retailer.repository.RetailerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RetailerService {
    @Autowired
    private RetailerRepository retailerRepository;

    public RetailerService(RetailerRepository retailerRepository) {
        this.retailerRepository = retailerRepository;
    }

    public Retailer saveRetailer(Retailer retailer) { return retailerRepository.save(retailer); }

    public List<Retailer> getAllRetailers() { return retailerRepository.findAll(); }

    public Retailer updateRetailer(String retailerId, Retailer retailer) {
        Retailer retailer1 = getRetailerWithRetailerId(retailerId);
        if(retailer != null){
            retailer1.setRetailerId(retailer.getRetailerId());
            retailer1.setRetailerName(retailer.getRetailerName());
            retailer1.setRetailerCompany(retailer.getRetailerCompany());
            retailer1.setRetailerEmail(retailer.getRetailerEmail());
            retailer1.setRetailerPassword(retailer.getRetailerPassword());
            retailer1.setRetailerContactNumber(retailer.getRetailerContactNumber());
        }
        retailerRepository.save(retailer1);
        return retailer1;

    }

    public Retailer getRetailerWithRetailerId(String retailerId) {
        List<Retailer> retailers = getAllRetailers();
        for (Retailer retailer : retailers){
            if(retailer.getRetailerId().equals(retailerId)){
                return retailer;
            }
        }
        return null;
    }

    public ResponseEntity<HttpStatus> deleteRetailerWithId(String retailerId) {
        Retailer retailer = getRetailerWithRetailerId(retailerId);
        if(retailer!=null){
            retailerRepository.delete(retailer);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public Retailer signIn(Retailer retailer) {
        List<Retailer> retailers = getAllRetailers();

        for(Retailer r : retailers){
            if(r.getRetailerEmail().equals(retailer.getRetailerEmail()) &&
            r.getRetailerPassword().equals(retailer.getRetailerPassword())){
                System.out.println("Retailer found!");
                return r;
            }
        }
        System.out.println("Invalid credentials");
        return null;
    }


    public Retailer findByEmail(String email) {
        List<Retailer> retailers = getAllRetailers();
        for(Retailer r : retailers){
            if(r.getRetailerEmail().equals(email)){
                return r;
            }
        }
        return null;
    }

    public Retailer updateByEmail(String retailerEmail, Retailer retailer) {
        Retailer retailer1 = findByEmail(retailerEmail);
        if(retailer1!=null){
            retailer1.setRetailerId(retailer.getRetailerId());
            retailer1.setRetailerName(retailer.getRetailerName());
            retailer1.setRetailerCompany(retailer.getRetailerCompany());
            retailer1.setRetailerEmail(retailer.getRetailerEmail());
            retailer1.setRetailerPassword(retailer.getRetailerPassword());
            retailer1.setRetailerContactNumber(retailer.getRetailerContactNumber());

            retailerRepository.save(retailer1);
            return retailer1;
        }
        return null;
    }
}
