package com.sanchezgustavo.sprintbootbackendapirest.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * CREATE TABLE Cliente (
    id        SERIAL PRIMARY KEY,
    name      TEXT NOT NULL,
    lastName  TEXT NOT NULL,
    email     TEXT NOT NULL,
    createAt  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
 */
@Entity
@Table(name = "Cliente", schema = "public")
public class Cliente implements Serializable {


  private Integer id;
  private String name;
  private String lastName;
  private String email;
  private Date createAt;

  public Cliente() {
  }

  public Cliente(Integer id, String name, String lastName, String email, Date createAt) {
    this.id = id;
    this.name = name;
    this.lastName = lastName;
    this.email = email;
    this.createAt = createAt;
  }

  @Id
  @Column(name = "id") // Es opcional cuando el nombre de la columna es igual al nombre del atributo
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Column(name = "name", nullable = false)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Column(name = "lastName", nullable = false)
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Column(name = "email", unique = true, nullable = false)
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Column(name = "create_at")
  // @Temporal(TemporalType.DATE)
  public Date getCreateAt() {
    return createAt;
  }

  public void setCreateAt(Date createAt) {
    this.createAt = createAt;
  }

}
