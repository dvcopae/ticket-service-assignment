package me.dvcopae.tickets.persistance.entity;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

/** A component that represents a part of a physical train. */
public final class Carriage implements Entity<Integer> {

  private Integer id;
  private String name;
  private Set<String> firstClassSeats;
  private Set<String> secondClassSeats;

  public Carriage(String name) {
    Objects.requireNonNull(name, "Carriage must have a carriage number.");
    this.name = name;
    this.firstClassSeats = Collections.emptySet();
    this.secondClassSeats = Collections.emptySet();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<String> getFirstClassSeats() {
    return firstClassSeats;
  }

  public void setFirstClassSeats(Set<String> firstClassSeats) {
    this.firstClassSeats = firstClassSeats;
  }

  public Set<String> getSecondClassSeats() {
    return secondClassSeats;
  }

  public void setSecondClassSeats(Set<String> secondClassSeats) {
    this.secondClassSeats = secondClassSeats;
  }

  @Override
  public Integer getId() {
    return id;
  }

  @Override
  public void setId(Integer id) {
    this.id = id;
  }
}
