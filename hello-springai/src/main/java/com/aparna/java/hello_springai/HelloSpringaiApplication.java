package com.aparna.java.hello_springai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HelloSpringaiApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringaiApplication.class, args);
	}
	@Bean
	public CommandLineRunner run(ChatClient.Builder builder) {
		return args -> {
			var client = builder.build();
			String response = client.prompt()
						.user("Tell me a dad joke about cats")
						.call()
						.content();
			System.out.println(response);
		};
	}

}
