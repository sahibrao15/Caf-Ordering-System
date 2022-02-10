package model;

public class Smoothie extends Blended {

    private double smoothiePrice;
    private String name;

    public double getPrice() {
        return this.smoothiePrice;
    }

    public Smoothie(String size) {
        if (size.equals("small")) {
            smoothiePrice = 3.45;
            name = "The small smoothie";
        } else if (size.equals("medium")) {
            smoothiePrice = 3.90;
            name = "The medium smoothie";
        } else {
            smoothiePrice = 5.10;
            name = "The large smoothie";
        }

    }

    public void changeToppings(String x, String y, String z) {
        if (x.equals("yes")) {
            name += " with whip cream";
            smoothiePrice += 0.10;
        } else {
            name += " without whip cream";

        }
        if (y.equals("yes")) {
            name += ", with cinnamon powder";
            smoothiePrice += 0.10;
        } else {
            name += ", without cinnamon powder";
        }
        if (z.equals("yes")) {
            name += " and with caramel drizzle";
            smoothiePrice += 0.10;
        } else {
            name += " and without caramel drizzle";
        }
    }

    @Override
    public String getNameDrink() {
        return name;
    }

    public void addMilk(String milk) {
        name += ", with a hint of " + milk + " milk";
    }

    public void addSugar(int sugar) {
        if (sugar > 1) {
            name += " and " + sugar + " packets of sugar";
        } else if (sugar == 1) {
            name += " and " + 1 + " packet of sugar";
        }
    }

}
