package persistance;

import model.Drink;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


// taken from JsonSerializationDemo
public class JsonTest {
    protected void checkDrink(double price, String call, Drink d) {
        assertEquals(price, d.getPrice());
        assertEquals(call, d.getNameDrink());
    }
}
