package model;

// Represents a stock with a name, ticker symbol, and info about whether
// the user has invested in the stock. User can also optionally add the
// share price of the stock.

import org.json.JSONObject;
import persistence.Writable;

public class Stock implements Writable {
    private String name;
    private String ticker;
    private boolean hasInvested;
    private double sharePrice;

    // EFFECTS: creates a Stock with a name, a ticker, and
    // investment status. sharePrice is set as 0
    public Stock(String name, String ticker, boolean hasInvested) {
        this.name = name;
        this.ticker = ticker;
        this.hasInvested = hasInvested;
        this.sharePrice = 0;
    }

    public String getStockName() {
        return name;
    }

    public String getTicker() {
        return ticker;
    }

    public void setInvestmentStatus(boolean hasInvested) {
        this.hasInvested = hasInvested;
    }

    public boolean getInvestmentStatus() {
        return this.hasInvested;
    }

    public double getSharePrice() {
        return sharePrice;
    }

    public void setSharePrice(double sharePrice) {
        this.sharePrice = sharePrice;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("ticker", ticker);
        json.put("hasInvested", hasInvested);
        json.put("sharePrice", sharePrice);
        return json;
    }

}
