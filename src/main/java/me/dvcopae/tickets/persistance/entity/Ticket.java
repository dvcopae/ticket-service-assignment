package me.dvcopae.tickets.persistance.entity;

import java.util.Objects;

/**
 * A ticket contains a seat and is only valid for an origin and destination on a certain service.
 * Immutable by design.
 */
public final class Ticket implements Entity<Integer> {

  private Integer id;
  private final Service service;
  private final Station origin;
  private final Station destination;
  private final String seat;

  public Ticket(Service service, Station origin, Station destination, String seat) {
    Objects.requireNonNull(service, "Ticket must have a service.");
    Objects.requireNonNull(origin, "Ticket must have an origin.");
    Objects.requireNonNull(destination, "Ticket must have a destination.");
    Objects.requireNonNull(seat, "Ticket must have a seat.");

    this.service = service;
    this.origin = origin;
    this.destination = destination;
    this.seat = seat;
  }

  @Override
  public Integer getId() {
    return id;
  }

  @Override
  public void setId(Integer id) {
    this.id = id;
  }

  public Service getService() {
    return service;
  }

  public Station getOrigin() {
    return origin;
  }

  public Station getDestination() {
    return destination;
  }

  public String getSeat() {
    return seat;
  }

  @Override
  public String toString() {
    return "Ticket{"
        + "id="
        + id
        + ", origin="
        + origin
        + ", destination="
        + destination
        + ", seat='"
        + seat
        + '\''
        + '}';
  }

  public boolean isFirstClass() {
    return this.service.getCarriages().stream()
        .anyMatch(c -> c.getFirstClassSeats().contains(seat));
  }

  public boolean isSecondClass() {
    return !isFirstClass();
  }
}
