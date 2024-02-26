package com.calculator.app.service;

import org.springframework.http.ResponseEntity;

public interface AdminService {

    ResponseEntity<Object> getCustomersPresentUnderRequestedShopkeeper(Long shopkeeperId);

}
