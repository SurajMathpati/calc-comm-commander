package com.calculator.app.service.impl;

import com.calculator.app.model.Customer;
import com.calculator.app.model.Shopkeeper;
import com.calculator.app.repository.CustomerRepository;
import com.calculator.app.repository.ShopkeeperRepository;
import com.calculator.app.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Autowired
    private ShopkeeperRepository shopkeeperRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public ResponseEntity<Object> getCustomersPresentUnderRequestedShopkeeper(Long shopkeeperId) {
        Optional<Shopkeeper> shopkeeper = shopkeeperRepository.findById(shopkeeperId);
        if (shopkeeper.isPresent()) {
            Shopkeeper shopkeeperData = shopkeeper.get();
            List<Customer> customers = customerRepository.findByShopkeeperId(shopkeeperData.getId());
            logger.info("Customers present under shopkeeperId :{} are: {}", shopkeeperId, customers);
            return ResponseEntity.ok(customers);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(shopkeeperId + " not found in database.");
        }
    }

}
