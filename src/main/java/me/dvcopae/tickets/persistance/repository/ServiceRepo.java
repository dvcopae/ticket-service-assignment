package me.dvcopae.tickets.persistance.repository;

import me.dvcopae.tickets.persistance.entity.Service;

public final class ServiceRepo extends IntegerCrudRepository<Service> {

  private static final ServiceRepo INSTANCE = new ServiceRepo();

  private ServiceRepo() {
    super();
  }

  public static ServiceRepo getInstance() {
    return INSTANCE;
  }
}
