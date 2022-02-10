package model;

public class CoffeeFrap extends Blended {

    private double frapPrice;
    private String name;


    public CoffeeFrap(String size) {
        if (size.equals("small")) {
            frapPrice = 4.50;
            name = "A small coffee frap ";
        } else if (size.equals("medium")) {
            frapPrice = 3.50;
            name = "A medium coffee frap ";
        } else {
            frapPrice = 5.50;
            name = "A large coffee frap ";
        }

    }
    public void changeToppings(String x, String y, String z) {
        if (x.equals("yes")) {
            name += "with whip cream";
            frapPrice += 0.10;
        } else {
            name += "without whip cream";

        }
        if (y.equals("yes")) {
            name += ", with cinnamon powder";
            frapPrice += 0.10;
        } else {
            name += ", without cinnamon powder";
        }
        if (z.equals("yes")) {
            name += " and with caramel drizzle";
            frapPrice += 0.10;
        } else {
            name += " and without caramel drizzle";
        }
    }

    @Override
    public double getPrice() {
        return this.frapPrice;
    }

    @Override
    public String getNameDrink() {
        return name;
    }
}
