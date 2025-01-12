package me.dvcopae.tickets.persistance.entity;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Set;

/**
 * A service is a physical train that operates at a specific route at a specific time. A service is
 * using multiple carriages to make a physical train.
 */
public final class Service {

  private Set<Carriage> carriages;
  private Route route;
  private ZonedDateTime time;

  public Service(Set<Carriage> carriages, Route route, ZonedDateTime time) {
    Objects.requireNonNull(carriages, "Service must have carriages.");
    if (carriages.isEmpty()) {
      throw new IllegalArgumentException("Service must have at least one carriage");
    }

    Objects.requireNonNull(route, "Service must have a route.");
    Objects.requireNonNull(time, "Service must have a time.");

    this.carriages = carriages;
    this.route = route;
    this.time = time;
  }

  public Set<Carriage> getCarriages() {
    return carriages;
  }

  public void setCarriages(Set<Carriage> carriages) {
    this.carriages = carriages;
  }

  public ZonedDateTime getTime() {
    return time;
  }

  public void setTime(ZonedDateTime time) {
    this.time = time;
  }

  public Route getRoute() {
    return route;
  }

  public void setRoute(Route route) {
    this.route = route;
  }
}
