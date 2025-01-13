package me.dvcopae.tickets.persistance.service;

import java.util.Optional;
import me.dvcopae.tickets.persistance.entity.Carriage;
import me.dvcopae.tickets.persistance.repository.CarriageRepo;

public class CarriageService extends RepositoryService<Carriage, Integer> {

  private final CarriageRepo repository;

  protected CarriageService(CarriageRepo repository) {
    super(repository);
    this.repository = repository;
  }

  public Optional<Carriage> findByName(String name) {
    return this.repository.findByName(name);
  }
}
