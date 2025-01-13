package me.dvcopae.tickets.api;

import java.util.HashMap;
import java.util.Optional;
import me.dvcopae.tickets.persistance.dto.BookingRequest;
import me.dvcopae.tickets.persistance.entity.Booking;
import me.dvcopae.tickets.persistance.service.BookingService;

public class BookingClient extends HTTPClient<Booking, Integer> {

  private final BookingService service;

  public BookingClient(BookingService service) {
    super(service);
    this.service = service;
  }

  @Override
  public Response post(String url, String body) {
    BookingRequest request = new BookingRequest(new HashMap<>()); // parse body into BookingRequest
    Booking booking = service.createBooking(request);

    return new DummyResponse(201, "{\"message\": \"" + booking.getId() + "\"}", "application/json");
  }

  @Override
  public Response get(String url) {
    Integer id = 0; // parse id from url
    Optional<Booking> bo = service.findById(id);

    return bo.map(
            booking ->
                new DummyResponse(
                    200, "{\"data\": \"" + booking.toString() + "\"}", "application/json"))
        .orElseGet(() -> new DummyResponse(404, "{}", "application/json"));
  }
}
