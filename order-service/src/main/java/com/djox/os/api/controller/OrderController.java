package com.djox.os.api.controller;

import com.djox.os.api.common.TransactionRequest;
import com.djox.os.api.common.TransactionResponse;
import com.djox.os.api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RefreshScope
public class OrderController {

    @Autowired
    private OrderService orderService;

//    @Value("${microservice.payment-service.endpoints.endpoint.uri}")
    private String ENDPOINT_URL = "TEEEEEST";

    @PostMapping("/bookOrder")
    public TransactionResponse bookOrder(@RequestBody TransactionRequest request){

        return orderService.saveOrder(request);
    }

    @GetMapping("/test")
    public String getPropertyValue(){
        return ENDPOINT_URL;
    }
}
