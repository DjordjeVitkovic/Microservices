package com.djox.os.api.service;

import com.djox.os.api.common.Payment;
import com.djox.os.api.common.TransactionRequest;
import com.djox.os.api.common.TransactionResponse;
import com.djox.os.api.entity.Order;
import com.djox.os.api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RefreshScope
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    @Lazy
    private RestTemplate restTemplate;


//    @Value("${microservice.payment-service.endpoints.endpoint.uri}")
    private String ENDPOINT_URL;

    public TransactionResponse saveOrder(TransactionRequest request) {

        String responseMsg = "";
        Order order = request.getOrder();
        Payment payment = request.getPayment();
        payment.setOrderId(order.getId());
        payment.setAmount(order.getPrice());
        //rest call
        Payment paymentResponse = restTemplate.postForObject("http://localhost:9191/payment/doPaymant", payment, Payment.class);

        responseMsg = (paymentResponse.getPaymentStatus().equalsIgnoreCase(
                "success") ? "payment processing successful and order placed" : "there is failure in payment api, order added to cart");

        orderRepository.save(order);

        return new TransactionResponse(order, paymentResponse.getTransactionId(), paymentResponse.getAmount(), responseMsg);
    }

}
