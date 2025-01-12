package me.dvcopae.tickets.persistance.entity;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

/**
 * A service is a physical train that operates at a specific route at a specific time. A service is
 * using multiple carriages to make a physical train.
 */
public final class Service implements Entity<Integer> {

  private final Integer id;
  private Set<Carriage> carriages;
  private Route route;
  private LocalDate date;

  public Service(Integer id, Set<Carriage> carriages, Route route, LocalDate time) {
    Objects.requireNonNull(id, "Service must have an id.");
    Objects.requireNonNull(carriages, "Service must have carriages.");
    if (carriages.isEmpty()) {
      throw new IllegalArgumentException("Service must have at least one carriage");
    }

    Objects.requireNonNull(route, "Service must have a route.");
    Objects.requireNonNull(time, "Service must have a time.");

    this.id = id;
    this.carriages = carriages;
    this.route = route;
    this.date = time;
  }

  public Set<Carriage> getCarriages() {
    return carriages;
  }

  public void setCarriages(Set<Carriage> carriages) {
    this.carriages = carriages;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public Route getRoute() {
    return route;
  }

  public void setRoute(Route route) {
    this.route = route;
  }

  @Override
  public Integer getId() {
    return id;
  }
}
