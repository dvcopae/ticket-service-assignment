package me.dvcopae.tickets.persistance.dto;

import java.util.Objects;

public record TicketRequest(Integer service, String seat, String origin, String destination) {

  public TicketRequest {
    Objects.requireNonNull(service, "Service must not be null");
    Objects.requireNonNull(seat, "Seat must not be null");
    Objects.requireNonNull(origin, "Origin must not be null");
    Objects.requireNonNull(destination, "Destination must not be null");
  }

  public String toString() {
    return "TicketRequest["
        + "service="
        + service
        + ", seat='"
        + seat
        + '\''
        + ", origin="
        + origin
        + ", destination="
        + destination
        + ']';
  }
}
