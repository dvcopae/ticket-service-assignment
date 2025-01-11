package me.dvcopae.tickets.persistance;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.SQLException;
import org.junit.jupiter.api.Test;

class DBTest {

  @Test
  void testDB() throws SQLException {
    var db = DB.get();
    assertNotNull(db.checkConnection());
  }
}
