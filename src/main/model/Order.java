package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {

    private ArrayList<Drink> orderList;


    public Order() {
        orderList = new ArrayList<>();

    }

    public void orderSmoothie(String size, String call) {
        Smoothie smoothie = new Smoothie(size);
        if (!(call.equals("ignore"))) {
            smoothie.setNameToCall(call);
        }
        orderList.add(smoothie);
    }

    public void orderLatte(String size, String call) {
        Latte latte = new Latte(size);
        if (!(call.equals("ignore"))) {
            latte.setNameToCall(call);
        }
        orderList.add(latte);
    }

    public void orderCoffeeFrap(String size, String call) {
        CoffeeFrap coffeeFrap = new CoffeeFrap(size);
        if (!(call.equals("ignore"))) {
            coffeeFrap.setNameToCall(call);
        }
        orderList.add(coffeeFrap);
    }

    public void orderAmericano(String size, String call) {
        Americano americano = new Americano(size);
        if (!(call.equals("ignore"))) {
            americano.setNameToCall(call);
        }
        orderList.add(americano);
    }

    public void toppings(int drinkNumber, String whip, String cinnamon, String drizzle) {
        Drink d = orderList.get(drinkNumber - 1);
        d.changeToppings(whip, cinnamon, drizzle);
    }

    public void milk(int drinkNumber, String milk) {
        Drink d = orderList.get(drinkNumber - 1);
        d.addMilk(milk);
    }

    public void sugar(int drinkNumber, int sugarNo) {
        Drink d = orderList.get(drinkNumber - 1);
        d.addSugar(sugarNo);
    }

    public void remove(int drinkNumber) {
        orderList.remove(drinkNumber - 1);
    }

    public ArrayList<Drink> getList() {
        return orderList;
    }

    public double getPrice() {
        double allPrice = 0.0;
        for (Drink d : orderList) {
            allPrice += d.getPrice();
        }
        return allPrice;
    }

    // @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Order", "Drinks");
        json.put("Drink", drinksToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray drinksToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Drink d : orderList) {
            jsonArray.put(d.toJson());
        }
        return jsonArray;
    }

    // EFFECTS: returns an unmodifiable list of thingies in this workroom
    public List<Drink> getDrinks() {
        return Collections.unmodifiableList(orderList);
    }
}
