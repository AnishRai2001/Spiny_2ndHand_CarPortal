package com.spiny.spiny_demo.config;

import com.twilio.Twilio;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwilioConfig {

    @Value("${twilio.account.sid}")  // Injects the Twilio Account SID from properties
    private String accountSid;

    @Value("${twilio.auth.token}")  // Injects the Twilio Auth Token from properties
    private String authToken;

    @PostConstruct  // Runs automatically after Spring creates the bean
    public void init() {
        Twilio.init(accountSid, authToken);  // Initializes Twilio SDK with provided credentials
    }

}
