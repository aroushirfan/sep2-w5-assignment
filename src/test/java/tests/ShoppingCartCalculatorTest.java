package tests;

import cart.ShoppingCartCalculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShoppingCartCalculatorTest {

    @Test
    void testCalculateItemCost_normal() {
        double result = ShoppingCartCalculator.calculateItemCost(10.0, 3);
        assertEquals(30.0, result);
    }

    @Test
    void testCalculateItemCost_zeroQuantity() {
        double result = ShoppingCartCalculator.calculateItemCost(10.0, 0);
        assertEquals(0.0, result);
    }

    @Test
    void testCalculateItemCost_zeroPrice() {
        double result = ShoppingCartCalculator.calculateItemCost(0.0, 5);
        assertEquals(0.0, result);
    }

    @Test
    void testCalculateItemCost_decimalPrice() {
        double result = ShoppingCartCalculator.calculateItemCost(2.50, 4);
        assertEquals(10.0, result, 0.001);
    }

    @Test
    void testAddToTotal_normal() {
        double result = ShoppingCartCalculator.addToTotal(50.0, 25.0);
        assertEquals(75.0, result);
    }

    @Test
    void testAddToTotal_zeroItemCost() {
        double result = ShoppingCartCalculator.addToTotal(100.0, 0.0);
        assertEquals(100.0, result);
    }

    @Test
    void testAddToTotal_bothZero() {
        double result = ShoppingCartCalculator.addToTotal(0.0, 0.0);
        assertEquals(0.0, result);
    }

    @Test
    void testAddToTotal_accumulation() {
        double total = 0.0;
        total = ShoppingCartCalculator.addToTotal(total, 10.0);
        total = ShoppingCartCalculator.addToTotal(total, 20.0);
        total = ShoppingCartCalculator.addToTotal(total, 5.50);
        assertEquals(35.50, total, 0.001);
    }
}
