package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class DatabaseConnection {

  private static final Logger LOGGER = Logger.getLogger(DatabaseConnection.class.getName());

  private static final String URL = "jdbc:mysql://db:3306/shopping_cart_localization";
  private static final String USER = System.getenv("DB_USER");
  private static final String PASS = System.getenv("DB_PASSWORD");
  private DatabaseConnection() { }

  public static Connection getConnection() {
    try {
      return DriverManager.getConnection(URL, USER, PASS);
    } catch (Exception e) {
      LOGGER.log(Level.SEVERE, "Database connection failed", e);
      return null;
    }
  }
}
