package com.src.itemactions;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class ItemService {

//	@Bean
//	@Order(value = 1)
	public CommandLineRunner printLastInsertedAction(ActionRepository repository) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				System.out.println("--------------------");
			}
		}; 
	}
	
}
