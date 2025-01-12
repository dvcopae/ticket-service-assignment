package me.dvcopae.tickets.persistance.repository;

import me.dvcopae.tickets.persistance.entity.Station;

public final class StationRepo extends IntegerCrudRepository<Station> {

  private static final StationRepo INSTANCE = new StationRepo();

  private StationRepo() {
    super();
    initializeStations();
  }

  private void initializeStations() {
    // Paris - London
    save(new Station("Paris Gare du Nord"));
    save(new Station("Lille Europe"));
    save(new Station("Calais-Fréthun"));
    save(new Station("Ebbsfleet International"));
    save(new Station("Ashford International"));
    save(new Station("London St. Pancras International"));

    // Paris - Amsterdam
    // Belgium - Amsterdam
    save(new Station("Brussels-Midi/Zuid"));
    save(new Station("Antwerp-Central"));
    save(new Station("Rotterdam Centraal"));
    save(new Station("Schiphol Airport"));
    save(new Station("Amsterdam Centraal"));
  }

  public static StationRepo getInstance() {
    return INSTANCE;
  }

  public Station findByName(String name) {
    if (name == null || name.isBlank()) {
      return null;
    }

    return findAll().stream()
        .filter(station -> station.getName().equals(name))
        .findFirst()
        .orElse(null);
  }
}
