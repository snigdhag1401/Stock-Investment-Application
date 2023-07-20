package ui;

import model.Stock;
import model.StockList;

import java.util.Scanner;

// Represents a stock investment application
public class StockApplication {
    private StockList stockList;
    private Scanner input;

    // EFFECTS: creates a Stock Application with an empty stockList
    // and initializes Scanner
    public StockApplication() {
        stockList = new StockList();
        input = new Scanner(System.in);
    }

    // REQUIRES: user input must be an integer value
    // MODIFIES: this
    // EFFECTS: runs stock application and processes user input
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public void runStockApp() {
        boolean exit = false;

        while (!exit) {
            displayStockApp();
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    addStock();
                    break;
                case 2:
                    removeStock();
                    break;
                case 3:
                    modifyStock();
                    break;
                case 4:
                    displayStocks();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again");
            }
        }
        System.out.println("Thank you for using the Stock Investment Application!");
    }

    // EFFECTS: displays menu of options to user
    private void displayStockApp() {
        System.out.println("Welcome to the Stock Investment Application!");
        System.out.println("1. Add a stock");
        System.out.println("2. Remove a stock");
        System.out.println("3. Modify a stock");
        System.out.println("4. Display stocks");
        System.out.println("5. Exit");
        System.out.println("Please enter the number of your choice: ");
    }

    // MODIFIES: this
    // EFFECTS: creates a new stock and adds it to the list,
    // if stock already in list, doesn't add it again
    private void addStock() {
        System.out.println("Enter the name of the stock: ");
        String name = input.nextLine();
        System.out.println("Enter the ticker symbol of the stock: ");
        String ticker = input.nextLine();
        System.out.println("Have you invested in the stock (true/false): ");
        boolean hasInvested = input.nextBoolean();

        Stock stock = new Stock(name, ticker, hasInvested);
        if (stockList.findStockItem(name) == null) {
            stockList.addStock(stock);
            System.out.println("Stock added successfully!");
        } else {
            System.out.println("The stock is already in the list!");
        }
    }

    // MODIFIES: this
    // EFFECTS: removes stock with given name from list,
    // if stock not in list does nothing
    private void removeStock() {
        System.out.println("What is the name of the stock to remove? ");
        String name = input.nextLine();
        Stock stockToRemove;

        stockToRemove = stockList.findStockItem(name);

        if (stockToRemove != null) {
            stockList.removeStock(stockToRemove);
            System.out.println("Stock removed successfully!");
        } else {
            System.out.println("Stock could not be found");
        }
    }

    // MODIFIES: this
    // EFFECTS: finds stock by name given by user, then processes user command
    private void modifyStock() {
        System.out.println("What is the name of the stock to modify? ");
        String name = input.nextLine();
        Stock stockToModify = stockList.findStockItem(name);
        if (stockToModify != null) {
            displayModifyStock();
            String command = input.next();
            if (command.equalsIgnoreCase("s")) {
                doSetSharePrice(stockToModify);
            } else if (command.equalsIgnoreCase("i")) {
                doSetInvestmentStatus(stockToModify);
            } else {
                System.out.println("Invalid response");
            }
        } else {
            System.out.println("Stock could not be found");
        }
    }

    // EFFECTS: displays menu of modify options to user
    private void displayModifyStock() {
        System.out.println("\nSelect from:");
        System.out.println("\ts -> set share price");
        System.out.println("\ti -> set investment status");
    }

    // MODIFIES: this, stockToModify
    // EFFECTS: sets share price of given stock to value given by user
    private void doSetSharePrice(Stock stockToModify) {
        System.out.println("What is the share price of your stock?");
        double amount = input.nextDouble();

        if (amount > 0.0) {
            stockToModify.setSharePrice(amount);
            System.out.println("The share price was successfully set!");
        } else {
            System.out.println("Share price cannot be zero or negative");
        }
    }

    // MODIFIES: stockToModify, this
    // EFFECTS: sets investment status of given stock to boolean given by user
    private void doSetInvestmentStatus(Stock stockToModify) {
        System.out.println("What do you want to set the investment status to? (true/false)");
        boolean investmentStatus = input.nextBoolean();
        stockToModify.setInvestmentStatus(investmentStatus);
    }

    // EFFECTS: processes user command, passes proper list to display function
    // depending on what user wants to display
    private void displayStocks() {
        displayStocksMenu();
        String command = input.next();
        if (command.equalsIgnoreCase("a")) {
            displayStocksFromList(stockList);
        } else if (command.equalsIgnoreCase("i")) {
            displayStocksFromList(stockList.haveInvestedIn());
        } else if (command.equalsIgnoreCase("n")) {
            displayStocksFromList(stockList.notInvestedIn());
        } else {
            System.out.println("Invalid response");
        }
    }

    // EFFECTS: displays menu of display options to user
    private void displayStocksMenu() {
        System.out.println("\nWhat stocks do you want to display?");
        System.out.println("\ta -> display all stocks");
        System.out.println("\ti -> display stocks that have been invested in");
        System.out.println("\tn -> display stocks that haven't been invested in");
    }

    // EFFECTS: displays stocks from given list with their name, ticker,
    // investment status and share price
    private void displayStocksFromList(StockList stockList) {
        if (stockList.getStockItems().isEmpty()) {
            System.out.println("No stocks in the list.");
        } else {
            System.out.println("Stocks in the list: ");
            for (Stock stock : stockList.getStockItems()) {
                System.out.println(stock.getStockName() + " ("
                        + stock.getTicker() + " ) - Invested: "
                        + stock.getInvestmentStatus() + " , Share Price = " + stock.getSharePrice());
            }
        }
    }

}
