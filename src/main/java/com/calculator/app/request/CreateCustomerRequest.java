package com.calculator.app.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerRequest {

    private String name;
    private String email;
    private String mobileNumber;
    private String address;
    private String occupation;
    private Long shopkeeperId;

}
