package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistance.JsonReader;
import persistance.JsonWriter;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// test cases for the Coffee Frap Class
public class CoffeeFrapTest {
    private Drink drink1;
    private Drink drink2;
    private Drink drink3;

    @BeforeEach
    void runBefore() {
        drink1 = new CoffeeFrap("small");
        drink2 = new CoffeeFrap("medium");
        drink3 = new CoffeeFrap("large");
    }

    @Test
    void testConstructor() {

        assertEquals(3.50, drink1.getPrice());
        assertEquals("The small coffee frap", drink1.getNameDrink());

        assertEquals(4.50, drink2.getPrice());
        assertEquals("The medium coffee frap", drink2.getNameDrink());

        assertEquals(5.50, drink3.getPrice());
        assertEquals("The large coffee frap", drink3.getNameDrink());
    }

    @Test
    void testChangeToppings() {

        drink1.changeToppings("yes", "yes", "yes");
        assertEquals(3.50 + 0.10 + 0.10 + 0.10, drink1.getPrice());
        String nameOfDrink1;
        nameOfDrink1 = "The small coffee frap with whip cream, with cinnamon powder and with caramel drizzle";
        assertEquals(nameOfDrink1, drink1.getNameDrink());

        drink2.changeToppings("yes", "no", "no");
        assertEquals(4.50 + 0.10, drink2.getPrice());
        String nameOfDrink2;
        nameOfDrink2 = "The medium coffee frap with whip cream, without cinnamon powder and without caramel drizzle";
        assertEquals(nameOfDrink2, drink2.getNameDrink());

        drink3.changeToppings("no", "no", "no");
        assertEquals(5.50, drink3.getPrice());
        String nameOfDrink3;
        nameOfDrink3 = "The large coffee frap without whip cream, without cinnamon powder and without caramel drizzle";
        assertEquals(nameOfDrink3, drink3.getNameDrink());
    }

    @Test
    void testAddMilk() {
        drink1.addMilk("oat");
        assertEquals("The small coffee frap, using oat milk", drink1.getNameDrink());
        drink2.addMilk("almond");
        assertEquals("The medium coffee frap, using almond milk", drink2.getNameDrink());
    }

    @Test
    void testAddSugar() {
        drink1.addSugar(1);
        assertEquals("The small coffee frap and 1 packet of sugar", drink1.getNameDrink());
        drink2.addSugar(10);
        assertEquals("The medium coffee frap and 10 packets of sugar", drink2.getNameDrink());
        drink3.addSugar(0);
        assertEquals("The large coffee frap", drink3.getNameDrink());
    }
    @Test
    void testToJson() {
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
