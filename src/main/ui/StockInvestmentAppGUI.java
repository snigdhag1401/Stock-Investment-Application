package ui;

import model.Stock;
import model.StockList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JFrame;

// Represents a stock investment app with a graphical user interface
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

    private ImageIcon smallIcon;
    private ImageIcon smallAddIcon;
    private ImageIcon smallSaveIcon;
    private ImageIcon smallLoadIcon;
    private ImageIcon smallViewInvestedIcon;
    private ImageIcon smallViewAllIcon;

    // MODIFIES: this
    // EFFECTS: creates the graphical user interface for the stock app,
    // including a window and buttons
    public StockInvestmentAppGUI() {
        initializeValues();

        createPanel();

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        nameField = new JTextField();
        tickerField = new JTextField();
        investmentCheckBox = new JCheckBox();

        createImages();

        inputPanel.add(new JLabel("Stock name:", smallIcon, JLabel.CENTER));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Ticker Symbol:",  smallIcon, JLabel.CENTER));
        inputPanel.add(tickerField);
        inputPanel.add(new JLabel("Investment Status:",  smallIcon, JLabel.CENTER));
        inputPanel.add(investmentCheckBox);

        createAddAndSaveStockButtons();
        createLoadAndViewButtons();

        panel.add(new JScrollPane(stockList), BorderLayout.CENTER);
        panel.add(inputPanel, BorderLayout.SOUTH);

        addButtonsToPanel();

        setTitle("Stock Investment Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,500);
        setLocationRelativeTo(null);
        add(panel);
    }

    // EFFECTS: creates an empty stocklist and its list model,
    // and initializes JSON elements
    public void initializeValues() {
        stocks = new StockList("My stocklist");
        listModel = new DefaultListModel<>();
        stockList = new JList<>(listModel);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // MODIFIES: this
    // EFFECTS: creates the main panel for the GUI
    public void createPanel() {
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
    }

    // EFFECTS: creates images for icons used in GUI
    public void createImages() {
        ImageIcon icon = new ImageIcon("data/stock-market-vector-icon-small.jpg",
                "a stock icon");
        smallIcon = new ImageIcon(getScaledImage(icon.getImage(), 32, 32));

        ImageIcon addIcon = new ImageIcon("data/addStockIcon.jpg",
                "an add stock icon");
        smallAddIcon = new ImageIcon(getScaledImage(addIcon.getImage(), 32,32));

        ImageIcon saveIcon = new ImageIcon("data/saveStockIcon.jpg",
                "a save stock icon");
        smallSaveIcon = new ImageIcon(getScaledImage(saveIcon.getImage(), 32, 32));

        ImageIcon loadIcon = new ImageIcon("data/loadStockIcon.jpg",
                "a load stock icon");
        smallLoadIcon = new ImageIcon(getScaledImage(loadIcon.getImage(), 32, 32));

        ImageIcon viewInvestedIcon = new ImageIcon("data/viewInvestedStocksIcon.jpg",
                "a view invested stock icon");
        smallViewInvestedIcon = new ImageIcon(getScaledImage(viewInvestedIcon.getImage(), 32, 32));

        ImageIcon viewAllIcon = new ImageIcon("data/viewAllStocksIcon.jpg",
                "a view all stock icon");
        smallViewAllIcon = new ImageIcon(getScaledImage(viewAllIcon.getImage(), 32, 32));
    }

    // EFFECTS: creates a new image with given width and height
    private Image getScaledImage(Image srcImg, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();
        return resizedImg;
    }

    // EFFECTS: creates Add and Save buttons for the GUI
    public void createAddAndSaveStockButtons() {
        addButton = new JButton("Add a stock");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAStock();
            }
        });
        addButton.setIcon(smallAddIcon);

        saveButton = new JButton("Save Stocks");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveStocksToFile();
            }
        });
        saveButton.setIcon(smallSaveIcon);
    }

    // EFFECTS: creates Load and View buttons for the GUI
    public void createLoadAndViewButtons() {
        loadButton = new JButton("Load Stocks");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadStocksFromFile();
            }
        });
        loadButton.setIcon(smallLoadIcon);

        viewInvestedButton = new JButton("View Invested Stocks");
        viewInvestedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewInvestedStocks();
            }
        });
        viewInvestedButton.setIcon(smallViewInvestedIcon);

        createViewAllButton();
    }

    // EFFECTS: creates view all button for GUI
    public void createViewAllButton() {
        viewAllButton = new JButton("View All Stocks");
        viewAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewAllStocks();
            }
        });
        viewAllButton.setIcon(smallViewAllIcon);
    }

    // MODIFIES: this
    // EFFECTS: adds all buttons to GUI
    public void addButtonsToPanel() {
        JPanel buttonPanel = new JPanel(new GridLayout(1, 5, 5, 5));
        buttonPanel.add(addButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(viewInvestedButton);
        buttonPanel.add(viewAllButton);
        panel.add(buttonPanel, BorderLayout.NORTH);
    }

    // MODIFIES: this
    // EFFECTS: creates a new stock and adds it to list and to screen display,
    // if stock already in list, doesn't add it again
    public void addAStock() {
        String name = nameField.getText();
        String tickerSymbol = tickerField.getText();
        boolean investmentStatus = investmentCheckBox.isSelected();

        Stock stock = new Stock(name, tickerSymbol, investmentStatus);
        if (stocks.findStockItem(name) == null) {
            stocks.addStock(stock);
            listModel.addElement(stock.getStockName() + " ("
                    + stock.getTicker() + " ) - Invested: "
                    + stock.getInvestmentStatus() + " , Share Price = " + stock.getSharePrice());
        } else {
            JOptionPane.showMessageDialog(this, "Stock is already in list!");
        }

        // Clear input fields after adding stock
        nameField.setText("");
        tickerField.setText("");
        investmentCheckBox.setSelected(false);
    }

    // EFFECTS: saves all stocks to file
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

    // MODIFIES: this
    // EFFECTS: loads stocklist from file and displays it on screen
    public void loadStocksFromFile() {
        try {
            stocks = jsonReader.read();
            JOptionPane.showMessageDialog(this, "Loaded " + stocks.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Unable to read from file: " + JSON_STORE);
        }
        viewAllStocks();
    }

    // EFFECTS: displays stocks that have been invested in, with their
    // name, ticker symbol, share price, and investment status
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

    // EFFECTS: displays all stocks in list, with their name,
    // ticker symbol, share price, and investment status
    public void viewAllStocks() {
        listModel.clear();
        for (Stock stock : stocks.getStockItems()) {
            listModel.addElement(stock.getStockName() + " ("
                    + stock.getTicker() + " ) - Invested: "
                    + stock.getInvestmentStatus() + " , Share Price = " + stock.getSharePrice());
        }
    }
}
