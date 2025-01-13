package me.dvcopae.tickets.persistance.service;

import java.util.List;
import java.util.Optional;
import me.dvcopae.tickets.persistance.entity.Entity;
import me.dvcopae.tickets.persistance.repository.CrudRepository;

public abstract class RepositoryService<T extends Entity<Y>, Y> {

  private final CrudRepository<T, Y> repository;

  protected RepositoryService(CrudRepository<T, Y> repository) {
    this.repository = repository;
  }

  public List<T> findAll() {
    return this.repository.findAll().stream().toList();
  }

  public Optional<T> findById(Y id) {
    return this.repository.findById(id);
  }

  public Y save(T entity) {
    return this.repository.save(entity);
  }

  public List<Y> saveAll(List<T> entities) {
    return entities.stream().map(this::save).toList();
  }

  public void delete(T entity) {
    this.repository.delete(entity.getId());
  }

  public void delete(Y id) {
    this.repository.delete(id);
  }
}
