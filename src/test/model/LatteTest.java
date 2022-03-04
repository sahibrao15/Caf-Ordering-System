package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistance.JsonReader;
import persistance.JsonWriter;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// test cases for the Latte Class
public class LatteTest {

    private Drink drink1;
    private Drink drink2;
    private Drink drink3;

    @BeforeEach
    void runBefore() {
        drink1 = new Latte("small");
        drink2 = new Latte("medium");
        drink3 = new Latte("large");
    }

    @Test
    void testConstructor() {

        assertEquals(3.00, drink1.getPrice());
        assertEquals("The small latte", drink1.getNameDrink());

        assertEquals(4.00, drink2.getPrice());
        assertEquals("The medium latte", drink2.getNameDrink());

        assertEquals(5.00, drink3.getPrice());
        assertEquals("The large latte", drink3.getNameDrink());
    }

    @Test
    void testChangeToppings() {

        drink1.changeToppings("yes", "yes", "yes");
        assertEquals(3.00 + 0.10 + 0.10 + 0.10, drink1.getPrice());
        String nameOfDrink1;
        nameOfDrink1 = "The small latte with whip cream, with cinnamon powder and with caramel drizzle";
        assertEquals(nameOfDrink1, drink1.getNameDrink());

        drink2.changeToppings("yes", "no", "no");
        assertEquals(4.00 + 0.10, drink2.getPrice());
        String nameOfDrink2;
        nameOfDrink2 = "The medium latte with whip cream, without cinnamon powder and without caramel drizzle";
        assertEquals(nameOfDrink2, drink2.getNameDrink());

        drink3.changeToppings("no", "no", "no");
        assertEquals(5.00, drink3.getPrice());
        String nameOfDrink3;
        nameOfDrink3 = "The large latte without whip cream, without cinnamon powder and without caramel drizzle";
        assertEquals(nameOfDrink3, drink3.getNameDrink());
    }

    @Test
    void testAddMilk() {
        drink1.addMilk("oat");
        assertEquals("The small latte, made with oat milk", drink1.getNameDrink());
        assertEquals(3.00 + 0.30, drink1.getPrice());
        drink2.addMilk("almond");
        assertEquals("The medium latte, made with almond milk", drink2.getNameDrink());
        assertEquals(4.00 + 0.30, drink2.getPrice());
    }

    @Test
    void testAddSugar() {
        drink1.addSugar(1);
        assertEquals("The small latte and 1 packet of sugar", drink1.getNameDrink());
        drink2.addSugar(10);
        assertEquals("The medium latte and 10 packets of sugar", drink2.getNameDrink());
        drink3.addSugar(0);
        assertEquals("The large latte", drink3.getNameDrink());
    }
    @Test
    void testToJson() {
        try {
            Order order = new Order();
            order.orderLatte("small");
            order.orderLatte("medium");
            order.orderLatte("large");
            JsonWriter writer = new JsonWriter("./data/testJson.json");
            writer.open();
            writer.write(order);
            writer.close();
            JsonReader reader = new JsonReader("./data/testJson.json");
            order = reader.read();
            List<Drink> drinks = order.getDrinks();
            assertEquals(3, drinks.size());
            assertEquals(3, drinks.get(0).getPrice());
            assertEquals(4, drinks.get(1).getPrice());
            assertEquals(5,drinks.get(2).getPrice());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


}
