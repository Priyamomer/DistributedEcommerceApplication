package com.example.paymentservice.paymentgateway;


import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.model.Product;
import com.stripe.param.PaymentLinkCreateParams;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("StripePG")
public class StripePaymentGateway implements PaymentGateway{
    @Override
    public String generatePaymentLink(String orderId, Long amount, String phoneNumber, String email) throws StripeException {
        Stripe.apiKey = "";

//        Price price=new Price();
//        price.setCurrency("INR");
//        price.setProduct("1");
//        price.setUnitAmount(100L);
//        price.setType("one-time");
//
//        PaymentLinkCreateParams params =
//                PaymentLinkCreateParams.builder()
//                        .addLineItem(
//                                PaymentLinkCreateParams.LineItem.builder()
//                                        .setPrice(String.valueOf(price))
//                                        .setQuantity(1L)
//                                        .build()
//                        )
//                        .build();
//        PaymentLink paymentLink = PaymentLink.create(params);
//        System.out.println("Invoked");
//        System.out.println(paymentLink.toString());
        Map<String,Object> priceData=new HashMap<>();
        priceData.put("unit_amount",amount);
        priceData.put("currency","inr");

        Map<String,Object> productData=new HashMap<>();
        productData.put("name","iphone");
        productData.put("description","This is latest tech we are offering");

        //priceData.put("product_data",productData);
        Product product=Product.create(productData);
        priceData.put("product",product.getId());

        Price price=Price.create(priceData);

        Map<String,Object> lintItem1=new HashMap<>();

        lintItem1.put("price",price.getId());
        lintItem1.put("quantity",5);

        Map<String, Object> afterPayment = new HashMap<>();
        afterPayment.put("type", "redirect");

        Map<String, Object> redirect = new HashMap<>(); //lineItem2
        redirect.put("url", "https://scaler.com/");
        afterPayment.put("redirect", redirect);

        List<Object> lineItems = new ArrayList<>();
        lineItems.add(lintItem1);

        Map<String, Object> params = new HashMap<>();
        params.put("line_items", lineItems);
        params.put("after_completion", afterPayment);

        PaymentLink paymentLink = PaymentLink.create(params);
        return paymentLink.getUrl();
    }
}
