package com.ishnit.os.api.service;

import com.ishnit.os.api.common.Payment;
import com.ishnit.os.api.common.TransactionRequest;
import com.ishnit.os.api.common.TransactionResponse;
import com.ishnit.os.api.entity.Order;
import com.ishnit.os.api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    public TransactionResponse saveOrder(TransactionRequest request){
        String response = "";
        Order order = request.getOrder();
        Payment payment = request.getPayment();
        payment.setOrderId(order.getId());
        payment.setAmount(order.getPrice());

        Payment paymentResponse = restTemplate.postForObject("http://localhost:9191/payments",payment,Payment.class);
        response = paymentResponse.getPaymentStatus().equals("success")?"Payment Processing is Successful!":"Payment API failed in processing the payment!";

        orderRepository.save(order);
        return new TransactionResponse(order, paymentResponse.getAmount(),paymentResponse.getTransactionId(), response );
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public Order updateOrder(Order order){
        return orderRepository.save(order);
    }
}
