package com.example.paymentservice.services;

import com.example.paymentservice.paymentgateway.PaymentGateway;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
// https://kinsta.com/blog/stripe-java-api/
@Service
public class PaymentService {
    PaymentGateway paymentGateway;
    @Autowired
    public PaymentService(@Qualifier("RazorPayPG") PaymentGateway paymentGateway){
        this.paymentGateway=paymentGateway;
    }

    public String initiatePayment(String orderId,String phoneNumber,String email) throws StripeException, RazorpayException {
        Long amount= 100L;
        return paymentGateway.generatePaymentLink(orderId,amount,phoneNumber,email);
    }
}
