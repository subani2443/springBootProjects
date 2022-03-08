package com.TejaITB2.Service;
import java.text.ParseException;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import com.TejaITB2.Model.OtpModel;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
@Component
public class OtpService {
     private final String ACCOUNT_SID ="AC588bbdae7a1b7ddfbbb0ecfaf53617c0";

    private final String AUTH_TOKEN = "8001ce5a8c35cf06c4a45a7022427512";

    private final String FROM_NUMBER = "+12284004428";

    public void send(OtpModel sms) throws ParseException {
    	Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
      
    	
        int min = 100000;  
         int max = 999999; 
        int number=(int)(Math.random()*(max-min+1)+min);
      
        
        String msg ="Your OTP - "+number+ " please verify this OTP in your Application by Subani simplekart.com sending OTP with springBoot REST API";
       
        
        Message message = Message.creator(new PhoneNumber(sms.getTo()), new PhoneNumber(FROM_NUMBER), msg)
                .create();
       
    }

    public void receive(MultiValueMap<String, String> smscallback) {
   
    }

}
