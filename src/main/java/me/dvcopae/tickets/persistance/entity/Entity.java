package me.dvcopae.tickets.persistance.entity;

public interface Entity<Y> {
  Y getId();

  void setId(Y id);
}
