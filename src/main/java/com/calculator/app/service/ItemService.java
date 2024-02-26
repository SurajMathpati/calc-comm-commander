package com.calculator.app.service;

import com.calculator.app.request.GenerateBill;
import com.calculator.app.request.ItemRequest;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface ItemService {

    ResponseEntity<Object> generateBill(Long customerId, GenerateBill requestBody);

    ResponseEntity<Object> getTotalBillAmountForRequestedDate(LocalDate requestedDate);

    ResponseEntity<Object> getDueTransactionsData();

}
