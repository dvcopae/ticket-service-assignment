package me.dvcopae.tickets.persistance.repository;

import java.util.Collection;
import java.util.List;
import me.dvcopae.tickets.persistance.dto.PartialBooking;
import me.dvcopae.tickets.persistance.entity.Booking;
import me.dvcopae.tickets.persistance.entity.Service;

public final class BookingRepo extends IntegerCrudRepository<Booking> {

  private static final BookingRepo INSTANCE = new BookingRepo();

  public static BookingRepo getInstance() {
    return INSTANCE;
  }

  private BookingRepo() {
    super();
  }

  /**
   * Get all the bookings made for the given service.
   *
   * @param service service to restrict the bookings by.
   * @return bookings made for `service`
   */
  public List<Booking> findAllByService(Service service) {
    return this.findAll().stream()
        .filter(
            b ->
                b.getPartialBookings().stream()
                    .map(PartialBooking::tickets)
                    .flatMap(Collection::stream)
                    .anyMatch(t -> t.getService().equals(service)))
        .toList();
  }
}
