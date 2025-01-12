package me.dvcopae.tickets.persistance.repository;

import me.dvcopae.tickets.persistance.entity.Entity;

public abstract class IntegerCrudRepository<T extends Entity<Integer>>
    extends CrudRepository<T, Integer> {

  /**
   * @return Next integer ID in this repository
   */
  @Override
  public Integer nextID() {
    return findAll().stream().mapToInt(Entity::getId).max().orElse(0) + 1;
  }
}
