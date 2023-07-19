package modelTest;

import model.Stock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StockTest {
    Stock testStock;

    @BeforeEach
    public void runBefore() {
        testStock = new Stock("Apple", "AAPL", true);
    }

    @Test
    public void testConstructor() {
        assertEquals("Apple", testStock.getStockName());
        assertEquals("AAPL", testStock.getTicker());
        assertTrue(testStock.getInvestmentStatus());
    }

}
