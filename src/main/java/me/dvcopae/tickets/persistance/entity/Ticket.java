package me.dvcopae.tickets.persistance.entity;

import java.util.Objects;

/**
 * A ticket contains a seat and is only valid for an origin and destination on a certain service.
 * Immutable by design.
 */
public record Ticket(Service service, Station origin, Station destination, String seat) {
  public Ticket {
    Objects.requireNonNull(service, "Ticket must have a service.");
    Objects.requireNonNull(origin, "Ticket must have an origin.");
    Objects.requireNonNull(destination, "Ticket must have a destination.");
    Objects.requireNonNull(seat, "Ticket must have a seat.");
  }
}
