package me.dvcopae.tickets.persistance.service;

import java.util.Set;
import me.dvcopae.tickets.exceptions.InvalidTicketException;
import me.dvcopae.tickets.persistance.entity.Carriage;
import me.dvcopae.tickets.persistance.entity.Service;
import me.dvcopae.tickets.persistance.entity.Station;
import me.dvcopae.tickets.persistance.entity.Ticket;
import me.dvcopae.tickets.persistance.repository.TicketRepo;

public class TicketingService extends RepositoryService<Ticket, Integer> {

  private final RoutingService routingService;

  protected TicketingService(TicketRepo repository, RoutingService routingService) {
    super(repository);
    this.routingService = routingService;
  }

  /**
   * Verify if the ticket has a valid configuration: - the service route contains both the origin
   * and destination station - the service carriages contains the ticket seat This does not check
   * the availability of the ticket within the service.
   *
   * @return true if the configuration is valid
   * @throws InvalidTicketException reason why the ticket is not valid
   */
  public boolean isValidRequest(String seat, Service service, Station origin, Station destination)
      throws InvalidTicketException {
    Set<Carriage> carriages = service.getCarriages();

    if (!routingService.verifyStations(service.getRoute(), origin, destination)) {
      throw new InvalidTicketException(
          String.format(
              "Ticket origin (%s) or destination (%s) are not valid for the service %d.%n",
              origin.getName(), destination.getName(), service.getId()));
    }

    if (carriages.stream()
        .noneMatch(
            c -> c.getFirstClassSeats().contains(seat) || c.getSecondClassSeats().contains(seat))) {
      throw new InvalidTicketException(
          String.format(
              "Ticket seat '%s' is not valid for the service %d.%n", seat, service.getId()));
    }

    return true;
  }
}
