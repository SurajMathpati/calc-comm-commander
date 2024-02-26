package com.calculator.app.controller;

import com.calculator.app.request.GenerateBill;
import com.calculator.app.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private ItemService itemService;

    @Operation(summary = "Generates bill for customer.")
    @PostMapping(value = "/generateBill/{customerId}", produces = "application/json")
    public ResponseEntity<Object> generateBill(@PathVariable Long customerId, @RequestBody GenerateBill requestBody) {
        return itemService.generateBill(customerId, requestBody);
    }

    @Operation(summary = "Retrieves the total billed amount for requested date.")
    @GetMapping(value = "/getTotalBillAmountOfDay/{date}", produces = "application/json")
    public ResponseEntity<Object> getTotalBillAmountForRequestedDate(@PathVariable("date")
                                                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate requestedDate) {
        return itemService.getTotalBillAmountForRequestedDate(requestedDate);
    }

    @Operation(summary = "Retrieves the customer due transaction details.")
    @GetMapping(value = "/getDueTransactionsData", produces = "application/json")
    public ResponseEntity<Object> getDueTransactionsData() {
        return itemService.getDueTransactionsData();
    }

}
