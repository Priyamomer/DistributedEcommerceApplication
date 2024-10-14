package com.example.paymentservice.paymentgateway;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service("RazorPayPG")
public class RazorPayPaymentGateway implements PaymentGateway{
    @Override
    public String generatePaymentLink(String orderId, Long amount, String phoneNumber, String email) throws RazorpayException {
        RazorpayClient razorpay = new RazorpayClient("rzp_test_O2C35TyJA9aq7M", "VIv7CgMJGykoAY8vETqxB386");
        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount",1000);
//        paymentLinkRequest.put("currency","INR");
//        paymentLinkRequest.put("accept_partial",true);
//        paymentLinkRequest.put("first_min_partial_amount",100);
//        //paymentLinkRequest.put("expire_by",1691097057);
//        //paymentLinkRequest.put("reference_id","TS19811");
//        paymentLinkRequest.put("description","Payment for policy no #23456");
//        JSONObject customer = new JSONObject();
//        customer.put("name","+919000090000");
//        customer.put("contact","Gaurav Kumar");
//        customer.put("email","gaurav.kumar@example.com");
//        paymentLinkRequest.put("customer",customer);
//        JSONObject notify = new JSONObject();
//        notify.put("sms",true);
//        notify.put("email",true);
//        paymentLinkRequest.put("notify",notify);
//        paymentLinkRequest.put("reminder_enable",true);
//        JSONObject notes = new JSONObject();
//        notes.put("policy_name","Jeevan Bima");
//        paymentLinkRequest.put("notes",notes);
//        paymentLinkRequest.put("callback_url","https://scaler.com/");
//        paymentLinkRequest.put("callback_method","get");

        PaymentLink payment = razorpay.paymentLink.create(paymentLinkRequest);
        JSONObject paymentJson= payment.toJson();
        return paymentJson.getString("short_url");
        //System.out.println(paymentJson);
        //System.out.println(payment.toString());

    }
}
