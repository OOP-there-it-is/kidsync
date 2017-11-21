package com.codeup.kidsync.twillio;

import com.codeup.kidsync.Config;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;



public class SendSms  {



    // Find your Account Sid and Token at twilio.com/user/account


    public static void main(String[] args) {
        Twilio.init(Config.getAccountSid(), Config.getAuthToken());

        Message message = Message.creator(
                new PhoneNumber("+14029577924"),
                new PhoneNumber("+17123555738"),
                "test"
        ).create();
    }
}