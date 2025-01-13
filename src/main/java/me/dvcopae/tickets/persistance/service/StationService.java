package me.dvcopae.tickets.persistance.service;

import java.util.Optional;
import me.dvcopae.tickets.persistance.entity.Station;
import me.dvcopae.tickets.persistance.repository.StationRepo;

public class StationService extends RepositoryService<Station, Integer> {

  private final StationRepo repository;

  protected StationService(StationRepo repository) {
    super(repository);
    this.repository = repository;
  }

  public Optional<Station> findByName(String name) {
    return repository.findByName(name);
  }
}
