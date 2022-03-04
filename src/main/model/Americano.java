package model;

import org.json.JSONObject;

// a class representing an Americano that is extending espresso's methods that are implemented in Drink
public class Americano extends Espresso {

    private double americanoPrice;
    private String nameToCall;
    private String size;

    // REQUIRES: a string size of "small", "medium" or "large"
    // EFFECTS:  based on the size, americanoPrice will be the correct price of the americano,
    //           and the nameToCall will contain the right size of the americano
    //           and size will be set to  "small", "medium" or "large"
    public Americano(String size) {
        if (size.equals("small")) {
            setSize("small");
            americanoPrice = 2.85;
            nameToCall = "The small americano";
        } else if (size.equals("medium")) {
            setSize("medium");
            americanoPrice = 3.85;
            nameToCall = "The medium americano";
        } else {
            setSize("large");
            americanoPrice = 4.85;
            nameToCall = "The large americano";
        }
    }

    // REQUIRES: Strings of whipCream, cinnamon and caramel to be either "yes" or "no"
    // MODIFIES: this
    // EFFECTS:  adds 0.10 to americanoPrice for each "yes" of toppings and makes the correct
    //           adjustments to nameToCall
    public void changeToppings(String x, String y, String z) {
        if (x.equals("yes")) {
            nameToCall += " with whip cream";
            americanoPrice += 0.10;
        } else {
            nameToCall += " without whip cream";
        }
        if (y.equals("yes")) {
            nameToCall += ", with cinnamon powder";
            americanoPrice += 0.10;
        } else {
            nameToCall += ", without cinnamon powder";
        }
        if (z.equals("yes")) {
            nameToCall += " and with caramel drizzle";
            americanoPrice += 0.10;
        } else {
            nameToCall += " and without caramel drizzle";
        }
    }

    // REQUIRES: Strings of milk to be a type of milk, such as "oat" or "almond"
    // MODIFIES: this
    // EFFECTS:  no charge for milk added and makes the correct
    //           adjustments to nameToCall
    public void addMilk(String milk) {
        nameToCall += ", with " + milk + " milk on top";
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

    //getter for nameToCall
    public String getNameDrink() {
        return nameToCall;
    }

    //getter for americanoPrice
    @Override
    public double getPrice() {
        return americanoPrice;
    }

    // setter for name to call
    public void setNameToCall(String nameToCall) {
        this.nameToCall = nameToCall;
    }

    // getter for size
    public String getSize() {
        return size;
    }

    // setter method for size
    public void setSize(String size) {
        this.size = size;
    }

    // taken from JsonSerializationDemo
    // EFFECTS: returns an americano as a JSON v
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("price", americanoPrice);
        json.put("name", "Americano");
        json.put("size", getSize());
        json.put("call", getNameDrink());
        return json;
    }
}
