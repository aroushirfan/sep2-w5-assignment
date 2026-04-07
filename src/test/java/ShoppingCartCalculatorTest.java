import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartCalculatorTest {

    @Test
    void testCalculateItemCost() {
        assertEquals(10.0, ShoppingCartCalculator.calculateItemCost(2.0, 5));
        assertEquals(0.0, ShoppingCartCalculator.calculateItemCost(0.0, 10));
        assertEquals(15.0, ShoppingCartCalculator.calculateItemCost(3.0, 5));
        assertEquals(0.0, ShoppingCartCalculator.calculateItemCost(5.0, 0));
        assertEquals(7.5, ShoppingCartCalculator.calculateItemCost(2.5, 3));
    }

    @Test
    void testAddToTotal() {
        assertEquals(25.0, ShoppingCartCalculator.addToTotal(10.0, 15.0));
        assertEquals(10.0, ShoppingCartCalculator.addToTotal(10.0, 0.0));
        assertEquals(30.0, ShoppingCartCalculator.addToTotal(20.0, 10.0));
        assertEquals(5.0, ShoppingCartCalculator.addToTotal(0.0, 5.0));
        assertEquals(100.0, ShoppingCartCalculator.addToTotal(40.0, 60.0));
    }
}