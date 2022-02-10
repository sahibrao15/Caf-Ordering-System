package model;

public class Americano extends Espresso {

    private double americanoPrice;
    private String name;

    public Americano(String size) {
        if (size.equals("small")) {
            americanoPrice = 4.00;
            name = "A small americano ";
        } else if (size.equals("medium")) {
            americanoPrice = 3.00;
            name = "A medium americano ";
        } else {
            americanoPrice = 5.00;
            name = "A large americano ";
        }
    }

    public void changeToppings(String x, String y, String z) {
        if (x.equals("yes")) {
            name += "with whip cream";
            americanoPrice += 0.10;
        } else {
            name += "without whip cream";

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

    @Override
    public String getNameDrink() {
        return name;
    }

    @Override
    public double getPrice() {
        return americanoPrice;
    }

    public boolean isLatte() {
        return false;
    }

    public boolean isAmericano() {
        return true;
    }
}
