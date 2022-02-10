package model;

public class Latte extends Espresso {

    private double lattePrice;
    //private boolean foam;
    private String name;


    public Latte(String size) {

        // this.foam = true;
        if (size.equals("small")) {
            lattePrice = 4.00;
            name = "A small latte ";
        } else if (size.equals("medium")) {
            lattePrice = 3.00;
            name = "A medium latte ";
        } else {
            lattePrice = 5.00;
            name = "A large latte ";
        }
    }

    // public void changeFoam() {this.foam = !foam;}

    public double getPrice() {
        return lattePrice;
    }
    @Override
    public String getNameDrink() {
        return name;
    }

    public void changeToppings(String x, String y, String z) {
        if (x.equals("yes")) {
            name += "with whip cream";
            lattePrice += 0.10;
        } else {
            name += "without whip cream";

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

    public boolean isLatte() {
        return true;
    }

    public boolean isAmericano() {
        return false;
    }


}
