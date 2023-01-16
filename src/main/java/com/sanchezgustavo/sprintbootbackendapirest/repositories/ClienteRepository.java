package com.sanchezgustavo.sprintbootbackendapirest.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sanchezgustavo.sprintbootbackendapirest.models.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Integer> {
  
  @Override
  public List<Cliente> findAll();
}
