package me.dvcopae.tickets.persistance.service;

import java.util.Optional;
import me.dvcopae.tickets.persistance.entity.Passenger;
import me.dvcopae.tickets.persistance.repository.PassengerRepo;

public class PassengerService extends RepositoryService<Passenger, Integer> {

  private final PassengerRepo repository;

  protected PassengerService(PassengerRepo repository) {
    super(repository);
    this.repository = repository;
  }

  public Optional<Passenger> findByName(String name) {
    return this.repository.findByName(name);
  }
}
