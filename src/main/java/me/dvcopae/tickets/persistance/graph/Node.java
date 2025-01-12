package me.dvcopae.tickets.persistance.graph;

import java.util.ArrayList;
import java.util.List;
import me.dvcopae.tickets.persistance.entity.Station;

public final class Node {

  private final Station station;
  private final List<Edge> edges;

  public Node(final Station station) {
    this.station = station;
    this.edges = new ArrayList<>();
  }

  public Station getStation() {
    return station;
  }

  public List<Edge> getEdges() {
    return edges;
  }

  public void addEdge(Node destination, int distance) {
    edges.add(new Edge(destination, distance));
  }
}
