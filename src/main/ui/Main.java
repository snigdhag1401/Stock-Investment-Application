package ui;

import javax.swing.*;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StockInvestmentAppGUI().setVisible(true);
            }
        });
    }
}


