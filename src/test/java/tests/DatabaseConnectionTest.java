package tests;

import db.DatabaseConnection;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.DriverManager;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;

class DatabaseConnectionTest {

  @Test
  void testGetConnection_success() {
    Connection mockConn = mock(Connection.class);

    try (MockedStatic<DriverManager> dm = Mockito.mockStatic(DriverManager.class)) {
      dm.when(() -> DriverManager.getConnection(anyString(), any(), any()))
          .thenReturn(mockConn);

      Connection result = DatabaseConnection.getConnection();

      assertNotNull(result);
      assertEquals(mockConn, result);
    }
  }

  @Test
  void testGetConnection_failureReturnsNull() {
    try (MockedStatic<DriverManager> dm = Mockito.mockStatic(DriverManager.class)) {
      dm.when(() -> DriverManager.getConnection(anyString(), any(), any()))
          .thenThrow(new RuntimeException("DB error"));

      Connection result = DatabaseConnection.getConnection();

      assertNull(result);
    }
  }
}
