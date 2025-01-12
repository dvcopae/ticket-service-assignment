package me.dvcopae.tickets.persistance.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Each route is a sequence of stops on stations. Each stop at a station will have the distance
 * traveled on that route compared to the previous station.
 */
public final class Route {

  private final List<Station> stations;
  private final List<Float> distances;

  public Route() {
    stations = new ArrayList<>();
    distances = new ArrayList<>();
  }

  public void addStop(Station from, Station to, float distance) {
    if (!stations.isEmpty() && !stations.getLast().equals(from)) {
      throw new IllegalArgumentException("The 'from' node must match the last node in the path.");
    }

    if (stations.isEmpty()) {
      stations.add(from);
    }

    stations.add(to);
    distances.add(distance);
  }

  public List<Station> getStations() {
    return List.copyOf(stations);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("Path{");
    for (int i = 1; i < stations.size(); i++) {
      sb.append(stations.get(i - 1).name());
      sb.append(" -(").append(distances.get(i)).append(")-> ");
    }
    sb.append(stations.getLast().name());
    sb.append("}");

    return sb.toString();
  }
}
