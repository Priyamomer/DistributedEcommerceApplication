package com.example.notificationservice.services;

import com.twilio.Twilio;
import org.springframework.stereotype.Service;

@Service
public class TwilioMessageService implements SmsService {
    public static final String ACCOUNT_SID = "";
    public static final String AUTH_TOKEN = "";
    public String sendMessage(String phoneNumber,String message){
//        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//        com.twilio.rest.api.v2010.account.Message messageTwilio = com.twilio.rest.api.v2010.account.Message.creator(
//                        new com.twilio.type.PhoneNumber(phoneNumber),
//                        new com.twilio.type.PhoneNumber("+12542218970"),
//                        message)
//                .create();
//        return (messageTwilio.getSid());
        return "Twilio : Message has been send --> "+message;
    }
}
