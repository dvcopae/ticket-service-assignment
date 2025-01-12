package me.dvcopae.tickets.persistance;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import me.dvcopae.tickets.exceptions.DatabaseSetupException;

public final class DB {

  private static final DB INSTANCE = new DB();
  private final Properties prop = new Properties();
  private Connection conn;

  public static DB get() {
    return INSTANCE;
  }

  public DB() {
    try {
      initConnection();
    } catch (SQLException e) {
      throw new DatabaseSetupException(e);
    }
  }

  private void initConnection() throws SQLException {
    try (InputStream input = DB.class.getClassLoader().getResourceAsStream("db.properties")) {
      if (input == null) {
        System.out.println("Unable to find db.properties.");
        System.exit(1);
      }
      prop.load(input);
    } catch (IOException e) {
      throw new DatabaseSetupException(e);
    }

    Properties connectionProps = new Properties();
    connectionProps.put("user", prop.getProperty("db.username"));
    connectionProps.put("password", prop.getProperty("db.password"));

    conn = DriverManager.getConnection(prop.getProperty("db.url"), connectionProps);
  }

  public String checkConnection() throws SQLException {
    if (conn == null) {
      initConnection();
    }

    try (var preparedStatement = conn.prepareStatement("SELECT version()")) {
      var rs = preparedStatement.executeQuery();

      if (rs.next()) {
        String version = rs.getString(1);
        rs.close();
        return version;
      }

      return null;
    }
  }
}
