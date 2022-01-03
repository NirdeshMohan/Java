package com.ishnit.os.api.controller;

import com.ishnit.os.api.common.Payment;
import com.ishnit.os.api.common.TransactionRequest;
import com.ishnit.os.api.common.TransactionResponse;
import com.ishnit.os.api.service.OrderService;
import com.ishnit.os.api.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController

public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/orders")
    public TransactionResponse orderBook(@RequestBody TransactionRequest request){
        return orderService.saveOrder(request);
    }
}
