package me.dvcopae.tickets.persistance.entity;

/** A passenger will have a name and can have multiple tickets. */
public final class Passenger implements Entity<Integer> {

  private final Integer id;
  private final String name;

  public Passenger(Integer id, String name) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Passenger must have a name.");
    }

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
