package me.dvcopae.tickets.persistance.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import me.dvcopae.tickets.exceptions.BookingException;
import me.dvcopae.tickets.persistance.dto.PartialBooking;
import me.dvcopae.tickets.persistance.dto.TicketRequest;
import me.dvcopae.tickets.persistance.entity.Booking;
import me.dvcopae.tickets.persistance.entity.Carriage;
import me.dvcopae.tickets.persistance.entity.Passenger;
import me.dvcopae.tickets.persistance.entity.Route;
import me.dvcopae.tickets.persistance.entity.Service;
import me.dvcopae.tickets.persistance.entity.Station;
import me.dvcopae.tickets.persistance.entity.Ticket;
import me.dvcopae.tickets.persistance.repository.BookingRepo;
import me.dvcopae.tickets.persistance.repository.CarriageRepo;
import me.dvcopae.tickets.persistance.repository.PassengerRepo;
import me.dvcopae.tickets.persistance.repository.RouteRepo;
import me.dvcopae.tickets.persistance.repository.ServiceRepo;
import me.dvcopae.tickets.persistance.repository.StationRepo;
import me.dvcopae.tickets.persistance.repository.TicketRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SystemTest {

  private BookingService bookingService;
  private PassengerService passengerService;
  private StationService stationService;
  private ServiceService serviceService;
  private CarriageService carriageService;
  private RoutingService routingService;

  @Spy private BookingRepo bookingRepo;
  @Spy private PassengerRepo passengerRepo;
  @Spy private StationRepo stationRepo;
  @Spy private ServiceRepo serviceRepo;
  @Spy private CarriageRepo carriageRepo;
  @Spy private RouteRepo routeRepo;
  @Spy private TicketRepo ticketRepo;

  @BeforeEach
  void setUp() {
    stationService = new StationService(stationRepo);
    serviceService = new ServiceService(serviceRepo);
    carriageService = new CarriageService(carriageRepo);
    routingService = new RoutingService(routeRepo);
    passengerService = new PassengerService(passengerRepo);

    TicketingService ticketingService = new TicketingService(ticketRepo, routingService);
    bookingService = new BookingService(bookingRepo, ticketingService, routingService);

    setUpMockBehavior();
  }

  private void setUpMockBehavior() {
    setUpStationsMock();
    setUpRoutesMock();
    setUpCarriagesMock();
    setUpPassengersMock();
    setUpServiceMock();
  }

  private void setUpStationsMock() {
    // Paris - London
    Station paris = new Station("Paris Gare du Nord");
    Station lille = new Station("Lille Europe");
    Station calais = new Station("Calais-Fréthun");
    Station ebbsfleet = new Station("Ebbsfleet International");
    Station ashford = new Station("Ashford International");
    Station london = new Station("London St. Pancras International");

    // Paris - Amsterdam
    // Belgium - Amsterdam
    Station brussels = new Station("Brussels-Midi/Zuid");
    Station antwerp = new Station("Antwerp-Central");
    Station rotterdam = new Station("Rotterdam Centraal");
    Station schiphol = new Station("Schiphol Airport");
    Station amsterdam = new Station("Amsterdam Centraal");

    when(stationRepo.findByName("Paris Gare du Nord")).thenReturn(Optional.of(paris));
    when(stationRepo.findByName("Lille Europe")).thenReturn(Optional.of(lille));
    when(stationRepo.findByName("Calais-Fréthun")).thenReturn(Optional.of(calais));
    when(stationRepo.findByName("Ebbsfleet International")).thenReturn(Optional.of(ebbsfleet));
    when(stationRepo.findByName("Ashford International")).thenReturn(Optional.of(ashford));
    when(stationRepo.findByName("London St. Pancras International"))
        .thenReturn(Optional.of(london));

    when(stationRepo.findByName("Brussels-Midi/Zuid")).thenReturn(Optional.of(brussels));
    when(stationRepo.findByName("Antwerp-Central")).thenReturn(Optional.of(antwerp));
    when(stationRepo.findByName("Rotterdam Centraal")).thenReturn(Optional.of(rotterdam));
    when(stationRepo.findByName("Schiphol Airport")).thenReturn(Optional.of(schiphol));
    when(stationRepo.findByName("Amsterdam Centraal")).thenReturn(Optional.of(amsterdam));
  }

  private void setUpServiceMock() {
    var parisToAmsterdam = routingService.findByName("Paris-Amsterdam").orElseThrow();
    var carriageA = carriageService.findByName("A").orElseThrow();
    var carriageT = carriageService.findByName("T").orElseThrow();

    LocalDate date = LocalDate.of(2021, 4, 1);
    var s5160 = new Service(5160, Set.of(carriageA, carriageT), parisToAmsterdam, date);
    when(serviceService.findById(5160)).thenReturn(Optional.of(s5160));

    var londonToParis = routingService.findByName("Paris-London").orElseThrow();
    var carriageN = carriageService.findByName("N").orElseThrow();
    var carriageH = carriageService.findByName("H").orElseThrow();

    var s5170 = new Service(5170, Set.of(carriageN, carriageH), londonToParis, date);
    Mockito.lenient()
        .when(serviceService.findById(5170))
        .thenReturn(Optional.of(s5170)); // not needed in all test cases, so silence warning
  }

  private void setUpRoutesMock() {
    // Paris -> London
    Station paris = stationService.findByName("Paris Gare du Nord").orElseThrow();
    Station lille = stationService.findByName("Lille Europe").orElseThrow();
    Station calais = stationService.findByName("Calais-Fréthun").orElseThrow();
    Station ebbsfleet = stationService.findByName("Ebbsfleet International").orElseThrow();
    Station ashford = stationService.findByName("Ashford International").orElseThrow();
    Station london = stationService.findByName("London St. Pancras International").orElseThrow();

    Route parisToLondon = new Route();
    parisToLondon.setName("Paris-London");
    parisToLondon.addStop(paris, lille, 100.0f);
    parisToLondon.addStop(lille, calais, 80.0f);
    parisToLondon.addStop(calais, ebbsfleet, 50.0f);
    parisToLondon.addStop(ebbsfleet, ashford, 60.0f);
    parisToLondon.addStop(ashford, london, 90.0f);

    // Paris -> Amsterdam
    Station brusssels = stationService.findByName("Brussels-Midi/Zuid").orElseThrow();
    Station antwerp = stationService.findByName("Antwerp-Central").orElseThrow();
    Station rotterdam = stationService.findByName("Rotterdam Centraal").orElseThrow();
    Station schiphol = stationService.findByName("Schiphol Airport").orElseThrow();
    Station amsterdam = stationService.findByName("Amsterdam Centraal").orElseThrow();

    Route parisToAmsterdam = new Route();
    parisToAmsterdam.setName("Paris-Amsterdam");
    parisToAmsterdam.addStop(paris, brusssels, 300.0f);
    parisToAmsterdam.addStop(brusssels, antwerp, 45.0f);
    parisToAmsterdam.addStop(antwerp, rotterdam, 95.0f);
    parisToAmsterdam.addStop(rotterdam, schiphol, 60.0f);
    parisToAmsterdam.addStop(schiphol, amsterdam, 15.0f);

    // Amsterdam - Berlin
    Route amsterdamToBerlin = new Route();
    amsterdamToBerlin.setName("Amsterdam-Berlin");
    amsterdamToBerlin.addStop(amsterdam, schiphol, 15.0f);
    amsterdamToBerlin.addStop(schiphol, rotterdam, 60.0f);
    amsterdamToBerlin.addStop(rotterdam, antwerp, 95.0f);
    amsterdamToBerlin.addStop(antwerp, brusssels, 45.0f);

    when(routeRepo.findByName("Paris-London")).thenReturn(Optional.of(parisToLondon));
    when(routeRepo.findByName("Paris-Amsterdam")).thenReturn(Optional.of(parisToAmsterdam));
  }

  private void setUpCarriagesMock() {
    var a = new Carriage("A");
    a.setFirstClassSeats(Set.of("A10", "A11", "A12", "A13", "A14", "A15"));
    a.setSecondClassSeats(Set.of("A1", "A2", "A3", "A4", "A5"));

    var h = new Carriage("H");
    h.setSecondClassSeats(Set.of("H1", "H2", "H3", "H4", "H5"));

    var n = new Carriage("N");
    n.setFirstClassSeats(Set.of("N1", "N2", "N3", "N4", "N5"));

    var t = new Carriage("T");
    t.setFirstClassSeats(Set.of("T6", "T7", "T8", "T9", "T10"));

    when(carriageRepo.findByName("A")).thenReturn(Optional.of(a));
    when(carriageRepo.findByName("H")).thenReturn(Optional.of(h));
    when(carriageRepo.findByName("N")).thenReturn(Optional.of(n));
    when(carriageRepo.findByName("T")).thenReturn(Optional.of(t));
  }

  private void setUpPassengersMock() {
    Passenger michael = new Passenger("Michael");
    Passenger john = new Passenger("John");

    when(passengerRepo.findByName("Michael")).thenReturn(Optional.of(michael));
    when(passengerRepo.findByName("John")).thenReturn(Optional.of(john));
  }

  @Test
  void testTwoReservationOnSecondClass() throws BookingException {
    Map<Passenger, List<TicketRequest>> bookingRequest = new HashMap<>();
    Service s5160 = serviceService.findById(5160).orElseThrow();
    Station paris = stationService.findByName("Paris Gare du Nord").orElseThrow();
    Station amsterdam = stationService.findByName("Amsterdam Centraal").orElseThrow();
    TicketRequest reqA11ParisAmsterdam = new TicketRequest(s5160, "A11", paris, amsterdam);
    TicketRequest reqA12ParisAmsterdam = new TicketRequest(s5160, "A12", paris, amsterdam);

    bookingRequest.put(
        passengerService.findByName("Michael").orElseThrow(), List.of(reqA11ParisAmsterdam));
    bookingRequest.put(
        passengerService.findByName("John").orElseThrow(), List.of(reqA12ParisAmsterdam));

    Booking book = bookingService.createBooking(bookingRequest);

    // Verify Michael
    PartialBooking michaelBooking =
        book.getPartialBookings().stream()
            .filter(b -> b.passenger().getName().equals("Michael"))
            .findFirst()
            .orElseThrow();

    assertEquals("A11", michaelBooking.tickets().getFirst().getSeat());
    assertEquals(1, michaelBooking.tickets().size());
    Ticket michaelParisAmsterdamTicket = michaelBooking.tickets().getFirst();
    assertEquals("Paris Gare du Nord", michaelParisAmsterdamTicket.getOrigin().getName());
    assertEquals("Amsterdam Centraal", michaelParisAmsterdamTicket.getDestination().getName());
    assertTrue(michaelParisAmsterdamTicket.isFirstClass());

    // Verify John
    PartialBooking johnBooking =
        book.getPartialBookings().stream()
            .filter(b -> b.passenger().getName().equals("John"))
            .findFirst()
            .orElseThrow();

    assertEquals("A12", johnBooking.tickets().getFirst().getSeat());
    assertEquals(1, johnBooking.tickets().size());
    Ticket johnParisAmsterdamTicket = johnBooking.tickets().getFirst();
    assertEquals("Paris Gare du Nord", johnParisAmsterdamTicket.getOrigin().getName());
    assertEquals("Amsterdam Centraal", johnParisAmsterdamTicket.getDestination().getName());
    assertTrue(johnParisAmsterdamTicket.isFirstClass());
  }

  @Test
  void testRepeatedReservationOnSecondClass() {
    testTwoReservationOnSecondClass();
    BookingException exception =
        assertThrows(
            BookingException.class,
            this::testTwoReservationOnSecondClass,
            "Expected BookingException to be thrown when reserving the same seat twice");

    assertEquals("The requested seat is not available", exception.getMessage());
  }

  @Test
  void testTwoReservationOnSeparateRoute() throws BookingException {
    Map<Passenger, List<TicketRequest>> bookingRequest = new HashMap<>();

    Service s5170 = serviceService.findById(5170).orElseThrow();
    Station paris = stationService.findByName("Paris Gare du Nord").orElseThrow();
    Station london = stationService.findByName("London St. Pancras International").orElseThrow();
    TicketRequest reqH1LondonParis = new TicketRequest(s5170, "H1", london, paris);
    TicketRequest reqN5LondonParis = new TicketRequest(s5170, "N5", london, paris);

    Service s5160 = serviceService.findById(5160).orElseThrow();
    Station amsterdam = stationService.findByName("Amsterdam Centraal").orElseThrow();
    TicketRequest reqA1ParisAmsterdam = new TicketRequest(s5160, "A1", paris, amsterdam);
    TicketRequest reqT7ParisAmsterdam = new TicketRequest(s5160, "T7", paris, amsterdam);

    bookingRequest.put(
        passengerService.findByName("Michael").orElseThrow(),
        List.of(reqH1LondonParis, reqA1ParisAmsterdam));

    bookingRequest.put(
        passengerService.findByName("John").orElseThrow(),
        List.of(reqN5LondonParis, reqT7ParisAmsterdam));

    Booking book = bookingService.createBooking(bookingRequest);

    // Verify Michael
    PartialBooking michaelBooking =
        book.getPartialBookings().stream()
            .filter(b -> b.passenger().getName().equals("Michael"))
            .findFirst()
            .orElseThrow();

    List<Ticket> michaelTickets = michaelBooking.tickets();
    assertEquals(2, michaelTickets.size());

    Ticket londonParisTicket =
        michaelTickets.stream().filter(t -> t.getSeat().equals("H1")).findFirst().orElseThrow();
    assertEquals("London St. Pancras International", londonParisTicket.getOrigin().getName());
    assertEquals("Paris Gare du Nord", londonParisTicket.getDestination().getName());
    assertTrue(londonParisTicket.isSecondClass());

    Ticket parisAmsterdamTicket =
        michaelTickets.stream().filter(t -> t.getSeat().equals("A1")).findFirst().orElseThrow();
    assertEquals("Paris Gare du Nord", parisAmsterdamTicket.getOrigin().getName());
    assertEquals("Amsterdam Centraal", parisAmsterdamTicket.getDestination().getName());
    assertTrue(parisAmsterdamTicket.isSecondClass());

    // Verify John
    PartialBooking johnBooking =
        book.getPartialBookings().stream()
            .filter(b -> b.passenger().getName().equals("John"))
            .findFirst()
            .orElseThrow();

    List<Ticket> johnTickets = johnBooking.tickets();
    assertEquals(2, johnTickets.size());

    Ticket johnLondonParisTicket =
        johnTickets.stream().filter(t -> t.getSeat().equals("N5")).findFirst().orElseThrow();
    assertEquals("London St. Pancras International", johnLondonParisTicket.getOrigin().getName());
    assertEquals("Paris Gare du Nord", johnLondonParisTicket.getDestination().getName());
    assertTrue(johnLondonParisTicket.isFirstClass());

    Ticket johnParisAmsterdamTicket =
        johnTickets.stream().filter(t -> t.getSeat().equals("T7")).findFirst().orElseThrow();
    assertEquals("Paris Gare du Nord", johnParisAmsterdamTicket.getOrigin().getName());
    assertEquals("Amsterdam Centraal", johnParisAmsterdamTicket.getDestination().getName());
    assertTrue(johnParisAmsterdamTicket.isFirstClass());
  }

  @Test
  void testRepeatedReservationOnSeparateRoute() {
    testTwoReservationOnSeparateRoute();
    BookingException exception =
        assertThrows(
            BookingException.class,
            this::testTwoReservationOnSeparateRoute,
            "Expected BookingException to be thrown when reserving the same seat twice");

    assertEquals("The requested seat is not available", exception.getMessage());
  }
}
