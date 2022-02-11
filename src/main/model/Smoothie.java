package model;

public class Smoothie extends Blended {

    private double smoothiePrice;
    private String nameToCall;

    // REQUIRES: a string size of "small", "medium" or "large"
    // EFFECTS:  based on the size, smoothiePrice will be the correct price of the smoothie,
    //           and the nameToCall will contain the right size of the smoothie
    public Smoothie(String size) {
        if (size.equals("small")) {
            smoothiePrice = 3.45;
            nameToCall = "The small smoothie";
        } else if (size.equals("medium")) {
            smoothiePrice = 3.90;
            nameToCall = "The medium smoothie";
        } else {
            smoothiePrice = 5.10;
            nameToCall = "The large smoothie";
        }
    }

    // REQUIRES: Strings of whipCream, cinnamon and caramel to be either "yes" or "no"
    // MODIFIES: this
    // EFFECTS:  adds 0.10 to smoothiePrice for each "yes" of toppings and makes the correct
    //           adjustments to nameToCall
    public void changeToppings(String x, String y, String z) {
        if (x.equals("yes")) {
            nameToCall += " with whip cream";
            smoothiePrice += 0.10;
        } else {
            nameToCall += " without whip cream";

        }
        if (y.equals("yes")) {
            nameToCall += ", with cinnamon powder";
            smoothiePrice += 0.10;
        } else {
            nameToCall += ", without cinnamon powder";
        }
        if (z.equals("yes")) {
            nameToCall += " and with caramel drizzle";
            smoothiePrice += 0.10;
        } else {
            nameToCall += " and without caramel drizzle";
        }
    }

    // REQUIRES: Strings of milk to be a type of milk, such as "oat" or "almond"
    // MODIFIES: this
    // EFFECTS:  no charge to smoothiePrice for milk added and makes the correct
    //           adjustments to nameToCall
    public void addMilk(String milk) {
        nameToCall += ", with a hint of " + milk + " milk";
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

    // getter method for nameToCall
    public String getNameDrink() {
        return nameToCall;
    }

    // getter method for smoothiePrice
    public double getPrice() {
        return this.smoothiePrice;
    }

}
