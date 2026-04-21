package tests;

import cart.LocalizationService;
import db.DatabaseConnection;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class LocalizationServiceTest {

  @Test
  void testLoadLanguage_success() throws Exception {
    LocalizationService service = new LocalizationService();

    Connection mockConn = mock(Connection.class);
    PreparedStatement mockStmt = mock(PreparedStatement.class);
    ResultSet mockRs = mock(ResultSet.class);

    when(mockConn.prepareStatement(anyString())).thenReturn(mockStmt);
    when(mockStmt.executeQuery()).thenReturn(mockRs);

    when(mockRs.next()).thenReturn(true).thenReturn(false);
    when(mockRs.getString("key")).thenReturn("title");
    when(mockRs.getString("value")).thenReturn("Shopping Cart");

    try (MockedStatic<DatabaseConnection> dbMock = Mockito.mockStatic(DatabaseConnection.class)) {
      dbMock.when(DatabaseConnection::getConnection).thenReturn(mockConn);

      Map<String, String> result = service.loadLanguage("en");

      assertEquals(1, result.size());
      assertEquals("Shopping Cart", result.get("title"));
    }
  }

  @Test
  void testLoadLanguage_failureReturnsEmptyMap() {
    LocalizationService service = new LocalizationService();

    try (MockedStatic<DatabaseConnection> dbMock = Mockito.mockStatic(DatabaseConnection.class)) {
      dbMock.when(DatabaseConnection::getConnection)
          .thenThrow(new RuntimeException("DB error"));

      Map<String, String> result = service.loadLanguage("fi");

      assertTrue(result.isEmpty());
    }
  }
}
