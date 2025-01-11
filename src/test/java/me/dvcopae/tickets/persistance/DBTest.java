package me.dvcopae.tickets.persistance;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class DBTest {

    @Test
    void testDB() throws SQLException {
        var db = DB.get();
        assertNotNull(db.checkConnection());
    }
}
