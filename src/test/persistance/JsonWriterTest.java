package persistance;

import model.Drink;
import model.Order;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Order order = new Order();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }
    @Test
    void testWriterEmptyWorkroom() {
        try {
            Order order = new Order();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(order);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            order = reader.read();
            assertEquals(0, order.getDrinks().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
    @Test
    void testWriterGeneralWorkroom() {
        try {
            Order order = new Order();
            order.orderLatte("small");
            order.orderCoffeeFrap("medium");
            order.orderAmericano("large");
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(order);
            writer.close();
            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            order = reader.read();
            List<Drink> drinks = order.getDrinks();
            assertEquals(3, drinks.size());
            assertEquals(3.0, drinks.get(0).getPrice());
            assertEquals(4.5, drinks.get(1).getPrice());
            assertEquals(4.85,drinks.get(2).getPrice());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}

