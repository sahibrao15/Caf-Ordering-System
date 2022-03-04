package persistance;

import model.Drink;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class JsonTest {
    protected void checkDrink(double price, Drink d) {
        assertEquals(price, d.getPrice());

    }
}
