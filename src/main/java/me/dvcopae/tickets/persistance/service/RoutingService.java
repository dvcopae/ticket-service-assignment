package me.dvcopae.tickets.persistance.service;

import me.dvcopae.tickets.persistance.entity.Route;
import me.dvcopae.tickets.persistance.entity.Station;
import me.dvcopae.tickets.persistance.repository.RouteRepo;

public class RoutingService extends RepositoryService<Route, Integer> {

  protected RoutingService(RouteRepo repository) {
    super(repository);
  }

  public boolean verifyStations(Route route, Station start, Station end) {
    return route.getStations().contains(start) && route.getStations().contains(end);
  }

  /**
   * For the future: Uses the shortest path on the graph created from {@link
   * me.dvcopae.tickets.persistance.graph.Node} and {@link
   * me.dvcopae.tickets.persistance.graph.Edge}.
   *
   * @return most optimal route
   */
  public Route findBestRoute() {
    throw new UnsupportedOperationException("Not yet implemented");
  }
}
