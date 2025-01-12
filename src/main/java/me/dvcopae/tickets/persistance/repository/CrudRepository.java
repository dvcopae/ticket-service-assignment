package me.dvcopae.tickets.persistance.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import me.dvcopae.tickets.persistance.entity.Entity;

public abstract class CrudRepository<T extends Entity<Y>, Y> {

  private final Map<Y, T> objects;

  protected CrudRepository() {
    objects = new HashMap<>();
  }

  /**
   * Save the entity to the database.
   *
   * @param entity entity to be saved.
   * @return id after saving
   */
  public Y save(T entity) {
    if (entity == null) {
      throw new IllegalArgumentException("Entity cannot be null.");
    }

    if (entity.getId() == null) {
      entity.setId(nextID());
    }

    objects.put(entity.getId(), entity);

    return entity.getId();
  }

  public abstract Y nextID();

  /**
   * Retrieve the object with the identifier 'id', if any.
   *
   * @param id identifier of the object
   * @return object with identifier 'id'
   */
  public T findById(Y id) {
    return objects.get(id);
  }

  /**
   * Retrieve all the stored objects.
   *
   * @return all the stored objects.
   */
  public Collection<T> findAll() {
    return objects.values();
  }

  /**
   * Delete the object with the identifier 'id'.
   *
   * @param id identifier of the object
   */
  public void delete(Y id) {
    objects.remove(id);
  }
}
