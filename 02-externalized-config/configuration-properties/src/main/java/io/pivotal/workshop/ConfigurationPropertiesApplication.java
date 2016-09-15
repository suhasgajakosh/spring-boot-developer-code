package io.pivotal.workshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ConfigurationPropertiesApplication {

	final private PersonProperties personProperties;

	@Autowired
	public ConfigurationPropertiesApplication(PersonProperties personProperties) {
		this.personProperties = personProperties;
	}

	public static void main(String[] args) {
		SpringApplication.run(ConfigurationPropertiesApplication.class, args);
	}

	@RequestMapping("/")
	public String greetings(){
		return personProperties.getGreeting() + " Spring Boot! " + personProperties.getFarewell() + " Spring Boot!";
	}


}
