package com.calculator.app.service;

import com.calculator.app.model.Shopkeeper;
import com.calculator.app.request.CreateCustomerRequest;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface ProfileService {

    ResponseEntity<Object> createShopkeeper(Shopkeeper shopkeeper);

    ResponseEntity<Object> getAllShopkeepers();

    ResponseEntity<Optional<Shopkeeper>> getShopkeeperById(Long id);

    ResponseEntity<Object> updateShopName(Long id, Shopkeeper shopkeeperBody);

    ResponseEntity<Object> createCustomer(CreateCustomerRequest requestBody);

    ResponseEntity<Object> getAllCustomersData();

}
