package io.pivotal.workshop;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="person")
public class PersonProperties {
    private String greeting;
    private String farewell;

    public String getFarewell() {
        return farewell;
    }

    public void setFarewell(String farewell) {
        this.farewell = farewell;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }
}
