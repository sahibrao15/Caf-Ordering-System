package ui;

import model.Drink;
import model.Order;
import persistance.JsonReader;
import persistance.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainGUI extends JFrame {

    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;
    private static final String JSON_STORE = "./data/order.json";

    private Order order;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JDesktopPane desktop;
    private JInternalFrame controlPanel;


    public MainGUI() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        order = new Order();

        desktop = new JDesktopPane();

        controlPanel = new JInternalFrame("Coffee Order", false, false, false, false);
        controlPanel.setLayout(new BorderLayout());
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        controlPanel.setLocation((width - getWidth()) / 15, (height - getHeight()) / 6);

        setContentPane(desktop);
        setTitle("CPSC 210: Coffee Shop");
        setSize(WIDTH, HEIGHT);

        addButtonMenu();

        controlPanel.pack();
        controlPanel.setVisible(true);
        desktop.add(controlPanel);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        centreOnScreen();
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainGUI();
    }

    private void addButtonMenu() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 2));
        buttonPanel.add(new JButton(new AddCoffee()));
        buttonPanel.add(new JButton(new Summary()));
//        buttonPanel.add(new JButton(new CustomizeCoffee()));
        buttonPanel.add(new JButton(new SaveOrder()));
        buttonPanel.add(new JButton(new RemoveCoffee()));
        buttonPanel.add(new JButton(new LoadOrder()));


        controlPanel.add(buttonPanel, BorderLayout.WEST);
    }

    /**
     * Helper to centre main application window on desktop
     */
    private void centreOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }

    private class AddCoffee extends AbstractAction {

        AddCoffee() {
            super("Order Coffee");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            String coffeeOrdered = JOptionPane.showInputDialog(null,
                    "Enter Coffee followed by Size (e.g. Latte Medium)",
                    "Order Coffee",
                    JOptionPane.QUESTION_MESSAGE);

            int spaceIndex = coffeeOrdered.indexOf(" ");
            switch (coffeeOrdered.substring(0, spaceIndex)) {
                case "latte":
                case "Latte":
                    order.orderLatte(coffeeOrdered.substring(spaceIndex + 1), "ignore");
                    break;
                case "americano":
                case "Americano":
                    order.orderAmericano(coffeeOrdered.substring(spaceIndex + 1), "ignore");
                    break;
                case "Coffee Frap":
                case "coffee frap":
                    order.orderCoffeeFrap(coffeeOrdered.substring(spaceIndex + 1), "ignore");
                    break;
                case "Smoothie":
                case "smoothie":
                    order.orderSmoothie(coffeeOrdered.substring(spaceIndex + 1), "ignore");
                    break;
            }
        }
    }

    private class RemoveCoffee extends AbstractAction {

        RemoveCoffee() {
            super("Remove Coffee");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            String removeCoffee = JOptionPane.showInputDialog(null,
                    "Pick Numbered Coffee to Remove",
                    "Remove Coffee",
                    JOptionPane.QUESTION_MESSAGE);
            int coffeeNumber = Integer.parseInt(removeCoffee);
            order.remove(coffeeNumber);
        }
    }

    private class Summary extends AbstractAction {

        Summary() {
            super("Coffee Summary");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            String message;
            message = "Here is the list of drinks:\n";
            for (Drink d : order.getList()) {
                message += "-" + d.getNameDrink() + " = $" + String.format("%.2f", d.getPrice()) + "\n";
            }
            JOptionPane.showMessageDialog(null, message, "Coffee Summary",
                    JOptionPane.PLAIN_MESSAGE);
        }
    }

    public class LoadOrder extends AbstractAction {

        LoadOrder() {
            super("Load Order");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                order = jsonReader.read();
                String message = "Loaded order from " + JSON_STORE;
                JOptionPane.showMessageDialog(null, message, "Order Loaded",
                        JOptionPane.ERROR_MESSAGE);
            } catch (IOException e) {
                String message = "Unable to read from file: " + JSON_STORE;
                JOptionPane.showMessageDialog(null, message, "Order Not Loaded",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public class SaveOrder extends AbstractAction {

        SaveOrder() {
            super("Save Order");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                jsonWriter.open();
                jsonWriter.write(order);
                jsonWriter.close();
                String message = "Saved order to " + JSON_STORE;
                JOptionPane.showMessageDialog(null, message, "Order Saved",
                        JOptionPane.ERROR_MESSAGE);
            } catch (FileNotFoundException e) {
                String message = "Unable to write to file: " + JSON_STORE;
                JOptionPane.showMessageDialog(null, message, "Order Not Saved",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
