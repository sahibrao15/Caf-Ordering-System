package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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


}
