package ui;

import model.Drink;
import model.Event;
import model.EventLog;
import model.Order;
import persistance.JsonReader;
import persistance.JsonWriter;

import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

// cited from AlarmSystem - AlarmControllerUI.java
public class MainGUI extends JFrame {

    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;
    private static final String JSON_STORE = "./data/order.json";

    private Order order;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JInternalFrame controlPanel;

    // constructor for MainHUI
    // MODIFIES: this
    // EFFECTS: initialises all the necessary JFrame and JSON objects, a new order and setting up a button panel
    public MainGUI() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        order = new Order();

        JDesktopPane desktop = new JDesktopPane();

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

        validate();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);


        centreOnScreen();
        setVisible(true);
    }

    // EFFECTS: starts up the mainGUI
    public static void main(String[] args) {

        new MainGUI();

    }

    public void windowClosing(WindowEvent e) {

        // WindowConstants.EXIT_ON_CLOSE;
    }

    // MODIFIES: this
    // EFFECTS: a helper function to aid in setting up a button for each function
    private void addButtonMenu() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 2));
        buttonPanel.add(new JButton(new AddCoffee()));
        buttonPanel.add(new JButton(new Summary()));
        buttonPanel.add(new JButton(new CustomizeCoffee()));
        buttonPanel.add(new JButton(new SaveOrder()));
        buttonPanel.add(new JButton(new RemoveCoffee()));
        buttonPanel.add(new JButton(new LoadOrder()));
        buttonPanel.add(new JButton(new ExitCafe()));

        controlPanel.add(buttonPanel, BorderLayout.WEST);
    }

    // MODIFIES: this
    // EFFECTS: allows to centre the main page on the desktop
    private void centreOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }

    // MODIFIES: this
    // EFFECTS: adds a coffee based on user's input
    private class AddCoffee extends AbstractAction {

        // EFFECTS: constructor for AddCoffee()
        AddCoffee() {
            super("Order Coffee");
        }

        // EFFECTS: creates a panel of asking which coffee they'd
        // like and receiving input to put in orderingCoffee method
        @Override
        public void actionPerformed(ActionEvent evt) {
            String coffeeOrdered = JOptionPane.showInputDialog(null,
                    "Enter Coffee followed by Size (e.g. Latte Medium)",
                    "Order Coffee",
                    JOptionPane.PLAIN_MESSAGE);

            orderingCoffee(coffeeOrdered);
        }

        // REQUIRES: String coffeeOrdered has to be a drink and a size separated by a space button
        // MODIFIES: this
        // EFFECTS: adds a coffee to order based on String coffeeOrdered
        private void orderingCoffee(String coffeeOrdered) {
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

    // MODIFIES: this
    // EFFECTS: removes a coffee based on user's input
    private class RemoveCoffee extends AbstractAction {

        // EFFECTS: constructor for RemoveCoffee()
        RemoveCoffee() {
            super("Remove Coffee");
        }

        // MODIFIES: this
        // EFFECTS: creates a panel of asking which coffee they'd
        // like to remove and removes it from order
        @Override
        public void actionPerformed(ActionEvent evt) {
            String removeCoffee = JOptionPane.showInputDialog(null,
                    "Pick Numbered Coffee to Remove",
                    "Remove Coffee",
                    JOptionPane.PLAIN_MESSAGE);
            int coffeeNumber = Integer.parseInt(removeCoffee);
            order.remove(coffeeNumber);
        }
    }

    // EFFECTS: releases a summary of all the drinks so far and the prices
    private class Summary extends AbstractAction {

        // EFFECTS: constructor for Summary()
        Summary() {
            super("Coffee Summary");
        }

        // EFFECTS: creates a summary of all the drinks as well as add a picture based on how many drinks are ordered
        @Override
        public void actionPerformed(ActionEvent evt) {
            String message;
            message = "Here is the list of drinks:\n";
            ImageIcon icon1 = new ImageIcon("./data/coffee1.png");
            ImageIcon icon2 = new ImageIcon("./data/coffee2.jpeg");
            ImageIcon icon3 = new ImageIcon("./data/coffee3.jpeg");
            for (Drink d : order.getList()) {
                message += "-" + d.getNameDrink() + " = $" + String.format("%.2f", d.getPrice()) + "\n";
            }

            if (1 == order.getList().size()) {
                JOptionPane.showMessageDialog(null, message, "Coffee Summary",
                        JOptionPane.PLAIN_MESSAGE, icon1);
            } else if (2 == order.getList().size()) {
                JOptionPane.showMessageDialog(null, message, "Coffee Summary",
                        JOptionPane.PLAIN_MESSAGE, icon2);
            } else if (0 == order.getList().size()) {
                JOptionPane.showMessageDialog(null, message, "Coffee Summary",
                        JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, message, "Coffee Summary",
                        JOptionPane.PLAIN_MESSAGE, icon3);
            }


        }
    }

    // MODIFIES: order.JSON
    // EFFECTS: loads order from file order.JSON
    public class LoadOrder extends AbstractAction {

        // EFFECTS: constructor for LoadOrder()
        LoadOrder() {
            super("Load Order");
        }

        // REQUIRES: order.JSON should be readable
        // MODIFIES: order.JSON
        // EFFECTS: loads the order from order.JSON
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

    // EFFECTS: saves order from file order.JSON
    public class SaveOrder extends AbstractAction {

        // EFFECTS: constructor for SaveOrder()
        SaveOrder() {
            super("Save Order");

        }

        // REQUIRES: order.JSON to be writable
        // EFFECTS: saves order into file order.JSON
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

    // MODIFIES: this
    // EFFECTS: gives the options in customizing a drink
    public class CustomizeCoffee extends AbstractAction {

        // EFFECTS: constructor for CustomizeCoffee()
        CustomizeCoffee() {
            super("Customize Coffee");
        }

        // EFFECTS: creates a panel of asking which coffee they'd
        // like to customize and put in customizeDrink method
        @Override
        public void actionPerformed(ActionEvent evt) {
            String message = "Enter number of drink customized, followed by \n";
            message += "milk, sugar, whip cream, caramel drizzle, cinnamon powder \n";
            message += "ex. 1,oat,0,yes,yes,no";
            String customizeCoffee0 = JOptionPane.showInputDialog(null,
                    message,
                    "Remove Coffee",
                    JOptionPane.PLAIN_MESSAGE);

            customizeDrink(customizeCoffee0);

        }

        // REQUIRES: String customizeCoffee0 has to be separated by "," for each customization
        // MODIFIES: this
        // EFFECTS: customizes a coffee to order based on String customizeCoffee0
        private void customizeDrink(String customizeCoffee0) {
            int index0 = customizeCoffee0.indexOf(",");
            String numDrink = customizeCoffee0.substring(0, index0);
            String customizeCoffee1 = customizeCoffee0.substring(index0 + 1);
            int index1 = customizeCoffee1.indexOf(",");
            String milk = customizeCoffee1.substring(0, index1);
            String customizeCoffee2 = customizeCoffee1.substring(index1 + 1);
            int index2 = customizeCoffee2.indexOf(",");
            String sugar = customizeCoffee2.substring(0, index2);
            String customizeCoffee3 = customizeCoffee2.substring(index2 + 1);
            int index3 = customizeCoffee3.indexOf(",");
            String whip = customizeCoffee3.substring(0, index3);
            String customizeCoffee4 = customizeCoffee3.substring(index3 + 1);
            int index4 = customizeCoffee4.indexOf(",");
            String caramel = customizeCoffee4.substring(0, index4);
            String cinnamon = customizeCoffee4.substring(index4 + 1);


            order.getList().get(Integer.parseInt(numDrink) - 1).addMilk(milk);
            order.getList().get(Integer.parseInt(numDrink) - 1).addSugar(Integer.parseInt(sugar));
            order.getList().get(Integer.parseInt(numDrink) - 1).changeToppings(whip, caramel, cinnamon);

        }
    }

    // EFFECTS: exits the café and outputs the eventlog
    public class ExitCafe extends AbstractAction {

        // EFFECTS: constructor for SaveOrder()
        ExitCafe() {
            super("Leaving Cafe");

        }


        // EFFECTS: exits the café and outputs the eventlog
        @Override
        public void actionPerformed(ActionEvent evt) {
            for (Event e : EventLog.getInstance()) {
                System.out.println(e.toString());
            }
            System.exit(0);
        }
    }

}
