package me.dvcopae.tickets.persistance.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import me.dvcopae.tickets.persistance.dto.PartialBooking;
import me.dvcopae.tickets.persistance.entity.Booking;
import me.dvcopae.tickets.persistance.entity.Service;
import me.dvcopae.tickets.persistance.entity.Station;

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
  public List<Booking> findAllByService(Integer service) {
    return this.findAll().stream()
        .filter(
            b ->
                b.getPartialBookings().stream()
                    .map(PartialBooking::tickets)
                    .flatMap(Collection::stream)
                    .anyMatch(t -> t.getService().getId().equals(service)))
        .toList();
  }

  public List<PartialBooking> findAllByServiceAndOrigin(Integer service, String station) {
    return this.findAll().stream()
        .flatMap(b -> b.getPartialBookings().stream())
        .filter(
            pb ->
                pb.tickets().stream()
                    .anyMatch(
                        t ->
                            t.getService().getId().equals(service)
                                && t.getOrigin().getName().equals(station)))
        .toList();
  }

  public List<PartialBooking> findAllByServiceAndDestination(Integer service, String station) {
    return this.findAll().stream()
        .flatMap(b -> b.getPartialBookings().stream())
        .filter(
            pb ->
                pb.tickets().stream()
                    .anyMatch(
                        t ->
                            t.getService().getId().equals(service)
                                && t.getDestination().getName().equals(station)))
        .toList();
  }

  public List<PartialBooking> findAllByServiceAndBetweenStations(
      Integer service, String startStation, String stopStation) {
    return this.findAll().stream()
        .flatMap(b -> b.getPartialBookings().stream())
        .filter(
            pb ->
                pb.tickets().stream()
                    .anyMatch(
                        t -> {
                          Service s = t.getService();
                          if (s.getId().equals(service)) {
                            List<String> stationNames =
                                s.getRoute().getStations().stream().map(Station::getName).toList();

                            int ticketOrigin = stationNames.indexOf(t.getOrigin().getName());
                            int ticketDestination =
                                stationNames.indexOf(t.getDestination().getName());
                            int reqOrigin = stationNames.indexOf(startStation);
                            int reqDestination = stationNames.indexOf(stopStation);

                            // the requested stations must be completely within the ticket stations
                            // for it to count

                            boolean isValid =
                                ticketOrigin <= reqOrigin && ticketDestination >= reqDestination;
                            boolean isValidReverse =
                                ticketDestination <= reqOrigin && ticketOrigin >= reqDestination;
                            return isValid || isValidReverse;
                          }

                          return false;
                        }))
        .toList();
  }

  public Optional<PartialBooking> findByServiceAndSeatAndStation(
      Integer service, String seat, String station) {
    return this.findAll().stream()
        .flatMap(b -> b.getPartialBookings().stream())
        .filter(
            pb ->
                pb.tickets().stream()
                    .anyMatch(
                        t -> {
                          Service s = t.getService();
                          if (s.getId().equals(service) && t.getSeat().equals(seat)) {
                            List<String> stationNames =
                                s.getRoute().getStations().stream().map(Station::getName).toList();

                            int ticketOrigin = stationNames.indexOf(t.getOrigin().getName());
                            int ticketDestination =
                                stationNames.indexOf(t.getDestination().getName());
                            int stationIndex = stationNames.indexOf(station);

                            // the stations must be within the ticket stations for it to count
                            boolean isValid =
                                ticketOrigin <= stationIndex && ticketDestination >= stationIndex;

                            // origin and destination can be swapped if the route goes the other way
                            boolean isValidReverse =
                                ticketDestination <= stationIndex && ticketOrigin >= stationIndex;

                            return isValid || isValidReverse;
                          }

                          return false;
                        }))
        .findFirst();
  }
}
