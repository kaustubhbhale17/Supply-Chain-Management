package com.supplychainmanagment.inventory.util;

import com.supplychainmanagment.inventory.entity.Product;
import org.springframework.web.client.RestTemplate;

public class DataLoader {
    public void loadData(){

//        //int count = 1;
//
        Product product = new Product();
        RestTemplate restTemplate = new RestTemplate();

//        Data --------------------- Data
        product.setProductName("SURF (1KG)");
        product.setProductQuantity(200);
        product.setProductType("CLEANING");
        product.setProductPrice(250.00);
        restTemplate.postForObject("http://localhost:3003/inventory/add",product,Product.class);
        System.out.println("Data Loaded !!!");

        product.setProductName("SURF (5KG)");
        product.setProductQuantity(50);
        product.setProductType("CLEANING");
        product.setProductPrice(1200.00);
        restTemplate.postForObject("http://localhost:3003/inventory/add",product,Product.class);
        System.out.println("Data Loaded !!!");

        product.setProductName("ARIEL (1KG)");
        product.setProductQuantity(180);
        product.setProductType("CLEANING");
        product.setProductPrice(230.00);
        restTemplate.postForObject("http://localhost:3003/inventory/add",product,Product.class);
        System.out.println("Data Loaded !!!");

        product.setProductName("ARIEL (5KG)");
        product.setProductQuantity(60);
        product.setProductType("CLEANING");
        product.setProductPrice(1100.00);
        restTemplate.postForObject("http://localhost:3003/inventory/add",product,Product.class);
        System.out.println("Data Loaded !!!");

        product.setProductName("ARIEL (10KG)");
        product.setProductQuantity(30);
        product.setProductType("CLEANING");
        product.setProductPrice(2100.00);
        restTemplate.postForObject("http://localhost:3003/inventory/add",product,Product.class);
        System.out.println("Data Loaded !!!");

        product.setProductName("PAPER TOWELS");
        product.setProductQuantity(100);
        product.setProductType("CLEANING");
        product.setProductPrice(120.00);
        restTemplate.postForObject("http://localhost:3003/inventory/add",product,Product.class);
        System.out.println("Data Loaded !!!");

        product.setProductName("TOILET PAPER");
        product.setProductQuantity(120);
        product.setProductType("CLEANING");
        product.setProductPrice(150.00);
        restTemplate.postForObject("http://localhost:3003/inventory/add",product,Product.class);
        System.out.println("Data Loaded !!!");

        product.setProductName("DIAPER (PACK OF 20)");
        product.setProductQuantity(200);
        product.setProductType("CLEANING");
        product.setProductPrice(600.00);
        restTemplate.postForObject("http://localhost:3003/inventory/add",product,Product.class);
        System.out.println("Data Loaded !!!");

        product.setProductName("WIPES");
        product.setProductQuantity(100);
        product.setProductType("CLEANING");
        product.setProductPrice(80.00);
        restTemplate.postForObject("http://localhost:3003/inventory/add",product,Product.class);
        System.out.println("Data Loaded !!!");

        product.setProductName("BROWN BREAD");
        product.setProductQuantity(40);
        product.setProductType("BAKERY");
        product.setProductPrice(32.00);
        restTemplate.postForObject("http://localhost:3003/inventory/add",product,Product.class);
        System.out.println("Data Loaded !!!");

        product.setProductName("WHITE BREAD");
        product.setProductQuantity(45);
        product.setProductType("BAKERY");
        product.setProductPrice(30.00);
        restTemplate.postForObject("http://localhost:3003/inventory/add",product,Product.class);
        System.out.println("Data Loaded !!!");

        product.setProductName("BROWNIE MIX");
        product.setProductQuantity(60);
        product.setProductType("BAKERY");
        product.setProductPrice(63.00);
        restTemplate.postForObject("http://localhost:3003/inventory/add",product,Product.class);
        System.out.println("Data Loaded !!!");

        product.setProductName("KELLOGS CHOCOS (1KG)");
        product.setProductQuantity(40);
        product.setProductType("CEREAL");
        product.setProductPrice(230.00);
        restTemplate.postForObject("http://localhost:3003/inventory/add",product,Product.class);
        System.out.println("Data Loaded !!!");

        product.setProductName("KELLOGS CORNFLAKES (1KG)");
        product.setProductQuantity(40);
        product.setProductType("CEREAL");
        product.setProductPrice(180.00);
        restTemplate.postForObject("http://localhost:3003/inventory/add",product,Product.class);
        System.out.println("Data Loaded !!!");

        product.setProductName("LAYS (S)");
        product.setProductQuantity(300);
        product.setProductType("SNACKS");
        product.setProductPrice(20.00);
        restTemplate.postForObject("http://localhost:3003/inventory/add",product,Product.class);
        System.out.println("Data Loaded !!!");

        product.setProductName("LAYS (L)");
        product.setProductQuantity(200);
        product.setProductType("SNACKS");
        product.setProductPrice(80.00);
        restTemplate.postForObject("http://localhost:3003/inventory/add",product,Product.class);
        System.out.println("Data Loaded !!!");

        product.setProductName("KURKURE");
        product.setProductQuantity(350);
        product.setProductType("SNACKS");
        product.setProductPrice(20.00);
        restTemplate.postForObject("http://localhost:3003/inventory/add",product,Product.class);
        System.out.println("Data Loaded !!!");

        product.setProductName("BALAJI CAO");
        product.setProductQuantity(200);
        product.setProductType("SNACKS");
        product.setProductPrice(20.00);
        restTemplate.postForObject("http://localhost:3003/inventory/add",product,Product.class);
        System.out.println("Data Loaded !!!");

        product.setProductName("UNIBIC COOKIES");
        product.setProductQuantity(150);
        product.setProductType("BISCUITS");
        product.setProductPrice(30.00);
        restTemplate.postForObject("http://localhost:3003/inventory/add",product,Product.class);
        System.out.println("Data Loaded !!!");

        product.setProductName("GOODDAY COOKIES");
        product.setProductQuantity(100);
        product.setProductType("BISCUITS");
        product.setProductPrice(25.00);
        restTemplate.postForObject("http://localhost:3003/inventory/add",product,Product.class);
        System.out.println("Data Loaded !!!");

        product.setProductName("HIDE N SEEK");
        product.setProductQuantity(200);
        product.setProductType("BISCUITS");
        product.setProductPrice(20);
        restTemplate.postForObject("http://localhost:3003/inventory/add",product,Product.class);
        System.out.println("Data Loaded !!!");

        product.setProductName("CHOCOPIE");
        product.setProductQuantity(100);
        product.setProductType("SNACKS");
        product.setProductPrice(180.00);
        restTemplate.postForObject("http://localhost:3003/inventory/add",product,Product.class);
        System.out.println("Data Loaded !!!");

        product.setProductName("CADBURRY DAIRYMILK SILK");
        product.setProductQuantity(200);
        product.setProductType("CHOCOLATE");
        product.setProductPrice(120.00);
        restTemplate.postForObject("http://localhost:3003/inventory/add",product,Product.class);
        System.out.println("Data Loaded !!!");

        product.setProductName("CADBURRY DAIRYMILK");
        product.setProductQuantity(150);
        product.setProductType("CHOCOLATE");
        product.setProductPrice(25.00);
        restTemplate.postForObject("http://localhost:3003/inventory/add",product,Product.class);
        System.out.println("Data Loaded !!!");

        product.setProductName("CADBURRY GEMS");
        product.setProductQuantity(300);
        product.setProductType("CHOCOLATE");
        product.setProductPrice(15.00);
        restTemplate.postForObject("http://localhost:3003/inventory/add",product,Product.class);
        System.out.println("Data Loaded !!!");

        product.setProductName("SNICKERS");
        product.setProductQuantity(150);
        product.setProductType("CHOCOLATE");
        product.setProductPrice(20.00);
        restTemplate.postForObject("http://localhost:3003/inventory/add",product,Product.class);
        System.out.println("Data Loaded !!!");

        product.setProductName("KISSAN KETCHUP");
        product.setProductQuantity(200);
        product.setProductType("DAIRY");
        product.setProductPrice(115.00);
        restTemplate.postForObject("http://localhost:3003/inventory/add",product,Product.class);
        System.out.println("Data Loaded !!!");

        product.setProductName("KISSAN JAM");
        product.setProductQuantity(100);
        product.setProductType("DAIRY");
        product.setProductPrice(160.00);
        restTemplate.postForObject("http://localhost:3003/inventory/add",product,Product.class);
        System.out.println("Data Loaded !!!");

        product.setProductName("AMUL BUTTER");
        product.setProductQuantity(300);
        product.setProductType("DAIRY");
        product.setProductPrice(80.00);
        restTemplate.postForObject("http://localhost:3003/inventory/add",product,Product.class);
        System.out.println("Data Loaded !!!");

        product.setProductName("NUTELLA");
        product.setProductQuantity(120);
        product.setProductType("SPREADS");
        product.setProductPrice(210.00);
        restTemplate.postForObject("http://localhost:3003/inventory/add",product,Product.class);
        System.out.println("Data Loaded !!!");

        product.setProductName("VEBA PACK OF 5");
        product.setProductQuantity(50);
        product.setProductType("SPREADS");
        product.setProductPrice(300.00);
        restTemplate.postForObject("http://localhost:3003/inventory/add",product,Product.class);
        System.out.println("Data Loaded !!!");

    }
}
