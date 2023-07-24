package persistence;

import model.Stock;
import model.StockList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            StockList sl = new StockList("My stock list");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    void testWriterEmptyStockList() {
        try {
            StockList sl = new StockList("My stock list");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyStocklist.json");
            writer.open();
            writer.write(sl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyStocklist.json");
            sl = reader.read();
            assertEquals("My stock list", sl.getName());
        } catch (IOException e) {
            fail("Exception shouldn't have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            StockList sl = new StockList("My stock list");
            sl.addStock(new Stock("Apple", "AAPL", true));
            sl.addStock(new Stock("Tesla", "TSLA", false));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralStocklist.json");
            writer.open();
            writer.write(sl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralStocklist.json");
            sl = reader.read();
            assertEquals("My stock list", sl.getName());
            List<Stock> stockItems = sl.getStockItems();
            checkStock("Apple", "AAPL", true, 0.00, stockItems.get(0));
            checkStock("Tesla", "TSLA", false, 0.00, stockItems.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
