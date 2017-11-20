package com.codeup.kidsync.twillio;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SendSms {

    // Find your Account Sid and Token at twilio.com/user/account
    public static final String ACCOUNT_SID = "AC0e986420ee946e8fdc19fb13e4cd7ca5";
    public static final String AUTH_TOKEN = "6b633110ae4416ca37042c59ae05ab58";

    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(
                new PhoneNumber("+14029577924"),
                new PhoneNumber("+17123555738"),
                "This is the ship that made the Kessel Run in fourteen parsecs?"
        ).create();


    }
}