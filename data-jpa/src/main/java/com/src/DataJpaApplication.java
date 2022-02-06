package com.src;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;

import com.src.itemactions.Action;
import com.src.itemactions.Item;
import com.src.itemactions.ItemCustomRepoImpl;
import com.src.itemactions.ItemRepository;

@SpringBootApplication
public class DataJpaApplication {
	
	private static final Logger log = LoggerFactory.getLogger(DataJpaApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(DataJpaApplication.class, args);
	}
	
	
	private class Clr1 implements Ordered, CommandLineRunner {
		
		CustomerRepository repository;
		
		public Clr1(CustomerRepository repository) {
			super();
			this.repository = repository;
		}
		@Override
		public void run(String... args) throws Exception {
			repository.save(new Customer("Jack", "Bauer"));
			repository.save(new Customer("Chloe", "O'Brian"));
			repository.save(new Customer("Kim", "Bauer"));
			repository.save(new Customer("David", "Palmer"));
			repository.save(new Customer("Michelle", "Dessler"));
			// fetch all customers
			log.info("Customers found with findAll():");
			for (Customer customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			// fetch an individual customer by ID
			Customer customer = repository.findById(1L);
			log.info("Customer found with findById(1L):");
			log.info("--------------------------------");
			log.info(customer.toString());
			log.info("");

			// fetch customers by last name
			log.info("Customer found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			repository.findByLastName("Bauer").forEach(bauer -> {
				log.info(bauer.toString());
			});			
		}

		@Override
		public int getOrder() {
			return 22;
		}
		
	}
	
	
	private class ItemRepoDataSetup implements Ordered, CommandLineRunner {
		
		private ItemRepository itemRepository;
		private ItemCustomRepoImpl itemRepositoryImpl;

		public ItemRepoDataSetup(ItemRepository itemRepository, ItemCustomRepoImpl itemRepositoryImpl) {
			super();
			this.itemRepository = itemRepository;
			this.itemRepositoryImpl=itemRepositoryImpl;
		}

		void sleep(){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void run(String... args) throws Exception {
			Action a1= new Action("action-1", Timestamp.valueOf(LocalDateTime.now()));
			sleep();
			Action a2= new Action("action-2", Timestamp.valueOf(LocalDateTime.now()));
			sleep();
			Action a3= new Action("action-3", Timestamp.valueOf(LocalDateTime.now()));
			sleep();
			Item i1 = new Item("item1",Arrays.asList(a1,a2,a3));
			sleep();
			Action a4= new Action("action-4", Timestamp.valueOf(LocalDateTime.now()));
			sleep();
			Action a5= new Action("action-5", Timestamp.valueOf(LocalDateTime.now()));
			sleep();
			Action a6= new Action("action-6", Timestamp.valueOf(LocalDateTime.now()));
		
			Item i2 = new Item("item2",Arrays.asList(a4,a5,a6));
			System.out.println(i1);
			System.out.println(i2);
			
			itemRepository.save(i1);
			itemRepository.save(i2);
			System.out.println(i1);
			System.out.println(i2);
			System.out.println("**********************************************");
			System.out.println("**********************************************");
			
			Action a = itemRepositoryImpl.getLastAction(i1.getId());
			System.out.println(a);
			
		}

		@Override
		public int getOrder() {
			return 44;
		}
	}
	
	@Bean
	public CommandLineRunner setupData(final CustomerRepository customerRepository) {
		return new Clr1(customerRepository);
	}
	
	@Bean
	public CommandLineRunner itemClr(final ItemRepository itemRepository, ItemCustomRepoImpl itemCustomRepoImpl) {
		return new ItemRepoDataSetup(itemRepository,itemCustomRepoImpl);
	}

}
