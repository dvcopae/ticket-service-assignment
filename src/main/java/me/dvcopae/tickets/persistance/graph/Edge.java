package me.dvcopae.tickets.persistance.graph;

public record Edge(Node destination, float distance) {

  @Override
  public String toString() {
    return String.format("(%s, %.2f)", destination.getStation().name(), distance);
  }
}
