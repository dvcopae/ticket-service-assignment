package me.dvcopae.tickets.persistance.entity;

import java.util.List;
import me.dvcopae.tickets.persistance.dto.PartialBooking;

/**
 * A collection of passenger information and seat reservations. Immutable by design. A booking is a
 * collection of partial bookings.
 */
public final class Booking implements Entity<Integer> {

  private Integer id;

  private List<PartialBooking> partialBookings;

  @Override
  public Integer getId() {
    return id;
  }

  @Override
  public void setId(Integer id) {
    this.id = id;
  }

  public List<PartialBooking> getPartialBookings() {
    return partialBookings;
  }

  public void setPartialBookings(List<PartialBooking> partialBookings) {
    this.partialBookings = partialBookings;
  }

  @Override
  public String toString() {
    return "Booking{" + "id=" + id + ", partialBookings=" + partialBookings + '}';
  }
}
