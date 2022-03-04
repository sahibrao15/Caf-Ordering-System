package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistance.JsonReader;
import persistance.JsonWriter;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class OrderTest {

    Order order1;
    Order order2;
    Order order3;

    @BeforeEach
    void runBefore(){
        order1 = new Order();
        order2 = new Order();
        order3 = new Order();
    }

    @Test
    void testConstructor(){
        assertEquals(0,order1.getList().size());

    }

    @Test
    void testOrderSmoothie(){
        order1.orderSmoothie("small", "The small smoothie");
        assertEquals(1,order1.getList().size());
        assertEquals(3.45,order1.getPrice());
        assertEquals("The small smoothie", order1.getList().get(0).getNameDrink());

        order1.orderSmoothie("medium", "The medium smoothie");
        assertEquals(2,order1.getList().size());
        assertEquals(3.45+3.90,order1.getPrice());
        assertEquals("The medium smoothie", order1.getList().get(1).getNameDrink());

        order1.orderSmoothie("large", "The large smoothie");
        assertEquals(3,order1.getList().size());
        assertEquals(3.45+3.90+5.10,order1.getPrice());
        assertEquals("The large smoothie", order1.getList().get(2).getNameDrink());

        order1.orderSmoothie("large", "ignore");
        assertEquals(4,order1.getList().size());
        assertEquals(3.45+3.90+5.10*2,order1.getPrice());
        assertEquals("The large smoothie", order1.getList().get(3).getNameDrink());
    }
    @Test
    void testOrderLatte(){
        order1.orderLatte("small", "The small latte");
        assertEquals(1,order1.getList().size());
        assertEquals(3.00,order1.getPrice());
        assertEquals("The small latte", order1.getList().get(0).getNameDrink());

        order1.orderLatte("medium", "The medium latte");
        assertEquals(2,order1.getList().size());
        assertEquals(7.00,order1.getPrice());
        assertEquals("The medium latte", order1.getList().get(1).getNameDrink());

        order1.orderLatte("large", "The large latte");
        assertEquals(3,order1.getList().size());
        assertEquals(12.0,order1.getPrice());
        assertEquals("The large latte", order1.getList().get(2).getNameDrink());

        order1.orderLatte("large", "ignore");
        assertEquals(4,order1.getList().size());
        assertEquals(17.0,order1.getPrice());
        assertEquals("The large latte", order1.getList().get(3).getNameDrink());
    }
    @Test
    void testOrderAmericano(){
        order1.orderAmericano("small", "The small americano");
        assertEquals(1,order1.getList().size());
        assertEquals(2.85,order1.getPrice());
        assertEquals("The small americano", order1.getList().get(0).getNameDrink());

        order1.orderAmericano("medium", "The medium americano");
        assertEquals(2,order1.getList().size());
        assertEquals(2.85+3.85,order1.getPrice());
        assertEquals("The medium americano", order1.getList().get(1).getNameDrink());

        order1.orderAmericano("large", "The large americano");
        assertEquals(3,order1.getList().size());
        assertEquals(2.85+3.85+4.85,order1.getPrice());
        assertEquals("The large americano", order1.getList().get(2).getNameDrink());

        order1.orderAmericano("large", "ignore");
        assertEquals(4,order1.getList().size());
        assertEquals(2.85+3.85+4.85*2,order1.getPrice());
        assertEquals("The large americano", order1.getList().get(3).getNameDrink());
    }
    @Test
    void testOrderCoffeeFrap() {
        order1.orderCoffeeFrap("small", "The small coffee frap");
        assertEquals(1,order1.getList().size());
        assertEquals(3.5,order1.getPrice());
        assertEquals("The small coffee frap", order1.getList().get(0).getNameDrink());


        order1.orderCoffeeFrap("medium", "The medium coffee frap");
        assertEquals(2,order1.getList().size());
        assertEquals(8.0,order1.getPrice());
        assertEquals("The medium coffee frap", order1.getList().get(1).getNameDrink());


        order1.orderCoffeeFrap("large", "The large coffee frap");
        assertEquals(3,order1.getList().size());
        assertEquals(13.5,order1.getPrice());
        assertEquals("The large coffee frap", order1.getList().get(2).getNameDrink());

        order1.orderCoffeeFrap("large", "ignore");
        assertEquals(4,order1.getList().size());
        assertEquals(19,order1.getPrice());
        assertEquals("The large coffee frap", order1.getList().get(3).getNameDrink());

    }

    @Test
    void testToppings(){
        order1.orderCoffeeFrap("small", "The small coffee frap");
        order1.orderCoffeeFrap("medium", "The medium coffee frap");

        order1.toppings(1,"yes","yes","yes");
        assertEquals(3.50 + 0.10 + 0.10 + 0.10, order1.getList().get(0).getPrice());
        String nameOfDrink1;
        nameOfDrink1 = "The small coffee frap with whip cream, with cinnamon powder and with caramel drizzle";
        assertEquals(nameOfDrink1, order1.getList().get(0).getNameDrink());

        order1.toppings(2,"yes","no","no");
        assertEquals(4.50 + 0.10, order1.getList().get(1).getPrice());
        String nameOfDrink2;
        nameOfDrink2 = "The medium coffee frap with whip cream, without cinnamon powder and without caramel drizzle";
        assertEquals(nameOfDrink2, order1.getList().get(1).getNameDrink());
    }
    @Test
    void testMilk(){
        order1.orderLatte("small", "The small latte");
        order1.orderLatte("medium", "The medium latte");

        order1.milk(1,"oat");
        assertEquals("The small latte, made with oat milk", order1.getList().get(0).getNameDrink());
        assertEquals(3.00 + 0.30, order1.getList().get(0).getPrice());

        order1.milk(2,"almond");
        assertEquals("The medium latte, made with almond milk", order1.getList().get(1).getNameDrink());
        assertEquals(4.00 + 0.30, order1.getList().get(1).getPrice());
    }
    @Test
    void testSugar(){
        order1.orderLatte("small", "The small latte");
        order1.orderLatte("medium", "The medium latte");
        order1.orderLatte("large", "The large latte");

        order1.sugar(1,1);
        assertEquals("The small latte and 1 packet of sugar", order1.getList().get(0).getNameDrink());
        order1.sugar(2,10);
        assertEquals("The medium latte and 10 packets of sugar", order1.getList().get(1).getNameDrink());
        order1.sugar(3,0);
        assertEquals("The large latte", order1.getList().get(2).getNameDrink());
    }

    @Test
    void testRemove(){
        order1.orderLatte("small", "The small latte");
        order1.orderLatte("medium", "The medium latte");
        order1.orderLatte("large", "The large latte");

        order1.remove(2);
        assertEquals(2,order1.getList().size());
        assertEquals(3.00,order1.getList().get(0).getPrice());
        assertEquals(5.00,order1.getList().get(1).getPrice());

    }

    @Test
    void testGetPrice(){
        assertEquals(0,order1.getPrice());

        order1.orderLatte("small", "The small latte");

        assertEquals(3.0, order1.getPrice());
        order1.orderLatte("medium", "The medium latte");
        order1.orderLatte("large", "The large latte");

        assertEquals(12.0,order1.getPrice());
    }
    @Test
    void testDrinksToJson() {
        try {
            Order order = new Order();
            order.orderCoffeeFrap("small", "The small coffee frap");
            order.orderCoffeeFrap("medium", "The medium coffee frap");
            order.orderCoffeeFrap("large", "The large coffee frap");
            JsonWriter writer = new JsonWriter("./data/testJson.json");
            writer.open();
            writer.write(order);
            writer.close();
            JsonReader reader = new JsonReader("./data/testJson.json");
            order = reader.read();
            List<Drink> drinks = order.getDrinks();
            assertEquals(3, drinks.size());
            assertEquals(3.5, drinks.get(0).getPrice());
            assertEquals(4.5, drinks.get(1).getPrice());
            assertEquals(5.5,drinks.get(2).getPrice());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


}
