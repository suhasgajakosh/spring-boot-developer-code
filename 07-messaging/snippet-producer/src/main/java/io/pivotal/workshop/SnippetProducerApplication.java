package io.pivotal.workshop;

import io.pivotal.workshop.domain.Snippet;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class SnippetProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SnippetProducerApplication.class, args);
	}

	private ConnectionFactory cachingConnectionFactory;

	@Autowired
	public SnippetProducerApplication(ConnectionFactory cachingConnectionFactory){
		this.cachingConnectionFactory = cachingConnectionFactory;
	}

	@Bean
	public RabbitTemplate template() {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(this.cachingConnectionFactory);
		rabbitTemplate.setQueue("spring-boot");
		rabbitTemplate.setRoutingKey("spring-boot");
		rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
		return rabbitTemplate;
	}

	@Scheduled(fixedDelay = 1000)
	public void sender() {
		Snippet snippet = new Snippet("JavaScript: Alert","alert('Hi there!');");
		template().convertAndSend(snippet);
	}
}
