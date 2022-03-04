package persistance;


import model.Order;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReader {

    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
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
    // EFFECTS: parses drinks from JSON object and adds them to workroom
    private void addDrinks(Order order, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Drink");
        for (Object json : jsonArray) {
            JSONObject nextDrink = (JSONObject) json;
            addDrink(order, nextDrink);
        }
    }

    // MODIFIES: order
    // EFFECTS: parses drink from JSON object and adds it to workroom
    private void addDrink(Order order, JSONObject jsonObject) {
        String drinkName = jsonObject.getString("name");
        double price = jsonObject.getDouble("price");
        decideDrink(order, drinkName, price);
    }

    private void decideDrink(Order order, String call, double price) {
        if (call.equals("Latte")) {
            orderingLatte(order, price);
        } else if (call.equals("Americano")) {
            orderingAmericano(order, price);
        } else if (call.equals("Smoothie")) {
            orderingSmoothie(order, price);
        } else if (call.equals("Coffee Frap")) {
            orderingCoffeeFrap(order, price);
        }
    }

    private void orderingCoffeeFrap(Order order, double price) {
        if (price >= 5) {
            order.orderCoffeeFrap("large");
        } else if (price >= 4) {
            order.orderCoffeeFrap("medium");
        } else {
            order.orderCoffeeFrap("small");
        }
    }

    private void orderingSmoothie(Order order, double price) {
        if (price >= 5.000) {
            order.orderSmoothie("large");
        } else if (price > 3.80) {
            order.orderSmoothie("medium");
        } else {
            order.orderSmoothie("small");
        }
    }

    private void orderingAmericano(Order order, double price) {
        if (price >= 4) {
            order.orderAmericano("large");
        } else if (price >= 3) {
            order.orderAmericano("medium");
        } else {
            order.orderAmericano("small");
        }
    }

    private void orderingLatte(Order order, double price) {
        if (price >= 5.00) {
            order.orderLatte("large");
        } else if (price >= 4.00) {
            order.orderLatte("medium");
        } else {
            order.orderLatte("small");
        }
    }


}
