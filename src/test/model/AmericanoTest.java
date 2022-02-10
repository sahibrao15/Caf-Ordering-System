package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AmericanoTest {
    private Drink drink1;
    private Drink drink2;
    private Drink drink3;

    @BeforeEach
    void runBefore() {
        drink1 = new Americano("small");
        drink2 = new Americano("medium");
        drink3 = new Americano("large");
    }

    @Test
    void testConstructor() {

        assertEquals(2.85, drink1.getPrice());
        assertEquals("The small americano", drink1.getNameDrink());

        assertEquals(3.00, drink2.getPrice());
        assertEquals("The medium americano", drink2.getNameDrink());

        assertEquals(4.00, drink3.getPrice());
        assertEquals("The large americano", drink3.getNameDrink());
    }

    @Test
    void testChangeToppings() {

        drink1.changeToppings("yes", "yes", "yes");
        assertEquals(2.85 + 0.10 + 0.10 + 0.10, drink1.getPrice());
        String nameOfDrink1;
        nameOfDrink1 = "The small americano with whip cream, with cinnamon powder and with caramel drizzle";
        assertEquals(nameOfDrink1, drink1.getNameDrink());

        drink2.changeToppings("yes", "no", "no");
        assertEquals(3.00 + 0.10, drink2.getPrice());
        String nameOfDrink2;
        nameOfDrink2 = "The medium americano with whip cream, without cinnamon powder and without caramel drizzle";
        assertEquals(nameOfDrink2, drink2.getNameDrink());

        drink3.changeToppings("no", "no", "no");
        assertEquals(4.00, drink3.getPrice());
        String nameOfDrink3;
        nameOfDrink3 = "The large americano without whip cream, without cinnamon powder and without caramel drizzle";
        assertEquals(nameOfDrink3, drink3.getNameDrink());
    }

    @Test
    void testAddMilk() {
        drink1.addMilk("oat");
        assertEquals("The small americano, with oat milk on top", drink1.getNameDrink());
        drink2.addMilk("almond");
        assertEquals("The medium americano, with almond milk on top", drink2.getNameDrink());
    }

    @Test
    void testAddSugar() {
        drink1.addSugar(1);
        assertEquals("The small americano and 1 packet of sugar", drink1.getNameDrink());
        drink2.addSugar(10);
        assertEquals("The medium americano and 10 packets of sugar", drink2.getNameDrink());
        drink3.addSugar(0);
        assertEquals("The large americano", drink3.getNameDrink());
    }
}
