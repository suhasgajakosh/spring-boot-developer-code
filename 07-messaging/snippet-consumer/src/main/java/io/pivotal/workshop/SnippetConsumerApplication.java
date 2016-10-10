package io.pivotal.workshop;

import io.pivotal.workshop.domain.SnippetNotifier;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SnippetConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SnippetConsumerApplication.class, args);
	}

	private ConnectionFactory cachingConnectionFactory;

	@Autowired
	public SnippetConsumerApplication(ConnectionFactory cachingConnectionFactory){
		this.cachingConnectionFactory = cachingConnectionFactory;
	}

	@Bean
	public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConnectionFactory(this.cachingConnectionFactory);
		factory.setMessageConverter(new Jackson2JsonMessageConverter());
		return factory;
	}

	@RabbitListener(queues = "spring-boot")
	public void receiveMessage(SnippetNotifier snippetNotifier) {
		System.out.println("Received <" + snippetNotifier.getTitle() + " on " + snippetNotifier.getCreated() +">");
	}

	@Bean
	public Queue queue(){
		return new Queue("spring-boot", false);
	}
}
