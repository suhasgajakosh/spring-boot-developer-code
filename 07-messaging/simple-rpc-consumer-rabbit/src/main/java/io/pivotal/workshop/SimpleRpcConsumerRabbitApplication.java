package io.pivotal.workshop;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.pivotal.workshop.domain.ServerSnippet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.SendTo;

import java.io.IOException;

@SpringBootApplication
public class SimpleRpcConsumerRabbitApplication {

	private static final Logger log = LoggerFactory.getLogger(SimpleRpcConsumerRabbitApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SimpleRpcConsumerRabbitApplication.class, args);
	}

	private ObjectMapper mapper = new ObjectMapper();

	@RabbitListener(queues = "snippet")
	@SendTo("snippet-reply")
	public String receive(String message) {
		log.info("Received: " + message);
		log.info("Processing...");

		try {
			ServerSnippet snippet = mapper.readValue(message, ServerSnippet.class);
			assert snippet != null;
			log.info("Validated: OK");
		} catch (IOException e) {
			log.error(e.getMessage());
			return "RECEIVED:ERROR";
		}

		return "RECEIVED:OK";
	}

	@Bean
	public Queue snippet(){
		return new Queue("snippet");
	}

	@Bean
	public Queue snippetReply(){
		return new Queue("snippet-reply");
	}
}
