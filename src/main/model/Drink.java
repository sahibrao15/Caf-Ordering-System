package model;

public interface Drink {

    double getPrice();

    void changeToppings(String x, String y, String z);

    void changeMilk(String milk);

    void addSugar(int sugar);

    String getNameDrink();


}
