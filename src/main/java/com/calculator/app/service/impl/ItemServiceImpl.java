package com.calculator.app.service.impl;

import com.calculator.app.model.Customer;
import com.calculator.app.model.Item;
import com.calculator.app.repository.CustomerRepository;
import com.calculator.app.repository.ItemRepository;
import com.calculator.app.request.GenerateBill;
import com.calculator.app.request.ItemRequest;
import com.calculator.app.service.ItemService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    private static final Logger logger = LoggerFactory.getLogger(ItemServiceImpl.class);

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public ResponseEntity<Object> generateBill(Long customerId, GenerateBill requestBody) {
        Optional<Customer> customerData = customerRepository.findById(customerId);
        if (customerData.isPresent()) {
            Customer customer = customerData.get();
            double totalCost = 0;
            for (ItemRequest request : requestBody.getPurchasedItemsData()) {
                if (request.getQuantity() > 1) {
                    totalCost = totalCost + (request.getQuantity() * request.getCost());
                } else {
                    totalCost = totalCost + request.getCost();
                }
            }
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonPayload = "";
            try {
                jsonPayload = objectMapper.writeValueAsString(requestBody);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            Item item = new Item();
            item.setJsonData(jsonPayload);
            item.setDate(LocalDate.now());
            item.setTotalBilledAmount(totalCost);
            item.setCustomer(customer);
            item.setPaymentStatus(requestBody.getPaymentStatus());
            Item savedItem = itemRepository.save(item);
            // CODE FOR SENDING EMAIL TO BOTH SHOPKEEPER & CUSTOMER WHICH WILL HAVE ITEM OBJECT IN BODY
            return ResponseEntity.status(HttpStatus.CREATED).body(savedItem);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customerId + " not found in database, Please create a user and then generate the bill.");
        }
    }

    @Override
    public ResponseEntity<Object> getTotalBillAmountForRequestedDate(LocalDate requestedDate) {
        Double totalBillAmountFroRequestedDate = itemRepository.getTotalBillAmountForRequestedDate(requestedDate);
        return ResponseEntity.ok("Total billed amount for " + requestedDate + " : " + totalBillAmountFroRequestedDate);
    }

    @Override
    public ResponseEntity<Object> getDueTransactionsData() {
        List<Item> dueTransactionsData = itemRepository.getDueTransactionsData();
        Map<Long, List<Item>> customerIdDueDataMap = dueTransactionsData.stream().collect(Collectors.groupingBy(item -> item.getCustomer().getId()));
        if (!customerIdDueDataMap.isEmpty()) {
            Map<String, Double> finalResponse = new LinkedHashMap<>();
            for (Map.Entry<Long, List<Item>> entry : customerIdDueDataMap.entrySet()) {
                double totalDueAmount = 0;
                for (Item val : entry.getValue()) {
                    totalDueAmount = totalDueAmount + val.getTotalBilledAmount();
                }
                Optional<Customer> customer = customerRepository.findById(entry.getKey());
                if (customer.isPresent()) {
                    finalResponse.put(customer.get().getName(), totalDueAmount);
                }
            }
            return ResponseEntity.ok(finalResponse);
        } else {
            logger.info("No due transactions present in database.");
            return ResponseEntity.ok("No due transactions found in database.");
        }
    }

}
