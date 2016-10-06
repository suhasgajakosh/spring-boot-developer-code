package io.pivotal.workshop;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
@EnableScheduling
public class SimpleRpcProducerRabbitApplication {

	public static void main(String[] args) {

		SpringApplication.run(SimpleRpcProducerRabbitApplication.class, args);
	}


	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Scheduled(fixedDelay = 1000L)
	public void send() {
		this.rabbitTemplate.convertAndSend("spring-boot", "Hello World! at " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
	}

	@Bean
	public Queue queue(){
		return new Queue("spring-boot", false);
	}
}
