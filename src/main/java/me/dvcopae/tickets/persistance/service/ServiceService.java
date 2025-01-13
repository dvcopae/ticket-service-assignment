package me.dvcopae.tickets.persistance.service;

import me.dvcopae.tickets.persistance.entity.Service;
import me.dvcopae.tickets.persistance.repository.ServiceRepo;

public class ServiceService extends RepositoryService<Service, Integer> {

  private final ServiceRepo repository;

  protected ServiceService(ServiceRepo repository) {
    super(repository);
    this.repository = repository;
  }
}
