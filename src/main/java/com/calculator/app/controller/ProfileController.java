package com.calculator.app.controller;

import com.calculator.app.model.Shopkeeper;
import com.calculator.app.request.CreateCustomerRequest;
import com.calculator.app.service.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @Operation(summary = "Creates a new shopkeeper.")
    @PostMapping(value = "/createShopkeeper", produces = "application/json")
    public ResponseEntity<Object> createShopKeeper(@RequestBody Shopkeeper shopkeeper) {
        return profileService.createShopkeeper(shopkeeper);
    }

    @Operation(summary = "Retrieves all the shopkeepers data.")
    @GetMapping(value = "/getAllShopkeepers", produces = "application/json")
    public ResponseEntity<Object> getAllShopkeepers() {
        return profileService.getAllShopkeepers();
    }

    @Operation(summary = "Retrieves data for requested shopkeeper.")
    @GetMapping(value = "/getShopkeeper/{id}", produces = "application/json")
    public ResponseEntity<Optional<Shopkeeper>> getShopkeeperById(@PathVariable Long id) {
        return profileService.getShopkeeperById(id);
    }

    @Operation(summary = "Updated shop name for requested shopkeeper.")
    @PutMapping(value = "/updateShopName/{id}", produces = "application/json")
    public ResponseEntity<Object> updateShopName(@PathVariable Long id, @RequestBody Shopkeeper shopkeeper) {
        return profileService.updateShopName(id, shopkeeper);
    }

    @Operation(summary = "Creates a new customer.")
    @PostMapping(value = "/createCustomer", produces = "application/json")
    public ResponseEntity<Object> createCustomer(@RequestBody CreateCustomerRequest requestBody) {
        return profileService.createCustomer(requestBody);
    }

    @Operation(summary = "Retrieves all the customers data for requested shopkeeper.")
    @GetMapping(value = "/getAllCustomersData", produces = "application/json")
    public ResponseEntity<Object> getAllCustomersData() {
        return profileService.getAllCustomersData();
    }

}
