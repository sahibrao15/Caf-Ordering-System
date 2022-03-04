package persistance;

import model.Drink;
import model.Order;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// taken from JsonSerializationDemo
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Order order = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkRoom.json");
        try {
            Order order = reader.read();
            assertEquals(0, order.getList().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkRoom.json");
        try {
            Order order = reader.read();
            List<Drink> drinks = order.getDrinks();
            assertEquals(12, drinks.size());
            checkDrink(3, "The small latte", drinks.get(0));
            checkDrink(5, "The large latte", drinks.get(1));
            checkDrink(4, "The medium latte", drinks.get(2));
            checkDrink(2.85,"The small americano", drinks.get(3));
            checkDrink(4.85,"The large americano", drinks.get(4));
            checkDrink(3.85,"The medium americano", drinks.get(5));
            checkDrink(3.5, "The small coffee frap", drinks.get(6));
            checkDrink(5.5, "The large coffee frap", drinks.get(7));
            checkDrink(4.5, "The medium coffee frap", drinks.get(8));
            checkDrink(3.45, "The small smoothie", drinks.get(9));
            checkDrink(5.1, "The large smoothie", drinks.get(10));
            checkDrink(3.9, "The medium smoothie", drinks.get(11));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
