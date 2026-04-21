package cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import db.DatabaseConnection;

public class LocalizationService {

  private static final Logger LOGGER = Logger.getLogger(LocalizationService.class.getName());

  public Map<String, String> loadLanguage(String lang) {
    Map<String, String> map = new HashMap<>();

    String sql = "SELECT `key`, value FROM localization_strings WHERE language = ?";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

      stmt.setString(1, lang);
      ResultSet rs = stmt.executeQuery();

      while (rs.next()) {
        map.put(rs.getString("key"), rs.getString("value"));
      }

    } catch (Exception e) {
      // ✔ Lambda for deferred string building
      LOGGER.log(Level.SEVERE, () -> "Failed to load language: " + lang);
      LOGGER.log(Level.SEVERE, "Exception occurred", e);
    }

    return map;
  }
}
