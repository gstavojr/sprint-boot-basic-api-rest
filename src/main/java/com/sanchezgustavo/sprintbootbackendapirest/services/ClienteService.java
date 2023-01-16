package com.sanchezgustavo.sprintbootbackendapirest.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanchezgustavo.sprintbootbackendapirest.models.Cliente;
import com.sanchezgustavo.sprintbootbackendapirest.repositories.ClienteRepository;

@Service
@Transactional
public class ClienteService {
  
  @Autowired
  private ClienteRepository clienteRepository;

  @Transactional(readOnly = true)
  public List<Cliente> getClientes() {
    return clienteRepository.findAll();
  }

  @Transactional(readOnly = true)
  public Cliente getclienteById(Integer id) {
    return clienteRepository.findById(id).orElse(null);
  }

  @Transactional(rollbackFor = Exception.class)
  public Cliente saveCliente(Cliente cliente) {
    return clienteRepository.save(cliente);
  }

  @Transactional(rollbackFor = Exception.class)
  public void deleteClienteById(Integer id) {
    clienteRepository.deleteById(id);
  }
}
