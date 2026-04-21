package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class DatabaseConnection {

  private static final Logger LOGGER = Logger.getLogger(DatabaseConnection.class.getName());
  private static final String HOST =
      System.getenv("DB_HOST") != null ? System.getenv("DB_HOST") : "localhost";

  private static final String USER =
      System.getenv("DB_USER") != null ? System.getenv("DB_USER") : "root";

  private static final String PASS =
      System.getenv("DB_PASSWORD") != null ? System.getenv("DB_PASSWORD") : "12345";

  private static final String URL =
      "jdbc:mysql://" + HOST + ":3306/shopping_cart_localization";


  private DatabaseConnection() {
  }

  public static Connection getConnection() {
    try {
      return DriverManager.getConnection(URL, USER, PASS);
    } catch (Exception e) {
      LOGGER.log(Level.SEVERE, "Database connection failed", e);
      return null;
    }
  }
}
