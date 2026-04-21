package tests;

import cart.CartItem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CartItemTest {

    @Test
    void testConstructorAndGetters() {
        CartItem item = new CartItem(1, 9.99, 3, 29.97);

        assertEquals(1, item.getItemNumber());
        assertEquals(9.99, item.getPrice());
        assertEquals(3, item.getQuantity());
        assertEquals(29.97, item.getSubtotal());
    }

    @Test
    void testZeroValues() {
        CartItem item = new CartItem(0, 0.0, 0, 0.0);

        assertEquals(0, item.getItemNumber());
        assertEquals(0.0, item.getPrice());
        assertEquals(0, item.getQuantity());
        assertEquals(0.0, item.getSubtotal());
    }

    @Test
    void testLargeValues() {
        CartItem item = new CartItem(999, 1999.99, 100, 199999.0);

        assertEquals(999, item.getItemNumber());
        assertEquals(1999.99, item.getPrice());
        assertEquals(100, item.getQuantity());
        assertEquals(199999.0, item.getSubtotal());
    }
}
