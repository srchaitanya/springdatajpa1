package com.src;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
	
	public List<Customer> findByLastName(String lastName);
	public Customer findById(long id);
	
}
