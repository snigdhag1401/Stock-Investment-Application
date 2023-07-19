//package ui;
//
//import model.Stock;
//import model.StockList;
//
//import java.util.Scanner;
//
//public class StockApplication {
//    private StockList stockList;
//    private Scanner input;
//
//    public StockApplication() {
//        stockList = new StockList();
//        input = new Scanner(System.in);
//    }
//
//    public void runStockApp() {
//        boolean exit = false;
//
//        while (!exit) {
//            System.out.println("Welcome to the Stock Investment Application!");
//            System.out.println("1. Add a stock");
//            System.out.println("2. Remove a stock");
//            System.out.println("3. Modify a stock");
//            System.out.println("4. Display all stocks");
//            System.out.println("5. Exit");
//            System.out.println("Please enter the number of your choice: ");
//            int choice = input.nextInt();
//            input.nextLine();
//
//            switch (choice) {
//                case 1:
//                    addStock();
//                    break;
//                case 2:
//                    removeStock();
//                case 3:
//                    modifyStock();
//                    break;
//                case 4:
//                    displayStocks();
//                    break;
//                case 5:
//                    exit = true;
//                    break;
//                default:
//                    System.out.println("Invalid choice. Please try again");
//
//            }
//        }
//        System.out.println("Thank you for using the Stock Investment Application!");
//    }
//
//    private void addStock() {
//        System.out.println("Enter the name of the stock: ");
//        String name = input.nextLine();
//        System.out.println("Enter the ticker symbol of the stock: ");
//        String ticker = input.nextLine();
//        System.out.println("Have you invested in the stock (true/false): ");
//        boolean hasInvested = input.nextBoolean();
//
//        Stock stock = new Stock(name, ticker, hasInvested);
//        stockList.addStock(stock);
//    }
//
//    private void removeStock() {
//        System.out.println("What is the name of the stock to remove? ");
//        String name = input.nextLine();
//        Stock stockToRemove = null;
//
//        stockToRemove = stockList.findStockItem(name);
//
//        if (stockToRemove != null) {
//            stockList.removeStock(stockToRemove);
//            System.out.println("Stock removed successfully!");
//        } else {
//            System.out.println("Stock could not be found");
//        }
//    }
//
//    private void modifyStock() {
//        System.out.println("What is the name of the stock to modify? ");
//        String name = input.nextLine();
//        Stock stockToModify = stockList.findStockItem(name);
//        if (stockToModify != null) {
//            displayModifyStock();
//            if (input.next().equalsIgnoreCase("s")) {
//                doSetSharePrice(stockToModify);
//            } else if (input.next().equalsIgnoreCase("i")) {
//                doSetInvestmentStatus(stockToModify);
//            } else {
//                System.out.println("Invalid response");
//            }
//        } else {
//            System.out.println("Stock could not be found");
//        }
//    }
//
//    private void displayModifyStock() {
//        System.out.println("\nSelect from:");
//        System.out.println("\ts -> set share price");
//        System.out.println("\ti -> set investment status");
//    }
//
//    private void doSetSharePrice(Stock stockToModify) {
//        System.out.println("What is the share price of your stock?");
//        double amount = input.nextDouble();
//
//        if (amount > 0.0) {
//            stockToModify.setSharePrice(amount);
//            System.out.println("The share price was successfully set!");
//        } else {
//            System.out.println("Share price cannot be negative");
//        }
//    }
//
//    private void doSetInvestmentStatus(Stock stockToModify) {
//        System.out.println("What do you want to set the investment status to? (true/false)");
//        boolean investmentStatus = input.nextBoolean();
//        stockToModify.setInvestmentStatus(investmentStatus);
//    }
//
//    private StockList doViewStock() {
//        return stockList;
//    }
//
//}
