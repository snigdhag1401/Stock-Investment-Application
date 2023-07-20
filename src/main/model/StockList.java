package model;

import java.util.ArrayList;

// Represents a list of stocks
public class StockList {
    private ArrayList<Stock> stockItems;

    // EFFECTS: creates a new, empty list of stocks
    public StockList() {
        stockItems = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds given stock to the list of stocks
    public void addStock(Stock stock) {
        stockItems.add(stock);
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
        StockList investedIn = new StockList();
        for (Stock stockItem : stockItems) {
            if (stockItem.getInvestmentStatus()) {
                investedIn.addStock(stockItem);
            }
        }
        return investedIn;
    }

    // EFFECTS: returns list of all stocks that haven't been invested in
    public StockList notInvestedIn() {
        StockList notInvested = new StockList();
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

}
