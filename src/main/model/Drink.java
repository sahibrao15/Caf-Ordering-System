package model;

public interface Drink {

    // being overridden by subclasses
    double getPrice();

    // being overridden by subclasses
    void changeToppings(String x, String y, String z);

    // being overridden by subclasses
    void addMilk(String milk);

    // being overridden by subclasses
    void addSugar(int sugar);

    // being overridden by subclasses
    String getNameDrink();

}
