package model;

public abstract class Blended implements Drink {


    // being overridden by subclasses
    public abstract double getPrice();

    // being overridden by subclasses
    public abstract String getNameDrink();

    // being overridden by subclasses
    public abstract void changeToppings(String x, String y, String z);

    // being overridden by subclasses
    public abstract void addMilk(String milk);

    // being overridden by subclasses
    public abstract void addSugar(int sugar);
}