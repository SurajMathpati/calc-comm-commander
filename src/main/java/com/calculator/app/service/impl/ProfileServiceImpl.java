package com.calculator.app.service.impl;

import com.calculator.app.model.Customer;
import com.calculator.app.model.Shopkeeper;
import com.calculator.app.repository.CustomerRepository;
import com.calculator.app.repository.ShopkeeperRepository;
import com.calculator.app.request.CreateCustomerRequest;
import com.calculator.app.service.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {

    private static final Logger logger = LoggerFactory.getLogger(ProfileServiceImpl.class);

    @Autowired
    private ShopkeeperRepository shopkeeperRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public ResponseEntity<Object> createShopkeeper(Shopkeeper shopkeeper) {
        Shopkeeper createdShopkeeper = shopkeeperRepository.save(shopkeeper);
        logger.info("Shopkeeper created successfully: {}", createdShopkeeper);
        // CODE FOR SENDING EMAIL ACCOUNT CREATED EMAIL TO BOTH CUSTOMER AND SHOPKEEPER
        return ResponseEntity.status(HttpStatus.CREATED).body(createdShopkeeper);
    }

    @Override
    public ResponseEntity<Object> getAllShopkeepers() {
        List<Shopkeeper> shopkeepersList = shopkeeperRepository.findAll();
        logger.info("Shopkeepers list: {}", shopkeepersList);
        return ResponseEntity.ok(shopkeepersList);
    }

    @Override
    public ResponseEntity<Optional<Shopkeeper>> getShopkeeperById(Long id) {
        Optional<Shopkeeper> shopkeeper = shopkeeperRepository.findById(id);
        return ResponseEntity.ok(shopkeeper);
    }

    @Override
    public ResponseEntity<Object> updateShopName(Long id, Shopkeeper shopkeeperBody) {
        Optional<Shopkeeper> shopkeeper = shopkeeperRepository.findById(id);
        if (shopkeeper.isPresent()) {
            Shopkeeper existingShopkeeper = shopkeeper.get();
            existingShopkeeper.setShopName(shopkeeperBody.getShopName());
            Shopkeeper savedShopkeeperData = shopkeeperRepository.save(existingShopkeeper);
            logger.info("Shop name updated successfully: {}", savedShopkeeperData);
            return ResponseEntity.ok(savedShopkeeperData);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id + " not found in database.");
    }

    @Override
    public ResponseEntity<Object> createCustomer(CreateCustomerRequest requestBody) {
        Long shopkeeperId = requestBody.getShopkeeperId();
        Shopkeeper shopkeeper = shopkeeperRepository.findById(shopkeeperId).orElseThrow(() -> new RuntimeException("Shopkeeper not found"));

        Customer customer = new Customer();
        customer.setName(requestBody.getName());
        customer.setEmail(requestBody.getEmail());
        customer.setMobileNumber(requestBody.getMobileNumber());
        customer.setAddress(requestBody.getAddress());
        customer.setOccupation(requestBody.getOccupation());
        customer.setShopkeeper(shopkeeper);
        Customer createdCustomer = customerRepository.save(customer);
        logger.info("Customer created successfully: {}", createdCustomer);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }

    @Override
    public ResponseEntity<Object> getAllCustomersData() {
        List<Customer> customersList = customerRepository.findAll();
        logger.info("Customers list : {}", customersList);
        return ResponseEntity.ok(customersList);
    }

}
