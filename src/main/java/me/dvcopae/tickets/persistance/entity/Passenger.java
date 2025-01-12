package me.dvcopae.tickets.persistance.entity;

/**
 * A passenger will have a name and can have multiple tickets.
 *
 * @param name
 */
public record Passenger(String name) {
  public Passenger {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Passenger must have a name.");
    }
  }
}
