package model;

public class Smoothie extends Blended {

    private double smoothiePrice;
    private String name;

    public double getPrice() {
        return this.smoothiePrice;
    }

    public Smoothie(String size) {
        if (size.equals("small")) {
            smoothiePrice = 4.50;
            name = "A small smoothie ";
        } else if (size.equals("medium")) {
            smoothiePrice = 3.50;
            name = "A small smoothie ";
        } else {
            smoothiePrice = 5.50;
            name = "A small smoothie ";
        }

    }
    public void changeToppings(String x, String y, String z) {
        if (x.equals("yes")) {
            name += "with whip cream";
            smoothiePrice += 0.10;
        } else {
            name += "without whip cream";

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

}
