package com.supplychainmanagment.inventory.service;

import com.supplychainmanagment.inventory.entity.Order;
import com.supplychainmanagment.inventory.entity.Product;
import com.supplychainmanagment.inventory.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RestTemplate restTemplate;


    public ProductService(ProductRepository productRepository, RestTemplate restTemplate) {
        this.productRepository = productRepository;
        this.restTemplate = restTemplate;
    }

    public Product addProduct(Product product) {
        List<Product> products = listAllProducts();
        for(Product p : products){
            if(p.getProductName().equalsIgnoreCase(product.getProductName())){
                System.out.println("Product Already exists!");
                return null;
            }
        }
        return productRepository.save(product);
    }

    public List<Product> listAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductByName(String productName) {
        List<Product> products = listAllProducts();
        for(Product product: products){
            if(product.getProductName().equals(productName)){
                System.out.println("Product Found!!");
                return product;
            }
        }
        System.out.println("No Product Found! ");
        return null;
    }

    public Product updateProductByName(String productName, Product productData) {
        Product product = getProductByName(productName);
        if(product!=null){
            product.setProductName(productData.getProductName());
            product.setProductType(productData.getProductType());
            product.setProductQuantity(productData.getProductQuantity());
            product.setProductPrice(productData.getProductPrice());

            productRepository.save(product);
            return product;
        }
        System.out.println("No Product Found!");
        return null;
    }

    public ResponseEntity<HttpStatus> deleteProductByName(String productName) {
        Product product = getProductByName(productName);
        if(product!=null){
            productRepository.delete(product);
            System.out.println("Product Deleted!");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public List<Order> getAllOrders() {
        List<Order> orders = restTemplate.getForObject("http://localhost:3004/retailer/allOrders",List.class);
        //System.out.println(orders);
        return orders;
    }

    public Order acceptRejectOrder(String orderNumber) {
        double bill = 0.0;
        boolean flag=true;

        Order order = restTemplate.getForObject("http://localhost:3004/order/getorderbyid/"+orderNumber, Order.class);
        List<Product> productList = order.getCartItems();
        List<Product> allProducts = listAllProducts();


        for(Product product : allProducts){
            for(Product p : productList){
                if(p.getProductName().equals(product.getProductName())){
                    if(p.getProductQuantity()>product.getProductQuantity()){
                        System.out.println("Insufficient Quantity Of "+p.getProductName());
                        order.setOrderStatus("Rejected");
                        flag=false;
                    }else{
                        bill = bill+(p.getProductQuantity()*product.getProductPrice());
                        int originalQuantity = product.getProductQuantity();
                        int requestedQuantity = p.getProductQuantity();
                        int leftQuantityAfterSale = originalQuantity-requestedQuantity;

                        //changing the product quantity in inventory
                        System.out.println("Original "+product);
                        String updatedProduct = product.getProductName();
                        product.setProductQuantity(leftQuantityAfterSale);
                        updateProductByName(updatedProduct,product);

                        System.out.println(originalQuantity+" "+requestedQuantity+" "+leftQuantityAfterSale);
                    }

                }
            }
        }
        if(!flag){
            order.setOrderStatus("Rejected!");
            order.setOrderBill(0.0);
            //update order in order collection
            restTemplate.put("http://localhost:3004/order/update/"+orderNumber,order);
        }else{
            order.setOrderStatus("Request Accepted ! ");
            order.setOrderBill(bill);
            System.out.println("Total Billing is : "+bill);
            //update order in order collection
            restTemplate.put("http://localhost:3004/order/update/"+orderNumber,order);
        }
        return order;
    }


}
