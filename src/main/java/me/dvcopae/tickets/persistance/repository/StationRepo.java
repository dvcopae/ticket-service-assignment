package me.dvcopae.tickets.persistance.repository;

import me.dvcopae.tickets.persistance.entity.Station;

public final class StationRepo extends CrudRepository<Station, Integer> {

  private static final StationRepo INSTANCE = new StationRepo();

  private StationRepo() {
    super();
    initializeStations();
  }

  private void initializeStations() {
    // Paris - London
    save(new Station(6, "Paris Gare du Nord"));
    save(new Station(7, "Lille Europe"));
    save(new Station(8, "Calais-Fréthun"));
    save(new Station(9, "Ebbsfleet International"));
    save(new Station(10, "Ashford International"));
    save(new Station(11, "London St. Pancras International"));

    // Paris - Amsterdam
    // Belgium - Amsterdam
    save(new Station(5, "Brussels-Midi/Zuid"));
    save(new Station(4, "Antwerp-Central"));
    save(new Station(3, "Rotterdam Centraal"));
    save(new Station(2, "Schiphol Airport"));
    save(new Station(1, "Amsterdam Centraal"));
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
