package com.example.accessingdatajpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@SpringBootApplication
public class AccessingDataJpaApplication {

	private static final Logger LOG =
			LoggerFactory.getLogger(AccessingDataJpaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AccessingDataJpaApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(CustomerRepository repository) {

		return (args) -> {
			repository.save(new Customer("Stas", "Roshchenko"));
			repository.save(new Customer("Bohan", "Tamirov"));
			repository.save(new Customer("Mora", "Tsamyuk"));
			repository.save(new Customer("Naya", "Rechenko"));

			LOG.info("");
			LOG.info("Find customers with findAll():");
			LOG.info("------------------------------");
			for (Customer c : repository.findAll()) {
				LOG.info(c.toString());
			}
			LOG.info("");

			Optional<Customer> customer = repository.findById(1L);
			if (customer.isEmpty()) throw new IllegalStateException("No such user");
			LOG.info("Find customer by ID:");
			LOG.info("------------------------------");
			LOG.info(customer.get().toString());
			LOG.info("");

			repository.findByLastName("Tamirov").forEach(c ->
					LOG.info(c.toString())
			);
			LOG.info("");

		};
	}

}
