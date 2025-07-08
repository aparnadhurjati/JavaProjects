package com.aparna.java.hello_springai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.aparna.java.hello_springai.functioncalling.wallet.WalletRepository;
import com.aparna.java.hello_springai.toolcalling.StockTools;
import com.aparna.java.hello_springai.toolcalling.WalletTools;

@SpringBootApplication
public class HelloSpringaiApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringaiApplication.class, args);
	}

	/** Below code is for testing the chat client */
	// @Bean
	// public CommandLineRunner run(ChatClient.Builder builder) {
	// 	return args -> {
	// 		var client = builder.build();
	// 		String response = client.prompt()
	// 					.user("Tell me a dad joke about cats")
	// 					.call()
	// 					.content();
	// 		System.out.println(response);
	// 	};
	// }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
	@Bean
    public WalletTools walletTools(WalletRepository walletRepository) {
        return new WalletTools(walletRepository);
    }

	@Bean
    public StockTools stockTools() {
        return new StockTools(restTemplate());
    }

}
