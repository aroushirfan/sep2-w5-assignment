package tests;

import cart.CartItem;
import cart.CartService;
import db.DatabaseConnection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.sql.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class CartServiceTest {

  private CartService cartService;
  private Connection mockConn;
  private PreparedStatement mockStmt;
  private ResultSet mockRs;

  @BeforeEach
  void setUp() {
    cartService = new CartService();
    mockConn = mock(Connection.class);
    mockStmt = mock(PreparedStatement.class);
    mockRs = mock(ResultSet.class);
  }

  @Test
  void testSaveCartRecord_returnsGeneratedId() throws Exception {
    when(mockConn.prepareStatement(anyString(), eq(Statement.RETURN_GENERATED_KEYS)))
        .thenReturn(mockStmt);
    when(mockStmt.getGeneratedKeys()).thenReturn(mockRs);
    when(mockRs.next()).thenReturn(true);
    when(mockRs.getInt(1)).thenReturn(42);

    try (MockedStatic<DatabaseConnection> dbMock = Mockito.mockStatic(DatabaseConnection.class)) {
      dbMock.when(DatabaseConnection::getConnection).thenReturn(mockConn);

      int id = cartService.saveCartRecord(3, 99.99, "en");
      assertEquals(42, id);
    }
  }

  @Test
  void testSaveCartRecord_noGeneratedKey_returnsMinusOne() throws Exception {
    when(mockConn.prepareStatement(anyString(), eq(Statement.RETURN_GENERATED_KEYS)))
        .thenReturn(mockStmt);
    when(mockStmt.getGeneratedKeys()).thenReturn(mockRs);
    when(mockRs.next()).thenReturn(false);

    try (MockedStatic<DatabaseConnection> dbMock = Mockito.mockStatic(DatabaseConnection.class)) {
      dbMock.when(DatabaseConnection::getConnection).thenReturn(mockConn);

      int id = cartService.saveCartRecord(3, 99.99, "en");
      assertEquals(-1, id);
    }
  }

  @Test
  void testSaveCartItems_executesForEachItem() throws Exception {
    when(mockConn.prepareStatement(anyString())).thenReturn(mockStmt);

    List<CartItem> items = Arrays.asList(
        new CartItem(1, 5.0, 2, 10.0),
        new CartItem(2, 3.0, 3, 9.0)
    );

    try (MockedStatic<DatabaseConnection> dbMock = Mockito.mockStatic(DatabaseConnection.class)) {
      dbMock.when(DatabaseConnection::getConnection).thenReturn(mockConn);

      cartService.saveCartItems(1, items);

      verify(mockStmt, times(2)).addBatch();
      verify(mockStmt).executeBatch();
    }
  }

  @Test
  void testSaveCartItems_emptyList_doesNotThrow() throws Exception {
    when(mockConn.prepareStatement(anyString())).thenReturn(mockStmt);

    try (MockedStatic<DatabaseConnection> dbMock = Mockito.mockStatic(DatabaseConnection.class)) {
      dbMock.when(DatabaseConnection::getConnection).thenReturn(mockConn);

      assertDoesNotThrow(() -> cartService.saveCartItems(1, Collections.emptyList()));
    }
  }
}
