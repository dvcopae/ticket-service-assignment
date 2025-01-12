package me.dvcopae.tickets.persistance.entity;

import java.util.List;
import java.util.Objects;

/**
 * A collection of passenger information and seat reservations.
 * Immutable by design.
 */
public record Booking(String id, Passenger passenger, List<Ticket> tickets) {

  public Booking {
    Objects.requireNonNull(id, "Booking must have an id.");
    Objects.requireNonNull(passenger, "Booking must have a passenger.");
    Objects.requireNonNull(tickets, "Booking must have tickets.");
    if (tickets.isEmpty()) {
      throw new IllegalArgumentException("Booking must have at least one ticket.");
    }
  }
}
