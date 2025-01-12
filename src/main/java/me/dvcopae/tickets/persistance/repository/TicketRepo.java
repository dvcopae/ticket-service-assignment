package me.dvcopae.tickets.persistance.repository;

import me.dvcopae.tickets.persistance.entity.Ticket;

public final class TicketRepo extends CrudRepository<Ticket, Integer> {

  private static final TicketRepo INSTANCE = new TicketRepo();

  public static TicketRepo getInstance() {
    return INSTANCE;
  }

  private TicketRepo() {
    super();
  }
}
