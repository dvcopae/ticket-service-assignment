package me.dvcopae.tickets.persistance.repository;

import java.util.Optional;
import me.dvcopae.tickets.persistance.entity.Route;

public final class RouteRepo extends IntegerCrudRepository<Route> {

  private static final RouteRepo INSTANCE = new RouteRepo();

  private RouteRepo() {
    super();
  }

  public static RouteRepo getInstance() {
    return INSTANCE;
  }

  public Optional<Route> findByName(String name) {
    if (name == null || name.isBlank()) {
      return Optional.empty();
    }

    return findAll().stream().filter(route -> route.getName().equals(name)).findFirst();
  }
}
