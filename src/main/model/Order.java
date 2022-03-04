package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {

    private ArrayList<Drink> orderList;

    // MODIFIES: this
    // EFFECTS: creates a new orderList
    public Order() {
        orderList = new ArrayList<>();

    }

    // REQUIRES: a size of "small", "medium" or "large"
    // MODIFIES: this
    // EFFECTS: adds a smoothie of the size with corresponding call
    public void orderSmoothie(String size, String call) {
        Smoothie smoothie = new Smoothie(size);
        if (!(call.equals("ignore"))) {
            smoothie.setNameToCall(call);
        }
        orderList.add(smoothie);
    }

    // REQUIRES: a size of "small", "medium" or "large"
    // MODIFIES: this
    // EFFECTS: adds a latte of the size with corresponding call
    public void orderLatte(String size, String call) {
        Latte latte = new Latte(size);
        if (!(call.equals("ignore"))) {
            latte.setNameToCall(call);
        }
        orderList.add(latte);
    }

    // REQUIRES: a size of "small", "medium" or "large"
    // MODIFIES: this
    // EFFECTS: adds a coffee frap of the size with corresponding call
    public void orderCoffeeFrap(String size, String call) {
        CoffeeFrap coffeeFrap = new CoffeeFrap(size);
        if (!(call.equals("ignore"))) {
            coffeeFrap.setNameToCall(call);
        }
        orderList.add(coffeeFrap);
    }

    // REQUIRES: a size of "small", "medium" or "large"
    // MODIFIES: this
    // EFFECTS: adds an americano of the size with corresponding call
    public void orderAmericano(String size, String call) {
        Americano americano = new Americano(size);
        if (!(call.equals("ignore"))) {
            americano.setNameToCall(call);
        }
        orderList.add(americano);
    }

    // REQUIRES: drink number within orderList.size() and a "yes" or "no" for all the other parameters
    // MODIFIES: this
    // EFFECTS: adds appropriate toppings the drink selected
    public void toppings(int drinkNumber, String whip, String cinnamon, String drizzle) {
        Drink d = orderList.get(drinkNumber - 1);
        d.changeToppings(whip, cinnamon, drizzle);
    }

    // REQUIRES: drink number within orderList.size() and a type of milk in milk parameter
    // MODIFIES: this
    // EFFECTS: adds appropriate milk to the drink selected
    public void milk(int drinkNumber, String milk) {
        Drink d = orderList.get(drinkNumber - 1);
        d.addMilk(milk);
    }

    // REQUIRES: drink number within orderList.size() and sugarNo >= 0
    // MODIFIES: this
    // EFFECTS: adds sugar packets the drink selected
    public void sugar(int drinkNumber, int sugarNo) {
        Drink d = orderList.get(drinkNumber - 1);
        d.addSugar(sugarNo);
    }

    // REQUIRES: drink number within orderList.size()
    // MODIFIES: this
    // EFFECTS: removes the index of drinkNumber - 1 from orderList
    public void remove(int drinkNumber) {
        orderList.remove(drinkNumber - 1);
    }

    // getter method for orderList
    public ArrayList<Drink> getList() {
        return orderList;
    }

    // EFFECTS: returns the price of all drinks combined
    public double getPrice() {
        double allPrice = 0.0;
        for (Drink d : orderList) {
            allPrice += d.getPrice();
        }
        return allPrice;
    }

    // taken from JsonSerializationDemo
    // EFFECTS: returns a json value of the drinks under "Order"
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Order", "Drinks");
        json.put("Drink", drinksToJson());
        return json;
    }

    // taken from JsonSerializationDemo
    // EFFECTS: returns drinks in this order as a JSON array
    private JSONArray drinksToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Drink d : orderList) {
            jsonArray.put(d.toJson());
        }
        return jsonArray;
    }

    // taken from JsonSerializationDemo
    // EFFECTS: returns an unmodifiable list of drinks in this workroom
    public List<Drink> getDrinks() {
        return Collections.unmodifiableList(orderList);
    }
}
