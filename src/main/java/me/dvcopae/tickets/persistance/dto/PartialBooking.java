package me.dvcopae.tickets.persistance.dto;

import java.util.List;
import java.util.Objects;
import me.dvcopae.tickets.persistance.entity.Passenger;
import me.dvcopae.tickets.persistance.entity.Ticket;

/**
 * A partial booking contains information for a single passenger and its seat reservations
 * (tickets).
 */
public record PartialBooking(Passenger passenger, List<Ticket> tickets) {

  public PartialBooking {
    Objects.requireNonNull(passenger, "Passenger must not be null");
    Objects.requireNonNull(tickets, "Tickets must not be null");
    if (tickets.isEmpty()) {
      throw new IllegalArgumentException("At least one ticket must be reserved");
    }
  }

  @Override
  public String toString() {
    return "{passenger=" + passenger.getName() + ", tickets=" + tickets + '}';
  }
}
