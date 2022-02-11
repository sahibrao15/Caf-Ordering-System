package ui;

import model.*;

import java.util.ArrayList;
import java.util.Scanner;


public class Order {

    ArrayList<Drink> orderList;
    double totalPrice;

    // MODIFIES: this
    // EFFECTS: initializes the orderList and sets the totalPrice to 0 before starting the chain of commands to order
    public Order() {
        orderList = new ArrayList<>();
        totalPrice = 0;

        welcomeMessage();
        orderTypeDrink();
        customizeOrNo();
        printListOfDrinks();
        removeDrink();
        endingMessage();
    }

    // MODIFIES: this
    // EFFECTS: Prints out all the messages needed to welcome the customers
    public void welcomeMessage() {
        System.out.println("\033[3m");
        System.out.println("Welcome to your very own virtual café!");
        System.out.println("\nPlease note that all requests must be typed in \033[1mlowercase\033[0m lettres!");
        System.out.println();
        System.out.print("First, order up to as many drinks as you want,");
        System.out.println(" then customize up to as many as you want!");
        System.out.print("\nFor the launch of this café, we are giving u a 10% chance to get 50% off your entire ");
        System.out.println("order!!!\033[0m");
    }

    // MODIFIES: this
    // EFFECTS: Asks customers which type of drink they would like to add for arbitrary numbered drink
    //          before leading them to either orderEspresso() or orderBlended()
    public void orderTypeDrink() {
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
            orderTypeDrink();
        }
    }

    // MODIFIES: this, orderList
    // EFFECTS: Asks customers which espresso drink they would like to add to orderList
    public void orderEspresso() {
        Espresso espDrink;
        System.out.println("\nWould you like a latte or an americano?");
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

    // MODIFIES: this, orderList
    // EFFECTS: Asks customers which blended drink they would like to add to orderList
    public void orderBlended() {
        Blended blendDrink;
        System.out.println("\nWould you like a coffee frap or a smoothie?");
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

    // MODIFIES: this
    // EFFECTS: Asks customers whether they want to customize drinks or not
    //          If yes, customizeDrink(), if not just continue to checkout mode
    public void customizeOrNo() {
        System.out.println("\nWould you like to customize any of your drinks?");
        Scanner customize = new Scanner(System.in);
        String customDrink = customize.nextLine();
        if (customDrink.equals("yes")) {
            customizeDrink();
        } else if (customDrink.equals("no")) {
            System.out.println("\nProceeding to checkout mode!");
        } else {
            System.out.println("You have selected neither, please try again");
            customizeOrNo();
        }
    }

    // MODIFIES: this, drinks in orderList
    // EFFECTS: Asks customers whether they would like to customize any of the drinks
    //          If yes, use customizeToppings, changeMilk and changeSugar to numbered drink selected
    public void customizeDrink() {

        System.out.println("\nIf so, which numbered drink would you like to customize?");
        Scanner number = new Scanner(System.in);
        int numDrink = number.nextInt();

        customizeToppings(orderList.get(numDrink - 1));
        changeMilk(orderList.get(numDrink - 1));
        changeSugar(orderList.get(numDrink - 1));

        System.out.println("\n\033[1mWould you would like to customize any more?\033[0m");
        Scanner answer1 = new Scanner(System.in);
        String answer1yes = answer1.nextLine();

        if (answer1yes.equals("yes")) {
            customizeDrink();
        }
    }

    // MODIFIES: this, Drink
    // EFFECTS: Asks customers which toppings they would like on their drink and using changeToppings method
    //          to edit the Drink
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

    // MODIFIES: this, Drink
    // EFFECTS: Asks customers which milk they would like for their drink and using changeMilk method
    //          to edit the Drink
    public void changeMilk(Drink d) {
        System.out.println("Would you like to change milk?");
        Scanner answer = new Scanner(System.in);
        String milkChange = answer.nextLine();
        if (milkChange.equals("yes")) {
            System.out.println("We have the option of oat, soy or almond milk, which would you like");
            Scanner milk = new Scanner(System.in);
            String milkChangeTo = milk.nextLine();
            d.addMilk(milkChangeTo); ///?????
        }
    }

    // MODIFIES: this, Drink
    // EFFECTS: Asks customers how many sugars would like for their drink and using changeSugar method
    //          to edit the Drink
    public void changeSugar(Drink d) {
        System.out.println("How many sugars would you like to add, for no additional charge");
        Scanner sugar = new Scanner(System.in);
        int sugarNumber = sugar.nextInt();
        d.addSugar(sugarNumber);
    }

    // MODIFIES: this
    // EFFECTS: Asks customers if they would like to order another drink
    //          if yes, orderTypeDrink(), if not continue.
    public void orderAgain() {
        System.out.println("\nWould you like to order another drink?");
        Scanner orderAgain = new Scanner(System.in);
        String repeat = orderAgain.nextLine();

        if (repeat.equals("yes") || repeat.equals("Yes")) {
            orderTypeDrink();
        }
    }

    // MODIFIES: this
    // EFFECTS: Gives the ending message with total price and adding the discount if they got it or not

    public void endingMessage() {
        int min = 1;
        int max = 10;
        int random = (int) Math.floor(Math.random() * (max - min + 1) + min);
        if (random == 2) {
            System.out.print("\n\033[1mYour total is $");
            System.out.println(String.format("%.2f", (totalPrice() * 0.56)) + " with tax.\033[0m");
            System.out.println("Congratulations, you have won the 50% discount off of your order! ");
        } else {
            System.out.print("\n\033[1mYour total is $");
            System.out.println(String.format("%.2f", (totalPrice() * 1.12)) + " with tax.\033[0m");
            System.out.println("Unfortunately you didn't win the 50% discount this time!");
        }

        System.out.println("We hope you have a great day and come again!");
    }

    // MODIFIES: this
    // EFFECTS: lists out all the names of the drinks, as well as the adjustments and the price at the dn
    public void printListOfDrinks() {
        System.out.println();
        for (int i = 1; i <= orderList.size(); i++) {
            System.out.println("--beep--");
        }
        System.out.println("\nHere is the list of drinks:");
        for (Drink d : orderList) {
            System.out.println("-" + d.getNameDrink() + " = $" + String.format("%.2f", d.getPrice()));
        }
    }

    public void removeDrink() {
        System.out.println("\nWould you like to take out any drinks?");
        Scanner yesOrNo = new Scanner(System.in);
        String answer = yesOrNo.nextLine();
        if (answer.equals("yes")) {
            System.out.println("\nWhich numbered drink would you like to get rid of?");
            Scanner number = new Scanner(System.in);
            int takeOut = number.nextInt();
            System.out.println(orderList.get(takeOut - 1).getNameDrink() + " has been removed");
            orderList.remove(takeOut - 1);
        }
    }

    // MODIFIES: this, orderList
    // EFFECTS: Adding drink d to the orderList
    public void addToList(Drink d) {
        orderList.add(d);
    }

    // MODIFIES: this
    // EFFECTS: adding up all the prices of the drinks in orderList and returning the totalPrice
    public double totalPrice() {
        double allPrice = 0.0;
        for (Drink d : orderList) {
            allPrice += d.getPrice();
        }
        return allPrice;
    }

}
