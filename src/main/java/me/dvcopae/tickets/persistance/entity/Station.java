package me.dvcopae.tickets.persistance.entity;

import java.util.Objects;

/** Each station has a name. */
public final class Station implements Entity<Integer> {

  private Integer id;
  private final String name;

  public Station(String name) {
    Objects.requireNonNull(name, "Station must have a name.");

    this.name = name;
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
}
