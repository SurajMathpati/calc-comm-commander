package com.calculator.app.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenerateBill {

    private String paymentStatus;
    private List<ItemRequest> purchasedItemsData;

}
