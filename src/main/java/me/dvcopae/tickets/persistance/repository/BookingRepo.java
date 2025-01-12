package me.dvcopae.tickets.persistance.repository;

import me.dvcopae.tickets.persistance.entity.Booking;

public final class BookingRepo extends CrudRepository<Booking, Integer> {

  private static final BookingRepo INSTANCE = new BookingRepo();

  public static BookingRepo getInstance() {
    return INSTANCE;
  }

  private BookingRepo() {
    super();
  }
}
