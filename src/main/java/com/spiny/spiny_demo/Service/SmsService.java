package com.spiny.spiny_demo.Service;



import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Message;

import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

@Value("${twilio.phone.number}")  // Injects the Twilio "from" number from application properties
private String fromPhoneNumber;

public void sendSms(String to, String body) {
    try {
        // Remove any leading or trailing whitespace from the 'to' number
        String cleanedTo = to.trim();

        // Create and send the SMS message using Twilio's API
        Message message = Message.creator(
                new PhoneNumber(cleanedTo),       // Recipient number
                new PhoneNumber(fromPhoneNumber), // Twilio sender number
                body                              // Message content
        ).create();

        // Optionally, you can print success to console or handle further
        System.out.println("Message sent successfully to: " + cleanedTo);
    } catch (ApiException e) {
        // Handle Twilio API specific exceptions
        System.out.println("Twilio API exception occurred while sending SMS to " + to + ": " + e.getMessage());
    } catch (Exception e) {
        // Catch any other exceptions
        System.out.println("An error occurred while sending SMS to " + to + ": " + e.getMessage());
    }
}
}



