package io.pivotal.workshop;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.pivotal.workshop.domain.Snippet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SimpleRpcProducerRabbitApplication {

	private static final Logger log = LoggerFactory.getLogger(SimpleRpcProducerRabbitApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SimpleRpcProducerRabbitApplication.class, args);
	}

	@Bean
	CommandLineRunner sender(RabbitTemplate rabbitTemplate){
		return args -> {
			Snippet snippet = new Snippet("JavaScript: Alert","alert('Hi there!');");
			ObjectMapper mapper = new ObjectMapper();
			String result = (String) rabbitTemplate.convertSendAndReceive("snippet", mapper.writeValueAsString(snippet));

			assert result!=null;

			log.info("Result: " + result);
		};
	}
}
