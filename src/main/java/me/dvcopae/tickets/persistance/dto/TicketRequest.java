package me.dvcopae.tickets.persistance.dto;

import java.util.Objects;
import me.dvcopae.tickets.persistance.entity.Service;
import me.dvcopae.tickets.persistance.entity.Station;

public record TicketRequest(Service service, String seat, Station origin, Station destination) {

  public TicketRequest {
    Objects.requireNonNull(service, "Service must not be null");
    Objects.requireNonNull(seat, "Seat must not be null");
    Objects.requireNonNull(origin, "Origin must not be null");
    Objects.requireNonNull(destination, "Destination must not be null");
  }

  public String toString() {
    return "TicketRequest["
        + "service="
        + service.getId()
        + ", seat='"
        + seat
        + '\''
        + ", origin="
        + origin.getName()
        + ", destination="
        + destination.getName()
        + ']';
  }
}
