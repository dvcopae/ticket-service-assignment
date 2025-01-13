package me.dvcopae.tickets.persistance.repository;

import java.util.Optional;
import me.dvcopae.tickets.persistance.entity.Passenger;

public final class PassengerRepo extends IntegerCrudRepository<Passenger> {

  private static final PassengerRepo INSTANCE = new PassengerRepo();

  public static PassengerRepo getInstance() {
    return INSTANCE;
  }

  private PassengerRepo() {
    super();
  }

  public Optional<Passenger> findByName(String name) {
    if (name == null || name.isBlank()) {
      return Optional.empty();
    }

    return findAll().stream().filter(passenger -> passenger.getName().equals(name)).findFirst();
  }
}
