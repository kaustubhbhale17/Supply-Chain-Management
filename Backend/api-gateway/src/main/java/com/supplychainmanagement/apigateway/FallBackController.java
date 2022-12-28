package com.supplychainmanagement.apigateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackController {

    @GetMapping("/supplierServiceFallback")
    public String supplierServiceFallback(){
        return "supplier service is currently down";
    }

    @GetMapping("/orderServiceFallback")
    public String orderServiceFallback(){
        return "document service is currently down";
    }

    @GetMapping("/inventoryServiceFallback")
    public String inventoryServiceFallback(){
        return "document service is currently down";
    }

    @GetMapping("/retailerServiceFallback")
    public String retailerServiceFallback(){
        return "document service is currently down";
    }


}
