package persistence;

import model.Stock;
import model.StockList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            StockList sl = reader.read();
            fail("IOException expected!");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    void testReaderEmptyStockList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyStockList.json");
        try {
            StockList sl = reader.read();
            assertEquals("My stock list", sl.getName());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralStockList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralStockList.json");
        try {
            StockList sl = reader.read();
            assertEquals("My stock list", sl.getName());
            List<Stock> stockItems = sl.getStockItems();
            checkStock("Apple", "AAPL", true, 5.00, stockItems.get(0));
            checkStock("Tesla", "TSLA", false, 4.00, stockItems.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
