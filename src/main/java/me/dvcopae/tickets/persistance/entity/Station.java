package me.dvcopae.tickets.persistance.entity;

import java.util.Objects;

/**
 * Each station has a name.
 *
 * @param name
 */
public record Station(String name) {

  public Station {
    Objects.requireNonNull(name, "Station must have a name.");
  }
}
