package com.djox.ps.api.service;

import com.djox.ps.api.entity.Payment;
import com.djox.ps.api.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Random;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment doPayment(Payment payment){
        payment.setPaymentStatus(paymentProcessing());
        payment.setTransactionId(UUID.randomUUID().toString());
        return paymentRepository.save(payment);
    }

    public String paymentProcessing(){
        //api should be 3rd party payment gateway (paypal ...)
        return  new Random().nextBoolean()?"success":"false";
    }

    public Payment findPaymentByOrderId(int orderId) {
        return paymentRepository.findByOrderId(orderId);
    }
}
