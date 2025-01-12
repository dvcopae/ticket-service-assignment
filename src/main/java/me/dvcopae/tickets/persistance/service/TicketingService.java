package me.dvcopae.tickets.persistance.service;

import java.util.Set;
import me.dvcopae.tickets.exceptions.InvalidTicketException;
import me.dvcopae.tickets.persistance.dto.TicketRequest;
import me.dvcopae.tickets.persistance.entity.Carriage;
import me.dvcopae.tickets.persistance.entity.Service;
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
  public boolean isValidRequest(TicketRequest request) throws InvalidTicketException {
    Service service = request.service();
    Set<Carriage> carriages = service.getCarriages();

    if (!routingService.verifyStations(
        service.getRoute(), request.origin(), request.destination())) {
      throw new InvalidTicketException(
          "Ticket origin or destination are not valid for the service " + service.getId());
    }

    if (carriages.stream()
        .noneMatch(
            c ->
                c.getFirstClassSeats().contains(request.seat())
                    || c.getSecondClassSeats().contains(request.seat()))) {
      throw new InvalidTicketException(
          "Ticket seat is not valid for the service." + service.getId());
    }

    return true;
  }
}
