package me.dvcopae.tickets.persistance.repository;

import java.time.LocalDate;
import java.util.Set;
import me.dvcopae.tickets.persistance.entity.Service;

public final class ServiceRepo extends CrudRepository<Service, Integer> {

  private static final ServiceRepo INSTANCE =
      new ServiceRepo(RouteRepo.getInstance(), CarriageRepo.getInstance());

  private ServiceRepo(RouteRepo routeRepo, CarriageRepo carriageRepo) {
    super();
    initializeServices(routeRepo, carriageRepo);
  }

  private void initializeServices(RouteRepo routeRepo, CarriageRepo carriageRepo) {
    var parisToAmsterdam = routeRepo.findByName("Paris-Amsterdam");
    var carriageA = carriageRepo.findByName("A");
    var carriageT = carriageRepo.findByName("T");
    LocalDate date = LocalDate.of(2021, 4, 1);
    var s5160 = new Service(5160, Set.of(carriageA, carriageT), parisToAmsterdam, date);
    save(s5160);

    var londonToParis = routeRepo.findByName("Paris-London");
    var carriageN = carriageRepo.findByName("N");
    var carriageH = carriageRepo.findByName("H");
    var s5170 = new Service(5170, Set.of(carriageN, carriageH), londonToParis, date);
    save(s5170);
  }

  public static ServiceRepo getInstance() {
    return INSTANCE;
  }
}
