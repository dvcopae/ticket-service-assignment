package me.dvcopae.tickets.persistance.repository;

import me.dvcopae.tickets.persistance.entity.Passenger;

public final class PassengerRepo extends CrudRepository<Passenger, Integer> {

  private static final PassengerRepo INSTANCE = new PassengerRepo();

  public static PassengerRepo getInstance() {
    return INSTANCE;
  }

  private PassengerRepo() {
    super();
    initializePassengers();
  }

  public void initializePassengers() {
    save(new Passenger(1, "Michael"));
    save(new Passenger(2, "John"));
    save(new Passenger(3, "Polo"));
    save(new Passenger(4, "Barbara"));
    save(new Passenger(5, "Jessica"));
    save(new Passenger(6, "Sarah"));
  }
}
