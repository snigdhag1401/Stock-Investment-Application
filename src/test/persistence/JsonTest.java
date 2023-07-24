package persistence;

import model.Stock;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkStock(String name, String ticker, boolean hasInvested, double sharePrice, Stock stock) {
        assertEquals(name, stock.getStockName());
        assertEquals(ticker, stock.getTicker());
        assertEquals(hasInvested, stock.getInvestmentStatus());
        assertEquals(sharePrice, stock.getSharePrice());
    }
}
