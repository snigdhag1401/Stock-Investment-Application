package model;

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
        testStockList = new StockList("test Stocklist");
        testStock1 = new Stock("Apple", "AAPL", true);
        testStock2 = new Stock("Samsung", "KRX", false);
        testStock3 = new Stock("Tesla", "TSLA", true);
    }

    @Test
    public void testConstructor() {
        assertEquals(0, testStockList.getSize());
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
        assertTrue(testStockList.getStockItems().contains(testStock1));
        assertTrue(testStockList.getStockItems().contains(testStock2));
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
        assertFalse(testStockList.getStockItems().contains(testStock1));
    }

    @Test
    public void testRemoveStockMultiple() {
        testStockList.addStock(testStock1);
        testStockList.addStock(testStock2);
        testStockList.addStock(testStock3);
        testStockList.removeStock(testStock2);
        assertFalse(testStockList.getStockItems().contains(testStock2));
    }

    @Test
    public void testContainsStock() {
        assertFalse(testStockList.getStockItems().contains(testStock1));
        testStockList.addStock(testStock1);
        assertTrue(testStockList.getStockItems().contains(testStock1));
    }

    @Test
    public void testContainsStockWithAddAndRemoveStock() {
        assertFalse(testStockList.getStockItems().contains(testStock1));
        testStockList.addStock(testStock1);
        assertTrue(testStockList.getStockItems().contains(testStock1));
        testStockList.removeStock(testStock1);
        assertFalse(testStockList.getStockItems().contains(testStock1));
    }

    @Test
    public void testFindStockItemWithNoItem() {
        assertNull(testStockList.findStockItem("Apple"));
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
        assertTrue(testStockList.haveInvestedIn().getStockItems().contains(testStock1));
        assertFalse(testStockList.haveInvestedIn().getStockItems().contains(testStock2));
        assertTrue(testStockList.haveInvestedIn().getStockItems().contains(testStock3));
        assertEquals(2, testStockList.haveInvestedIn().getSize());
    }

    @Test
    public void testHaveInvestedInEmptyList() {
        assertEquals(0, testStockList.haveInvestedIn().getSize());
    }

    @Test
    public void testNotInvestedIn() {
        testStockList.addStock(testStock1);
        testStockList.addStock(testStock2);
        testStockList.addStock(testStock3);
        assertFalse(testStockList.notInvestedIn().getStockItems().contains(testStock1));
        assertTrue(testStockList.notInvestedIn().getStockItems().contains(testStock2));
        assertFalse(testStockList.notInvestedIn().getStockItems().contains(testStock3));
        assertEquals(1, testStockList.notInvestedIn().getSize());
    }

    @Test
    public void testNotInvestedInEmptyList() {
        assertEquals(0, testStockList.notInvestedIn().getSize());
    }

}