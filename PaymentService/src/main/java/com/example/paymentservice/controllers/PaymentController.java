package com.example.paymentservice.controllers;

import com.example.paymentservice.dtos.InitiatePaymentRequestDto;
import com.example.paymentservice.services.PaymentService;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.model.EventDataObjectDeserializer;
import com.stripe.model.StripeObject;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/")
    public String initiatePayment(InitiatePaymentRequestDto request) throws StripeException, RazorpayException {
        return paymentService.initiatePayment("1", "123", "abc@x.com");
    }

    @PostMapping("/webhooks")
    public void webhookInvoked(@RequestBody Event event) {
        System.out.println(event.getId());
        System.out.println("Hi");
        //EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
//        StripeObject stripeObject = null;
//        if (dataObjectDeserializer.getObject().isPresent()) {
//            stripeObject = dataObjectDeserializer.getObject().get();
//        } else {
//            System.out.println("Deseralization Failed");
//        }
        // Handle the event
        switch (event.getType()) {
            case "product.created": {
                System.out.println("A product has been created");
                break;
            }
            case "payment_link.created": {
                System.out.println("A payment link has been created");
                break;
            }
            default: {
                LocalDateTime currentTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
                String formattedTime = currentTime.format(formatter);
                System.out.println("None of the above events have been triggered"+event.getType()+"  "+formattedTime);
            }
        }
    }
}
