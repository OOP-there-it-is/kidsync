package com.codeup.kidsync.twillio;



import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SendSms  {

    private final String ACCOUNT_SID = "AC0e986420ee946e8fdc19fb13e4cd7ca5";
    private final String AUTH_TOKEN = "6b633110ae4416ca37042c59ae05ab58";

    public void sendCode(String phone){

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        int msg = (int) (Math.random() * 5000 + 2000);
         String newMsg = Integer.toString(msg);

        Message message = Message.creator(
                new PhoneNumber("+1" + phone),
                new PhoneNumber("+17123555738"), "Here is your verification code! " +
                newMsg
        ).create();
    }
}
