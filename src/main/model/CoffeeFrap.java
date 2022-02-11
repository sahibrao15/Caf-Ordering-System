package model;

public class CoffeeFrap extends Blended {

    private double frapPrice;
    private String nameToCall;

    // REQUIRES: a string size of "small", "medium" or "large"
    // EFFECTS:  based on the size, frapPrice will be the correct price of the coffee frap,
    //           and the nameToCall will contain the right size of the coffee frap
    public CoffeeFrap(String size) {
        if (size.equals("small")) {
            frapPrice = 3.50;
            nameToCall = "The small coffee frap";
        } else if (size.equals("medium")) {
            frapPrice = 4.50;
            nameToCall = "The medium coffee frap";
        } else {
            frapPrice = 5.50;
            nameToCall = "The large coffee frap";
        }
    }

    // REQUIRES: Strings of whipCream, cinnamon and caramel to be either "yes" or "no"
    // MODIFIES: this
    // EFFECTS:  adds 0.10 to frapPrice for each "yes" of toppings and makes the correct
    //           adjustments to nameToCall
    public void changeToppings(String x, String y, String z) {
        if (x.equals("yes")) {
            nameToCall += " with whip cream";
            frapPrice += 0.10;
        } else {
            nameToCall += " without whip cream";
        }
        if (y.equals("yes")) {
            nameToCall += ", with cinnamon powder";
            frapPrice += 0.10;
        } else {
            nameToCall += ", without cinnamon powder";
        }
        if (z.equals("yes")) {
            nameToCall += " and with caramel drizzle";
            frapPrice += 0.10;
        } else {
            nameToCall += " and without caramel drizzle";
        }
    }

    // REQUIRES: Strings of milk to be a type of milk, such as "oat" or "almond"
    // MODIFIES: this
    // EFFECTS:  no charge for milk added and makes the correct
    //           adjustments to nameToCall
    public void addMilk(String milk) {
        nameToCall += ", using " + milk + " milk";
    }

    // REQUIRES: sugar >= 0
    // MODIFIES: this
    // EFFECTS:  makes the correct adjustments to nameToCall depending on number of sugars
    //           if 0, no change, if above one, packets with an s is added to nameToCall instead of no s if sugar==1
    public void addSugar(int sugar) {
        if (sugar > 1) {
            nameToCall += " and " + sugar + " packets of sugar";
        } else if (sugar == 1) {
            nameToCall += " and " + 1 + " packet of sugar";
        }
    }

    // getter for frapPrice
    public double getPrice() {
        return this.frapPrice;
    }

    // getter for nameToCall
    public String getNameDrink() {
        return nameToCall;
    }
}
