package model;

import java.util.ArrayList;

// Represents a list of stocks
public class StockList {
    private ArrayList<Stock> stockItems;

    // EFFECTS: creates a new, empty list of stocks
    public StockList() {
        stockItems = new ArrayList<Stock>();
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

    // EFFECTS: returns true if given stock is in list, false otherwise
    public boolean containsStock(Stock stock) {
        return stockItems.contains(stock);
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
    public ArrayList<Stock> haveInvestedIn() {
        ArrayList<Stock> investedIn = new ArrayList<Stock>();
        for (Stock stockItem : stockItems) {
            if (stockItem.getInvestmentStatus()) {
                investedIn.add(stockItem);
            }
        }
        return investedIn;
    }

    // EFFECTS: returns list of all stocks that haven't been invested in
    public ArrayList<Stock> notInvestedIn() {
        ArrayList<Stock> notInvested = new ArrayList<Stock>();
        for (Stock stockItem : stockItems) {
            if (!stockItem.getInvestmentStatus()) {
                notInvested.add(stockItem);
            }
        }
        return notInvested;
    }

    public void displayStocksFromList() {
        if (stockItems.isEmpty()) {
            System.out.println("No stocks in the list.");
        } else {
            System.out.println("Stocks in the list: ");
            for (Stock stock : stockItems) {
                System.out.println(stock.getStockName() + " ("
                        + stock.getTicker() + " ) - Invested: "
                        + stock.getInvestmentStatus() + " , Share Price = " + stock.getSharePrice());
            }
        }
    }

    public ArrayList<Stock> getStockItems() {
        return this.stockItems;
    }

}
