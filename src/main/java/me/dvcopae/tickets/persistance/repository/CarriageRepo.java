package me.dvcopae.tickets.persistance.repository;

import java.util.Set;
import me.dvcopae.tickets.persistance.entity.Carriage;

public final class CarriageRepo extends CrudRepository<Carriage, Integer> {

  private static final CarriageRepo INSTANCE = new CarriageRepo();

  public static CarriageRepo getInstance() {
    return INSTANCE;
  }

  private CarriageRepo() {
    super();
    initializeCarriages();
  }

  private void initializeCarriages() {
    var a = new Carriage(1, "A");
    a.setFirstClassSeats(Set.of("A10", "A11", "A12", "A13", "A14", "A15"));
    a.setSecondClassSeats(Set.of("A1", "A2", "A3", "A4", "A5"));
    save(a);

    var h = new Carriage(2, "H");
    h.setSecondClassSeats(Set.of("H1", "H2", "H3", "H4", "H5"));
    save(h);

    var n = new Carriage(3, "N");
    n.setSecondClassSeats(Set.of("N1", "N2", "N3", "N4", "N5"));
    save(n);

    var t = new Carriage(4, "T");
    t.setFirstClassSeats(Set.of("T6", "T7", "T8", "T9", "T10"));
    t.setSecondClassSeats(Set.of("T1", "T2", "T3", "T4", "T5"));
    save(t);
  }

  public Carriage findByName(String name) {
    if (name == null || name.isBlank()) {
      return null;
    }

    return findAll().stream().filter(c -> c.getName().equals(name)).findFirst().orElse(null);
  }
}
