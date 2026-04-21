package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class DatabaseConnection {

  private static final Logger LOGGER = Logger.getLogger(DatabaseConnection.class.getName());

  private static final String URL = "jdbc:mysql://localhost:3306/shopping_cart_localization";
  private static final String USER = "root";

  private DatabaseConnection() { }

  public static Connection getConnection() {
    try {
      return DriverManager.getConnection(URL, USER, System.getenv("DB_PASSWORD"));
    } catch (Exception e) {
      // ✔ SonarQube‑approved logging (no System.err)
      LOGGER.log(Level.SEVERE, "Database connection failed", e);
      return null;
    }
  }
}
