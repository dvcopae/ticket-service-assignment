package me.dvcopae.tickets.persistance.dto;

import java.util.List;
import java.util.Map;
import me.dvcopae.tickets.persistance.entity.Passenger;

public record BookingRequest(Map<Passenger, List<TicketRequest>> request) {

  public BookingRequest {
    if (request.isEmpty()) {
      throw new IllegalArgumentException("At least one passenger must be reserved");
    }
  }
}
