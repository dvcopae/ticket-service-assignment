package me.dvcopae.tickets.persistance.repository;

import me.dvcopae.tickets.persistance.entity.Passenger;

public final class PassengerRepo extends IntegerCrudRepository<Passenger> {

  private static final PassengerRepo INSTANCE = new PassengerRepo();

  public static PassengerRepo getInstance() {
    return INSTANCE;
  }

  private PassengerRepo() {
    super();
    initializePassengers();
  }

  public void initializePassengers() {
    save(new Passenger("Michael"));
    save(new Passenger("John"));
    save(new Passenger("Polo"));
    save(new Passenger("Barbara"));
    save(new Passenger("Jessica"));
    save(new Passenger("Sarah"));
  }
}
