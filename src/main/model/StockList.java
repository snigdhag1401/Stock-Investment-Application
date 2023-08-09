package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a list of stocks
public class StockList implements Writable {
    private String name;
    private ArrayList<Stock> stockItems;

    // EFFECTS: creates a new, empty list of stocks with a name
    public StockList(String name) {
        this.name = name;
        stockItems = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: adds given stock to the list of stocks
    public void addStock(Stock stock) {
        stockItems.add(stock);
        EventLog.getInstance().logEvent(new Event("Added stock " + stock.getStockName() + " to list!"));
    }

    // MODIFIES: this
    // EFFECTS: removes given stock from the list of stocks and returns true,
    // if stock not in list then returns false
    public boolean removeStock(Stock stock) {
        return stockItems.remove(stock);
    }

    // EFFECTS: checks if a stock with given name is in list and returns the stock,
    // else returns null
    public Stock findStockItem(String name) {
        Stock stockToFind = null;
        for (Stock stock : stockItems) {
            if (stock.getStockName().equalsIgnoreCase(name)) {
                stockToFind = stock;
                break;
            }
        }
        return stockToFind;
    }

    // EFFECTS: returns list of all stocks that have been invested in
    public StockList haveInvestedIn() {
        StockList investedIn = new StockList("Invested in stocks");
        investedIn.getStockItems().addAll(stockItems);
        for (Stock stockItem : stockItems) {
            if (!stockItem.getInvestmentStatus()) {
                investedIn.removeStock(stockItem);
            }
        }
        EventLog.getInstance().logEvent(new Event("Viewed invested stocks"));
        return investedIn;
    }

    // EFFECTS: returns list of all stocks that haven't been invested in
    public StockList notInvestedIn() {
        StockList notInvested = new StockList("Not invested in stocks");
        for (Stock stockItem : stockItems) {
            if (!stockItem.getInvestmentStatus()) {
                notInvested.addStock(stockItem);
            }
        }
        return notInvested;
    }

    // EFFECTS: returns the number of stocks in the list of stocks
    public int getSize() {
        return stockItems.size();
    }

    public ArrayList<Stock> getStockItems() {
        return this.stockItems;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("stockItems", stockItemsToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray stockItemsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Stock s : stockItems) {
            jsonArray.put(s.toJson());
        }

        return jsonArray;
    }

}
