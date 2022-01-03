package com.ishnit.ps.api.controller;

import com.ishnit.ps.api.entity.Payment;
import com.ishnit.ps.api.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @PostMapping("/payments")
    public Payment addPayment(@RequestBody Payment payment){
        return paymentService.makePayment(payment);
    }
}
