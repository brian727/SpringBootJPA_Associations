package com.brian.springdata.associations.repos;

import org.springframework.data.repository.CrudRepository;

import com.brian.springdata.associations.entities.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {




}
