package com.sanchezgustavo.sprintbootbackendapirest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDto {
  private String name;
  private String lastName;
  private String email;
}
