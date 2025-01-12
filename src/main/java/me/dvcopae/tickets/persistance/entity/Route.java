package me.dvcopae.tickets.persistance.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Each route is a sequence of stops on stations. Each stop at a station will have the distance
 * traveled on that route compared to the previous station.
 */
public final class Route implements Entity<Integer> {

  private Integer id;
  private final List<Station> stations;
  private final List<Float> distances;
  private String name;

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

  public Station getOrigin() {
    return stations.getFirst();
  }

  public Station getDestination() {
    return stations.getLast();
  }

  public List<Station> getStations() {
    return List.copyOf(stations);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("Path{");
    for (int i = 1; i < stations.size(); i++) {
      sb.append(stations.get(i - 1).getName());
      sb.append(" -(").append(distances.get(i)).append(")-> ");
    }
    sb.append(stations.getLast().getName());
    sb.append("}");

    return sb.toString();
  }

  @Override
  public Integer getId() {
    return id;
  }

  @Override
  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
