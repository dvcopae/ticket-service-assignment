package me.dvcopae.tickets.persistance.repository;

import me.dvcopae.tickets.persistance.entity.Route;
import me.dvcopae.tickets.persistance.entity.Station;

public final class RouteRepo extends IntegerCrudRepository<Route> {

  private static final RouteRepo INSTANCE = new RouteRepo(StationRepo.getInstance());

  private RouteRepo(StationRepo stationRepo) {
    super();
    initializeRoutes(stationRepo);
  }

  public static RouteRepo getInstance() {
    return INSTANCE;
  }

  private void initializeRoutes(StationRepo stationRepo) {
    // Paris -> London
    Station paris = stationRepo.findByName("Paris Gare du Nord");
    Station lille = stationRepo.findByName("Lille Europe");
    Station calais = stationRepo.findByName("Calais-Fréthun");
    Station ebbsfleet = stationRepo.findByName("Ebbsfleet International");
    Station ashford = stationRepo.findByName("Ashford International");
    Station london = stationRepo.findByName("London St. Pancras International");

    Route parisToLondon = new Route();
    parisToLondon.setName("Paris-London");
    parisToLondon.addStop(paris, lille, 100.0f);
    parisToLondon.addStop(lille, calais, 80.0f);
    parisToLondon.addStop(calais, ebbsfleet, 50.0f);
    parisToLondon.addStop(ebbsfleet, ashford, 60.0f);
    parisToLondon.addStop(ashford, london, 90.0f);
    save(parisToLondon);

    // Paris -> Amsterdam
    Station brusssels = stationRepo.findByName("Brussels-Midi/Zuid");
    Station antwerp = stationRepo.findByName("Antwerp-Central");
    Station rotterdam = stationRepo.findByName("Rotterdam Centraal");
    Station schiphol = stationRepo.findByName("Schiphol Airport");
    Station amsterdam = stationRepo.findByName("Amsterdam Centraal");

    Route parisToAmsterdam = new Route();
    parisToAmsterdam.setName("Paris-Amsterdam");
    parisToAmsterdam.addStop(paris, brusssels, 300.0f);
    parisToAmsterdam.addStop(brusssels, antwerp, 45.0f);
    parisToAmsterdam.addStop(antwerp, rotterdam, 95.0f);
    parisToAmsterdam.addStop(rotterdam, schiphol, 60.0f);
    parisToAmsterdam.addStop(schiphol, amsterdam, 15.0f);
    save(parisToAmsterdam);

    // Amsterdam - Berlin
    Route amsterdamToBerlin = new Route();
    amsterdamToBerlin.setName("Amsterdam-Berlin");
    amsterdamToBerlin.addStop(amsterdam, schiphol, 15.0f);
    amsterdamToBerlin.addStop(schiphol, rotterdam, 60.0f);
    amsterdamToBerlin.addStop(rotterdam, antwerp, 95.0f);
    amsterdamToBerlin.addStop(antwerp, brusssels, 45.0f);
    save(amsterdamToBerlin);
  }

  public Route findByName(String name) {
    if (name == null || name.isBlank()) {
      return null;
    }

    return findAll().stream()
        .filter(route -> route.getName().equals(name))
        .findFirst()
        .orElse(null);
  }
}
