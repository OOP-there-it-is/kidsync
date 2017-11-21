package com.codeup.kidsync.twillio;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SendSms  {

    public Message sendCode(String phone){
        int msg = (int) (Math.random() * 5000 + 2000);
         String newMsg = Integer.toString(msg);

        Message message = Message.creator(
                new PhoneNumber("+1" + phone),
                new PhoneNumber("+17123555738"),
                newMsg
        ).create();

        return message;
    }
}
