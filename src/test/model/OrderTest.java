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
        order1.orderSmoothie("small");
        assertEquals(1,order1.getList().size());
        assertEquals(3.45,order1.getPrice());

        order1.orderSmoothie("medium");
        assertEquals(2,order1.getList().size());
        assertEquals(3.45+3.90,order1.getPrice());

        order1.orderSmoothie("large");
        assertEquals(3,order1.getList().size());
        assertEquals(3.45+3.90+5.10,order1.getPrice());
    }
    @Test
    void testOrderLatte(){
        order1.orderLatte("small");
        assertEquals(1,order1.getList().size());
        assertEquals(3.00,order1.getPrice());

        order1.orderLatte("medium");
        assertEquals(2,order1.getList().size());
        assertEquals(7.00,order1.getPrice());

        order1.orderLatte("large");
        assertEquals(3,order1.getList().size());
        assertEquals(12.0,order1.getPrice());
    }
    @Test
    void testOrderAmericano(){
        order1.orderAmericano("small");
        assertEquals(1,order1.getList().size());
        assertEquals(2.85,order1.getPrice());

        order1.orderAmericano("medium");
        assertEquals(2,order1.getList().size());
        assertEquals(2.85+3.85,order1.getPrice());

        order1.orderAmericano("large");
        assertEquals(3,order1.getList().size());
        assertEquals(2.85+3.85+4.85,order1.getPrice());
    }
    @Test
    void testOrderCoffeeFrap(){
        order1.orderCoffeeFrap("small");
        assertEquals(1,order1.getList().size());
        assertEquals(3.5,order1.getPrice());

        order1.orderCoffeeFrap("medium");
        assertEquals(2,order1.getList().size());
        assertEquals(8.0,order1.getPrice());

        order1.orderCoffeeFrap("large");
        assertEquals(3,order1.getList().size());
        assertEquals(13.5,order1.getPrice());
    }

    @Test
    void testToppings(){
        order1.orderCoffeeFrap("small");
        order1.orderCoffeeFrap("medium");

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
        order1.orderLatte("small");
        order1.orderLatte("medium");

        order1.milk(1,"oat");
        assertEquals("The small latte, made with oat milk", order1.getList().get(0).getNameDrink());
        assertEquals(3.00 + 0.30, order1.getList().get(0).getPrice());

        order1.milk(2,"almond");
        assertEquals("The medium latte, made with almond milk", order1.getList().get(1).getNameDrink());
        assertEquals(4.00 + 0.30, order1.getList().get(1).getPrice());
    }
    @Test
    void testSugar(){
        order1.orderLatte("small");
        order1.orderLatte("medium");
        order1.orderLatte("large");

        order1.sugar(1,1);
        assertEquals("The small latte and 1 packet of sugar", order1.getList().get(0).getNameDrink());
        order1.sugar(2,10);
        assertEquals("The medium latte and 10 packets of sugar", order1.getList().get(1).getNameDrink());
        order1.sugar(3,0);
        assertEquals("The large latte", order1.getList().get(2).getNameDrink());
    }

    @Test
    void testRemove(){
        order1.orderLatte("small");
        order1.orderLatte("medium");
        order1.orderLatte("large");

        order1.remove(2);
        assertEquals(2,order1.getList().size());
        assertEquals(3.00,order1.getList().get(0).getPrice());
        assertEquals(5.00,order1.getList().get(1).getPrice());

    }


}
