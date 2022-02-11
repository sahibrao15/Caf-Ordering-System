package model;

public abstract class Blended implements Drink {


    // being overridden by subclasses
    public double getPrice() {
        return 0.0;
    }

    // being overridden by subclasses
    public String getNameDrink() {

        return "";
    }

    // being overridden by subclasses
    public void changeToppings(String x, String y, String z) {

    }

    // being overridden by subclasses
    public void addMilk(String milk) {

    }

    // being overridden by subclasses
    public void addSugar(int sugar) {

    }

}
