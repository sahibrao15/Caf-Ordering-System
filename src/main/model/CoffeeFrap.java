package model;

public class CoffeeFrap extends Blended {

    private double frapPrice;
    private String name;


    public CoffeeFrap(String size) {
        if (size.equals("small")) {
            frapPrice = 3.50;
            name = "The small coffee frap";
        } else if (size.equals("medium")) {
            frapPrice = 4.50;
            name = "The medium coffee frap";
        } else {
            frapPrice = 5.50;
            name = "The large coffee frap";
        }

    }

    public void changeToppings(String x, String y, String z) {
        if (x.equals("yes")) {
            name += " with whip cream";
            frapPrice += 0.10;
        } else {
            name += " without whip cream";
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


    public void addMilk(String milk) {
        name += ", using " + milk + " milk";
    }

    public void addSugar(int sugar) {
        if (sugar > 1) {
            name += " and " + sugar + " packets of sugar";
        } else if (sugar == 1) {
            name += " and " + 1 + " packet of sugar";
        }
    }

    // getters
    public double getPrice() {
        return this.frapPrice;
    }


    public String getNameDrink() {
        return name;
    }
}
