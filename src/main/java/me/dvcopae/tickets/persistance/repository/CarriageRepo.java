package me.dvcopae.tickets.persistance.repository;

import java.util.Optional;
import me.dvcopae.tickets.persistance.entity.Carriage;

public final class CarriageRepo extends IntegerCrudRepository<Carriage> {

  private static final CarriageRepo INSTANCE = new CarriageRepo();

  public static CarriageRepo getInstance() {
    return INSTANCE;
  }

  private CarriageRepo() {
    super();
  }

  public Optional<Carriage> findByName(String name) {
    if (name == null || name.isBlank()) {
      return Optional.empty();
    }

    return findAll().stream().filter(c -> c.getName().equals(name)).findFirst();
  }
}
