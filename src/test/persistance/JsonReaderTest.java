package persistance;

import model.Drink;
import model.Order;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

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
            assertEquals(3, drinks.size());
            checkDrink(3, drinks.get(0));
            checkDrink(4.5, drinks.get(1));
            checkDrink(4.85,drinks.get(2));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
