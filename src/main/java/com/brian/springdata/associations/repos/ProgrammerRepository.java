package com.brian.springdata.associations.repos;

import org.springframework.data.repository.CrudRepository;

import com.brian.springdata.associations.manytomany.entities.Programmer;

public interface ProgrammerRepository extends CrudRepository<Programmer, Integer> {

}
