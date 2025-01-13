package me.dvcopae.tickets.api;

import me.dvcopae.tickets.persistance.entity.Entity;
import me.dvcopae.tickets.persistance.service.RepositoryService;

public abstract class HTTPClient<T extends Entity<Y>, Y> {

  private final RepositoryService<T, Y> service;

  protected HTTPClient(RepositoryService<T, Y> service) {
    this.service = service;
  }

  abstract Response post(String url, String body);

  abstract Response get(String url);
}
