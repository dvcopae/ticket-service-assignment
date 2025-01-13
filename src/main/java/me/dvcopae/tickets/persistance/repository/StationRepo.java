package me.dvcopae.tickets.persistance.repository;

import java.util.Optional;
import me.dvcopae.tickets.persistance.entity.Station;

public final class StationRepo extends IntegerCrudRepository<Station> {

  private static final StationRepo INSTANCE = new StationRepo();

  private StationRepo() {
    super();
  }

  public static StationRepo getInstance() {
    return INSTANCE;
  }

  public Optional<Station> findByName(String name) {
    if (name == null || name.isBlank()) {
      return Optional.empty();
    }

    return findAll().stream().filter(station -> station.getName().equals(name)).findFirst();
  }
}
