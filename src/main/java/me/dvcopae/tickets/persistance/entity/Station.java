package me.dvcopae.tickets.persistance.entity;

import java.util.Objects;

/** Each station has a name. */
public final class Station implements Entity<Integer> {

  private final Integer id;
  private final String name;

  public Station(Integer id, String name) {
    Objects.requireNonNull(id, "Station must have an id.");
    Objects.requireNonNull(name, "Station must have a name.");

    this.id = id;
    this.name = name;
  }

  @Override
  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }
}
