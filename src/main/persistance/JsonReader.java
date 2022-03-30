package persistance;


import model.Event;
import model.EventLog;
import model.Order;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// taken from JsonSerializationDemo
public class JsonReader {


    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads order from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Order read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseOrder(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        EventLog.getInstance().logEvent(new Event("Read order.JSON"));
        return contentBuilder.toString();
    }

    // EFFECTS: parses order from JSON object and returns it
    private Order parseOrder(JSONObject jsonObject) {
        String name = jsonObject.getString("Order");

        Order order = new Order();
        addDrinks(order, jsonObject);
        return order;

    }

    // MODIFIES: order
    // EFFECTS: parses drinks from JSON object and adds them to order
    private void addDrinks(Order order, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Drink");
        for (Object json : jsonArray) {
            JSONObject nextDrink = (JSONObject) json;
            addDrink(order, nextDrink);
        }
    }

    // MODIFIES: order
    // EFFECTS: parses drink from JSON object and adds it to order
    private void addDrink(Order order, JSONObject jsonObject) {
        String drinkName = jsonObject.getString("name");
        double price = jsonObject.getDouble("price");
        String size = jsonObject.getString("size");
        String call = jsonObject.getString("call");
        decideDrink(order, drinkName, price, call);
    }

    // MODIFIES: order
    // EFFECTS: depending on the drink, it will add that drink to order
    private void decideDrink(Order order, String drinkName, double price, String call) {
        switch (drinkName) {
            case "Latte":
                orderingLatte(order, price, call);
                break;
            case "Americano":
                orderingAmericano(order, price, call);
                break;
            case "Smoothie":
                orderingSmoothie(order, price, call);
                break;
            case "Coffee Frap":
                orderingCoffeeFrap(order, price, call);
                break;
        }
    }

    // MODIFIES: order
    // EFFECTS: depending on the price, it will add that sized coffee frap to order, with the call
    private void orderingCoffeeFrap(Order order, double price, String call) {
        if (price >= 5) {
            order.orderCoffeeFrap("large", call);
        } else if (price >= 4) {
            order.orderCoffeeFrap("medium", call);
        } else {
            order.orderCoffeeFrap("small", call);
        }
    }

    // MODIFIES: order
    // EFFECTS: depending on the price, it will add that sized smoothie to order, with the call
    private void orderingSmoothie(Order order, double price, String call) {
        if (price >= 5.000) {
            order.orderSmoothie("large", call);
        } else if (price > 3.80) {
            order.orderSmoothie("medium", call);
        } else {
            order.orderSmoothie("small", call);
        }
    }

    // MODIFIES: order
    // EFFECTS: depending on the price, it will add that sized americano to order, with the call
    private void orderingAmericano(Order order, double price, String call) {
        if (price >= 4) {
            order.orderAmericano("large", call);
        } else if (price >= 3) {
            order.orderAmericano("medium", call);
        } else {
            order.orderAmericano("small", call);
        }
    }

    // MODIFIES: order
    // EFFECTS: depending on the price, it will add that sized latte to order, with the call
    private void orderingLatte(Order order, double price, String call) {
        if (price >= 5.00) {
            order.orderLatte("large", call);
        } else if (price >= 4.00) {
            order.orderLatte("medium", call);
        } else {
            order.orderLatte("small", call);
        }
    }


}
