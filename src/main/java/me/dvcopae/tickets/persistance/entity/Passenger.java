package me.dvcopae.tickets.persistance.entity;

/** A passenger will have a name and can have multiple tickets. */
public final class Passenger implements Entity<Integer> {

  private Integer id;
  private final String name;

  public Passenger(String name) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Passenger must have a name.");
    }

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
