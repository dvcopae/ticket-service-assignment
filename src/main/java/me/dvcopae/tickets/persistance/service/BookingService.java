package me.dvcopae.tickets.persistance.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import me.dvcopae.tickets.exceptions.BookingException;
import me.dvcopae.tickets.persistance.dto.PartialBooking;
import me.dvcopae.tickets.persistance.dto.TicketRequest;
import me.dvcopae.tickets.persistance.entity.Booking;
import me.dvcopae.tickets.persistance.entity.Passenger;
import me.dvcopae.tickets.persistance.entity.Service;
import me.dvcopae.tickets.persistance.entity.Station;
import me.dvcopae.tickets.persistance.entity.Ticket;
import me.dvcopae.tickets.persistance.repository.BookingRepo;

public class BookingService extends RepositoryService<Booking, Integer> {

  private final BookingRepo bookingRepo;
  private final TicketingService ticketingService;
  private final RoutingService routingService;
  private final StationService stationService;
  private final ServiceService serviceService;

  protected BookingService(
      BookingRepo repository,
      TicketingService ticketingService,
      RoutingService routingService,
      StationService stationService,
      ServiceService serviceService) {
    super(repository);
    this.bookingRepo = repository;
    this.ticketingService = ticketingService;
    this.routingService = routingService;
    this.stationService = stationService;
    this.serviceService = serviceService;
  }

  public Booking createBooking(Map<Passenger, List<TicketRequest>> bookingRequest)
      throws BookingException {
    Booking booking = new Booking();
    List<PartialBooking> partialBookings = new ArrayList<>();
    for (var entry : bookingRequest.entrySet()) {
      partialBookings.add(createPartialBooking(entry.getKey(), entry.getValue()));
    }

    // Save only when all the partial bookings have been successfully created
    for (var partialBooking : partialBookings) {
      this.ticketingService.saveAll(partialBooking.tickets());
    }
    booking.setPartialBookings(partialBookings);
    this.save(booking);
    return booking;
  }

  private PartialBooking createPartialBooking(Passenger passenger, List<TicketRequest> requests)
      throws BookingException {
    List<Ticket> createdTickets = new ArrayList<>();

    for (TicketRequest request : requests) {

      Service service =
          serviceService
              .findById(request.service())
              .orElseThrow(() -> new BookingException("The requested service does not exist"));
      Station origin =
          stationService
              .findByName(request.origin())
              .orElseThrow(
                  () -> new BookingException("The requested origin station does not exist"));
      Station destination =
          stationService
              .findByName(request.destination())
              .orElseThrow(
                  () -> new BookingException("The requested destination station does not exist"));

      if (!this.ticketingService.isValidRequest(request.seat(), service, origin, destination)) {
        throw new BookingException("The ticket request is not valid");
      }

      if (!isSeatFree(request.seat(), service, origin, destination)) {
        throw new BookingException("The requested seat is not available");
      }

      // Valid
      createdTickets.add(new Ticket(service, origin, destination, request.seat()));
    }

    return new PartialBooking(passenger, createdTickets);
  }

  /**
   * Checks whether the seat is available in the service between the two stations. The start and end
   * stations are necessary since a passenger might only book a segment of the whole service route,
   * leaving other segments free for other passengers.
   *
   * @param seat seat to be checked
   * @param service service
   * @param start origin station where the seat is requested
   * @param end destination station where the seat is requested
   * @return true if the seat is free, false otherwise
   */
  public boolean isSeatFree(String seat, Service service, Station start, Station end)
      throws BookingException {
    List<Station> routeStations = service.getRoute().getStations();

    if (!this.routingService.verifyStations(service.getRoute(), start, end)) {
      throw new BookingException("The requested start/end stations are not valid for the service.");
    }

    // Get the i-th stations index for the requested seat in a single loop
    int bookingOriginIndex = 0;
    int bookingDestinationIndex = 0;
    for (int i = 0;
        i < routeStations.size() && (bookingOriginIndex == 0 || bookingDestinationIndex == 0);
        i++) {
      if (routeStations.get(i).equals(start)) {
        bookingOriginIndex = i;
      } else if (routeStations.get(i).equals(end)) {
        bookingDestinationIndex = i;
      }
    }

    for (Booking b : bookingRepo.findAllByService(service)) {
      List<Ticket> bookingTickets =
          b.getPartialBookings().stream()
              .map(PartialBooking::tickets)
              .flatMap(Collection::stream)
              .toList();
      for (Ticket t : bookingTickets) {
        if (t.getSeat().equals(seat)) {

          int ticketOriginIndex = 0;
          int ticketDestinationIndex = 0;

          // Get the i-th stations index for the booked seat in a single loop
          for (int i = 0;
              i < routeStations.size() && (ticketOriginIndex == 0 || ticketDestinationIndex == 0);
              i++) {
            if (routeStations.get(i).equals(t.getOrigin())) {
              ticketOriginIndex = i;
            } else if (routeStations.get(i).equals(t.getDestination())) {
              ticketDestinationIndex = i;
            }
          }

          // For a seat to be free, the stations of the requested must lie completely BEFORE or
          // AFTER the stations of the booked seat. If the seat is busy, this condition is reversed.
          boolean isBusy =
              ticketDestinationIndex > bookingOriginIndex
                  && bookingDestinationIndex > ticketOriginIndex;

          if (isBusy) {
            return false;
          }
        }
      }
    }

    return true;
  }
}
