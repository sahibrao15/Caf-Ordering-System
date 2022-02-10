package ui;

import model.*;

import java.util.ArrayList;
import java.util.Scanner;


public class Order {

    ArrayList<Drink> orderList;
    double totalPrice;

    public Order() {
        orderList = new ArrayList<>();
        totalPrice = 0;
        welcomeMessage();
        orderDrink();
        customizeOrNo();
        endingMessage();
    }

    public void welcomeMessage() {
        System.out.println("\033[1m");
        System.out.println("Welcome to your very own virtual café!");
        System.out.println();
        System.out.print("First, order up to as many drinks as you want,");
        System.out.println(" then customize up to as many as you want!\033[0m");
    }

    public void orderDrink() {
        System.out.println("\nWhat can we get for drink number " + (orderList.size() + 1) + "?");
        Scanner drinkType = new Scanner(System.in);
        System.out.println("An Espresso Beverage or a Blended Beverage?");
        System.out.println("\033[3mInsert either espresso or blended below\033[0m");
        String beverage = drinkType.nextLine();
        if (beverage.equals("espresso") || beverage.equals("Espresso")) {
            System.out.println("You have selected an Espresso Beverage");
            orderEspresso();
        } else if (beverage.equals("blended") || beverage.equals("Blended")) {
            System.out.println("You have selected a Blended Beverage");
            orderBlended();
        } else {
            System.out.println("You have selected neither, please try again");
            orderDrink();
        }
    }

    public void customizeOrNo() {
        System.out.println("\n\033[1mWould you like to customize any of your drinks?\033[0m");
        Scanner customize = new Scanner(System.in);
        String customDrink = customize.nextLine();

        if (customDrink.equals("Yes") || customDrink.equals("yes")) {
            System.out.println("Which numbered drink would you like to customize?");
            Scanner number = new Scanner(System.in);
            int numDrink = number.nextInt();

            customizeToppings(orderList.get(numDrink - 1));
            changeMilk(orderList.get(numDrink - 1));
            addSugar(orderList.get(numDrink - 1));

            System.out.println("\n\033[1mWould you would like to customize any more?\033[0m");
            Scanner answer1 = new Scanner(System.in);
            String answer1yes = answer1.nextLine();

            if (answer1yes.equals("yes")) {
                customizeOrNo();
            }

        } else if (customDrink.equals("No") || customDrink.equals("no")) {
            System.out.println("\nProceeding to checkout mode!");

        } else {
            System.out.println("You have selected neither, please try again");
            customizeOrNo();
        }
    }

    public void customizeToppings(Drink d) {
        System.out.println("Which toppings would you like? Enter yes or no for each topping");
        System.out.println("Keep in mind that each topping is an additional $0.10\n");
        System.out.println("Whip cream?");
        Scanner scanWhipCream = new Scanner(System.in);
        String whipCream = scanWhipCream.nextLine();
        System.out.println();
        System.out.println("Cinnamon Powder?");
        Scanner scanCinnamon = new Scanner(System.in);
        String cinnamon = scanCinnamon.nextLine();
        System.out.println();
        System.out.println("Caramel Drizzle?");
        Scanner scanCaramel = new Scanner(System.in);
        String caramel = scanCaramel.nextLine();
        System.out.println();
        d.changeToppings(whipCream, cinnamon, caramel);
    }

    public void changeMilk(Drink d) {
        System.out.println("Would you like to change milk?");
        Scanner answer = new Scanner(System.in);
        String milkChange = answer.nextLine();
        if (milkChange.equals("yes")) {
            System.out.println("We have the option of oat, soy or almond milk, which would you like");
            Scanner milk = new Scanner(System.in);
            String milkChangeTo = milk.nextLine();
            d.changeMilk(milkChangeTo); ///?????
        }
    }

    public void addSugar(Drink d) {
        System.out.println("How many sugars would you like to add, for no additional charge");
        Scanner sugar = new Scanner(System.in);
        int sugarNumber = sugar.nextInt();
        d.addSugar(sugarNumber);
    }

    public void orderEspresso() {
        Espresso espDrink;
        System.out.println("Would you like a latte or an americano?");
        System.out.println("\033[3mInsert either latte or americano below\033[0m");
        Scanner ansDrink = new Scanner(System.in);
        String drink = ansDrink.nextLine();

        System.out.println("Would size would you like your drink?");
        System.out.println("\033[3mInsert either small, medium or large below\033[0m");
        Scanner size = new Scanner(System.in);
        String drinkSize = size.nextLine();

        if (drink.equals("latte")) {
            espDrink = new Latte(drinkSize);
            addToList(espDrink);

        } else if (drink.equals("americano")) {
            espDrink = new Americano(drinkSize);
            addToList(espDrink);
        } else {
            System.out.println("You have selected neither, please try again");
            orderEspresso();
        }
        orderAgain();
    }

    public void orderBlended() {
        Blended blendDrink;
        System.out.println("Would you like a coffee frap or a smoothie?");
        System.out.println("\033[3mInsert either coffee frap or smmothie below\033[0m");
        Scanner ansDrink = new Scanner(System.in);
        String drink = ansDrink.nextLine();

        System.out.println("Would size would you like your drink?");
        System.out.println("\033[3mInsert either small, medium or large below\033[0m");
        Scanner size = new Scanner(System.in);
        String drinkSize = size.nextLine();

        if (drink.equals("coffee frap")) {
            blendDrink = new CoffeeFrap(drinkSize);
            addToList(blendDrink);

        } else if (drink.equals("smoothie")) {
            blendDrink = new Smoothie(drinkSize);
            addToList(blendDrink);
        } else {
            System.out.println("You have selected neither, please try again\n");
            orderBlended();
        }
        orderAgain();
    }

    public void orderAgain() {
        System.out.println("\nWould you like to order another drink?");
        Scanner orderAgain = new Scanner(System.in);
        String repeat = orderAgain.nextLine();

        if (repeat.equals("yes") || repeat.equals("Yes")) {
            orderDrink();
        }
    }

    public void endingMessage() {
        System.out.println();
        for (int i = 1; i <= orderList.size(); i++) {
            System.out.println("--beep--");

        }
        System.out.println("\nHere is the list of drinks:");
        for (Drink d : orderList) {
            System.out.println(d.getNameDrink());
        }
        System.out.print("\n\033[1mYour total is $");
        System.out.print(String.format("%.2f", (totalPrice() * 1.12)) + " with tax.\033[0m");
        System.out.println();
        System.out.println("We hope you have a great day and come again!");

    }

    public void addToList(Drink d) {
        orderList.add(d);

    }

    public double totalPrice() {
        double allPrice = 0.0;
        for (Drink d : orderList) {
            allPrice += d.getPrice();
        }
        return allPrice;
    }

}
