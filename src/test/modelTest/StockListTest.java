package modelTest;

import model.Stock;
import model.StockList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StockListTest {
    private StockList testStockList;
    private Stock testStock1;
    private Stock testStock2;
    private Stock testStock3;

    @BeforeEach
    public void runBefore() {
        testStockList = new StockList();
        testStock1 = new Stock("Apple", "AAPL", true);
        testStock2 = new Stock("Samsung", "KRX", false);
        testStock3 = new Stock("Tesla", "TSLA", true);
    }

    @Test
    public void testAddStock() {
        testStockList.addStock(testStock1);
        assertTrue(testStockList.getStockItems().contains(testStock1));
    }

    @Test
    public void testAddStockMultiple() {
        testStockList.addStock(testStock1);
        testStockList.addStock(testStock2);
        assertTrue(testStockList.containsStock(testStock1));
        assertTrue(testStockList.containsStock(testStock2));
    }

    @Test
    public void testRemoveStockTrueFalseValues() {
        testStockList.addStock(testStock1);
        assertTrue(testStockList.removeStock(testStock1));
        assertFalse(testStockList.removeStock(testStock2));
    }

    @Test
    public void testRemoveStock() {
        testStockList.addStock(testStock1);
        testStockList.removeStock(testStock1);
        assertFalse(testStockList.containsStock(testStock1));
    }

    @Test
    public void testContainsStock() {
        assertFalse(testStockList.containsStock(testStock1));
        testStockList.addStock(testStock1);
        assertTrue(testStockList.containsStock(testStock1));
    }

    @Test
    public void testFindStockItemWithOneItem() {
        testStockList.addStock(testStock1);
        assertEquals(testStock1, testStockList.findStockItem("Apple"));
    }

    @Test
    public void testFindStockItemWithMultipleItems() {
        testStockList.addStock(testStock1);
        testStockList.addStock(testStock2);
        testStockList.addStock(testStock3);
        assertEquals(testStock2, testStockList.findStockItem("Samsung"));
    }

    @Test
    public void testHaveInvestedIn() {
        testStockList.addStock(testStock1);
        testStockList.addStock(testStock2);
        testStockList.addStock(testStock3);
        assertTrue(testStockList.haveInvestedIn().contains(testStock1));
        assertFalse(testStockList.haveInvestedIn().contains(testStock2));
        assertTrue(testStockList.haveInvestedIn().contains(testStock3));
    }

    @Test
    public void testHaveInvestedInEmptyList() {
        assertEquals(0, testStockList.haveInvestedIn().size());
    }

    @Test
    public void testNotInvestedIn() {
        testStockList.addStock(testStock1);
        testStockList.addStock(testStock2);
        testStockList.addStock(testStock3);
        assertFalse(testStockList.notInvestedIn().contains(testStock1));
        assertTrue(testStockList.notInvestedIn().contains(testStock2));
        assertFalse(testStockList.notInvestedIn().contains(testStock3));
    }

    @Test
    public void testNotInvestedInEmptyList() {
        assertEquals(0, testStockList.notInvestedIn().size());
    }

}