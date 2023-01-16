package com.sanchezgustavo.sprintbootbackendapirest.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sanchezgustavo.sprintbootbackendapirest.dto.ClienteDto;
import com.sanchezgustavo.sprintbootbackendapirest.models.Cliente;
import com.sanchezgustavo.sprintbootbackendapirest.services.ClienteService;

@CrossOrigin(origins = { "http://localhost:4200", "*" })
@RestController
@RequestMapping("/api")
public class ClienteController {

  @Autowired
  private ClienteService clienteService;

  @GetMapping("/cliente")
  public List<Cliente> getClientes() {
    return this.clienteService.getClientes();
  }

  @GetMapping("/cliente/{id}")
  @ResponseStatus(HttpStatus.OK) // Por defecto lo agrega
  public ResponseEntity<?> getClienteById(@PathVariable Integer id) {
    
    Map<String, Object> res = new HashMap<>();

    try {

      final Cliente clientSearched =  this.clienteService.getclienteById(id);

      if (clientSearched == null) {

        res.put("msg", "El cliente con el ID: " + id + " no existe en la base de datos");
        return new ResponseEntity<Map<String, Object>>(res, HttpStatus.NOT_FOUND);

      }
      res.put("msg", "Se ha encontrado el cliente con el ID: " + id);
      res.put("data", clientSearched);

      return new ResponseEntity<Map<String, Object>>(res, HttpStatus.OK);

    } catch (DataAccessException e) {

      res.put("msg", "Error al ejecutar la consulta en la base de datos");
      res.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
      return new ResponseEntity<Map<String, Object>>(res, HttpStatus.INTERNAL_SERVER_ERROR);

    }




  }

  @PostMapping("/cliente")
  public ResponseEntity<?> saveCliente(@RequestBody ClienteDto cliente) {

    Map<String, Object> res = new HashMap<>();

    try {

      final Cliente newClient = new Cliente(
        null, 
        cliente.getName(), 
        cliente.getLastName(), 
        cliente.getEmail(), 
        new Date()
      );
      final Cliente clientSaved = this.clienteService.saveCliente(newClient);

      if (clientSaved == null) {

        res.put("msg", "Error al guardar el cliente en la base de datos");
        return new ResponseEntity<Map<String, Object>>(res, HttpStatus.INTERNAL_SERVER_ERROR);

      }

      res.put("msg", "Se ha guardado el cliente en la base de datos");
      res.put("data", clientSaved);

      return new ResponseEntity<Map<String, Object>>(res, HttpStatus.CREATED);

      
    } catch (DataAccessException e) {
      res.put("msg", "Error al ejecutar el insert en la base de datos");
      res.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
      return new ResponseEntity<Map<String, Object>>(res, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    


    // return this.clienteService.saveCliente(newClient);
  }

  @PutMapping("/cliente/{id}")
  public ResponseEntity<?> updateCliente(@RequestBody ClienteDto cliente, @PathVariable Integer id) {

    Map<String, Object> res = new HashMap<>();

    try {

      Cliente clienteFinded = clienteService.getclienteById(id);

      if (clienteFinded == null) {
  
        res.put("msg", "El cliente con el ID: " + id + " no existe en la base de datos");
        return new ResponseEntity<Map<String, Object>>(res, HttpStatus.NOT_FOUND);
  
      }
  
      clienteFinded.setName(cliente.getName());
      clienteFinded.setLastName(cliente.getLastName());
      clienteFinded.setEmail(cliente.getEmail());
  
      // El save se encarga de actualizar el cliente o crearlo si no existe
      final Cliente clientUpdated = this.clienteService.saveCliente(clienteFinded);
  
      res.put("msg", "Se ha actualizado el cliente en la base de datos");
      res.put("data", clientUpdated);
  
      return new ResponseEntity<Map<String, Object>>(res, HttpStatus.CREATED);
      
    } catch (DataAccessException e) {
      res.put("msg", "Error al ejecutar el actualizar en la base de datos");
      res.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
      return new ResponseEntity<Map<String, Object>>(res, HttpStatus.INTERNAL_SERVER_ERROR);
    }


  }

  @DeleteMapping("/cliente/{id}")
  public ResponseEntity<?> deleteCliente(@PathVariable Integer id) {

    Map<String, Object> res = new HashMap<>();

    try {

      this.clienteService.deleteClienteById(id);

      res.put("msg", "Se ha eliminado el cliente en la base de datos");
      return new ResponseEntity<Map<String, Object>>(res, HttpStatus.OK);
      
    } catch (DataAccessException e) {
      res.put("msg", "Error al ejecutar el eliminar en la base de datos");
      res.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
      return new ResponseEntity<Map<String, Object>>(res, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    
  }

}
