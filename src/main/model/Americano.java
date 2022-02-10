package model;

public class Americano extends Espresso {

    private double americanoPrice;
    private String name;

    public Americano(String size) {
        if (size.equals("small")) {
            americanoPrice = 2.85;
            name = "The small americano";
        } else if (size.equals("medium")) {
            americanoPrice = 3.00;
            name = "The medium americano";
        } else {
            americanoPrice = 4.00;
            name = "The large americano";
        }
    }

    public void changeToppings(String x, String y, String z) {
        if (x.equals("yes")) {
            name += " with whip cream";
            americanoPrice += 0.10;
        } else {
            name += " without whip cream";
        }
        if (y.equals("yes")) {
            name += ", with cinnamon powder";
            americanoPrice += 0.10;
        } else {
            name += ", without cinnamon powder";
        }
        if (z.equals("yes")) {
            name += " and with caramel drizzle";
            americanoPrice += 0.10;
        } else {
            name += " and without caramel drizzle";
        }
    }

    public void addMilk(String milk) {
        name += ", with " + milk + " milk on top";

    }

    public void addSugar(int sugar) {
        if (sugar > 1) {
            name += " and " + sugar + " packets of sugar";
        } else if (sugar == 1) {
            name += " and " + 1 + " packet of sugar";
        }
    }

    //getters
    public String getNameDrink() {
        return name;
    }

    public double getPrice() {
        return americanoPrice;
    }

}
