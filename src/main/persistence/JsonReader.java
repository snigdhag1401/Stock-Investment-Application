package persistence;

import model.Stock;
import model.StockList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads StockList from JSON data stored in file
// The following code was written by referring to the provided JsonSerializationDemo
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads StockList from file and returns it;
    // throws IOException if an error occurs reading data from file
    public StockList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseStockList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses stocklist from JSON object and returns it
    private StockList parseStockList(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        StockList sl = new StockList(name);
        addStocks(sl, jsonObject);
        return sl;
    }

    // MODIFIES: sl
    // EFFECTS: parses stocks from JSON object and adds them to stocklist
    private void addStocks(StockList sl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("stockItems");
        for (Object json : jsonArray) {
            JSONObject nextStock = (JSONObject) json;
            addStock(sl, nextStock);
        }
    }

    // MODIFIES: sl
    // EFFECTS: parses stock from JSON object and adds it to stocklist
    private void addStock(StockList sl, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String ticker = jsonObject.getString("ticker");
        boolean hasInvested = jsonObject.getBoolean("hasInvested");
        double sharePrice = jsonObject.getDouble("sharePrice");
        Stock stock = new Stock(name, ticker, hasInvested);
        stock.setSharePrice(sharePrice);
        sl.addStock(stock);
    }
}
