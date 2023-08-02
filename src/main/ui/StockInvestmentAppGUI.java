package ui;

import model.Stock;
import model.StockList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class StockInvestmentAppGUI extends JFrame {
    private StockList stocks;
    private DefaultListModel<String> listModel;
    private JList<String> stockList;
    private static final String JSON_STORE = "./data/stocklist.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JTextField nameField;
    private JTextField tickerField;
    private JCheckBox investmentCheckBox;
    private JButton addButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton viewInvestedButton;
    private JButton viewAllButton;
    private JPanel panel;

    public StockInvestmentAppGUI() {
        initializeVals();

        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        nameField = new JTextField();
        tickerField = new JTextField();
        investmentCheckBox = new JCheckBox();

        inputPanel.add(new JLabel("Stock name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Ticker Symbol:"));
        inputPanel.add(tickerField);
        inputPanel.add(new JLabel("Investment Status:"));
        inputPanel.add(investmentCheckBox);

        createButtons();

        panel.add(new JScrollPane(stockList), BorderLayout.CENTER);
        panel.add(inputPanel, BorderLayout.SOUTH);

        addButtonsToPanel();

        setTitle("Stock Investment Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,400);
        setLocationRelativeTo(null);
        add(panel);
    }

    public void initializeVals() {
        stocks = new StockList("My stocklist");
        listModel = new DefaultListModel<>();
        stockList = new JList<>(listModel);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public void createButtons() {
        addButton = new JButton("Add a stock");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAStock();
            }
        });

        saveButton = new JButton("Save Stocks");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveStocksToFile();
            }
        });

        loadButton = new JButton("Load Stocks");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadStocksFromFile();
            }
        });

        viewInvestedButton = new JButton("View Invested Stocks");
        viewInvestedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewInvestedStocks();
            }
        });

        viewAllButton = new JButton("View All Stocks");
        viewAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewAllStocks();
            }
        });
    }

    public void addButtonsToPanel() {
        JPanel buttonPanel = new JPanel(new GridLayout(1, 5, 5, 5));
        buttonPanel.add(addButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(viewInvestedButton);
        buttonPanel.add(viewAllButton);
        panel.add(buttonPanel, BorderLayout.NORTH);
    }

    public void addAStock() {
        String name = nameField.getText();
        String tickerSymbol = tickerField.getText();
        boolean investmentStatus = investmentCheckBox.isSelected();

        Stock stock = new Stock(name, tickerSymbol, investmentStatus);
        stocks.addStock(stock);
        listModel.addElement(stock.getStockName() + " ("
                + stock.getTicker() + " ) - Invested: "
                + stock.getInvestmentStatus() + " , Share Price = " + stock.getSharePrice());

        // Clear input fields after adding stock
        nameField.setText("");
        tickerField.setText("");
        investmentCheckBox.setSelected(false);
    }

    public void saveStocksToFile() {
        try {
            jsonWriter.open();
            jsonWriter.write(stocks);
            jsonWriter.close();
            JOptionPane.showMessageDialog(this, "Saved " + stocks.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Unable to write to file: " + JSON_STORE);
        }
    }

    public void loadStocksFromFile() {
        try {
            stocks = jsonReader.read();
            JOptionPane.showMessageDialog(this, "Loaded " + stocks.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Unable to read from file: " + JSON_STORE);
        }
    }

    public void viewInvestedStocks() {
        listModel.clear();
        for (Stock stock : stocks.getStockItems()) {
            if (stock.getInvestmentStatus()) {
                listModel.addElement(stock.getStockName() + " ("
                        + stock.getTicker() + " ) - Invested: "
                        + stock.getInvestmentStatus() + " , Share Price = " + stock.getSharePrice());
            }
        }
    }

    public void viewAllStocks() {
        listModel.clear();
        for (Stock stock : stocks.getStockItems()) {
            listModel.addElement(stock.getStockName() + " ("
                    + stock.getTicker() + " ) - Invested: "
                    + stock.getInvestmentStatus() + " , Share Price = " + stock.getSharePrice());
        }
    }
}
