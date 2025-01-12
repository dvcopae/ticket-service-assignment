package me.dvcopae.tickets.persistance.entity;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/** A collection of passenger information and seat reservations. Immutable by design. */
public final class Booking implements Entity<Integer> {

  private final Integer id;
  private Map<Passenger, List<Ticket>> passengerTickets;

  public Booking(Integer id) {
    Objects.requireNonNull(id, "Booking must have an id.");

    this.id = id;
  }

  @Override
  public Integer getId() {
    return id;
  }

  public Map<Passenger, List<Ticket>> getPassengerTickets() {
    return passengerTickets;
  }

  public void setPassengerTickets(Map<Passenger, List<Ticket>> passengerTickets) {
    this.passengerTickets = passengerTickets;
  }
}
