package com.calculator.app.controller;

import com.calculator.app.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Operation(summary = "Retrieves list of customers data present under requested shopkeeper.")
    @GetMapping(value = "/customersUnder/{shopkeeperId}", produces = "application/json")
    public ResponseEntity<Object> getCustomersPresentUnderRequestedShopkeeper(@PathVariable Long shopkeeperId) {
        return adminService.getCustomersPresentUnderRequestedShopkeeper(shopkeeperId);
    }

}
