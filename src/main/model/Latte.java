package model;

public class Latte extends Espresso {

    private double lattePrice;
    private String name;

    // constructor
    public Latte(String size) {
        if (size.equals("small")) {
            lattePrice = 3.00;
            name = "The small latte";
        } else if (size.equals("medium")) {
            lattePrice = 4.00;
            name = "The medium latte";
        } else {
            lattePrice = 5.00;
            name = "The large latte";
        }
    }

    //all toppings
    public void changeToppings(String x, String y, String z) {
        if (x.equals("yes")) {
            name += " with whip cream";
            lattePrice += 0.10;
        } else {
            name += " without whip cream";

        }
        if (y.equals("yes")) {
            name += ", with cinnamon powder";
            lattePrice += 0.10;
        } else {
            name += ", without cinnamon powder";
        }
        if (z.equals("yes")) {
            name += " and with caramel drizzle";
            lattePrice += 0.10;
        } else {
            name += " and without caramel drizzle";
        }
    }

    public void addMilk(String milk) {
        name += ", made with " + milk + " milk";

    }

    public void addSugar(int sugar) {
        if (sugar > 1) {
            name += " and " + sugar + " packets of sugar";
        } else if (sugar == 1) {
            name += " and " + 1 + " packet of sugar";
        }
    }

    //getters
    public double getPrice() {
        return lattePrice;
    }

    public String getNameDrink() {
        return name;
    }

}
