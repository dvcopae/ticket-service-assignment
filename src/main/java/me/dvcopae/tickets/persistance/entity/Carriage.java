package me.dvcopae.tickets.persistance.entity;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

/** A component that represents a part of a physical train. */
public final class Carriage {

  private String carriageNumber;
  private Set<String> firstClassSeats;
  private Set<String> secondClassSeats;

  public Carriage(String carriageNumber) {
    Objects.requireNonNull(carriageNumber, "Carriage must have a carriage number.");
    this.carriageNumber = carriageNumber;
    this.firstClassSeats = Collections.emptySet();
    this.secondClassSeats = Collections.emptySet();
  }

  public String getCarriageNumber() {
    return carriageNumber;
  }

  public void setCarriageNumber(String carriageNumber) {
    this.carriageNumber = carriageNumber;
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
}
