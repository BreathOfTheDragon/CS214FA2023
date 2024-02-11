import java.io.Console;
import java.util.Scanner;

public class Game {

    public static void storeMenu(Scanner scanner, Store store, Player player) {
        while (true) {
            System.out.println("\nStore Menu:");
            System.out.println("1. Buy an item");
            System.out.println("2. Sell an item");
            System.out.println("3. Display inventory");
            System.out.println("4. Exit store");

            String input = scanner.nextLine();

            if (input.equals("1")) {
                store.displayInventory();
                System.out.println("Enter the name of the item you want to buy:");
                String itemName = scanner.nextLine();
                Item item = store.getItemByName(itemName);
                if (item != null) {
                    if (store.buyItem(item, player)){
                        System.out.println("Item purchased successfully!");
                    } else {
                        System.out.println("Could not purchase the desired item.");
                    }
                } else {
                    System.out.println("Item not available in the store.");
                }
            } else if (input.equals("2")) {
                System.out.println("Enter the name of the item you want to sell:");
                String itemName = scanner.nextLine();
                Item item = player.getItemByName(itemName);
                if (item != null) {
                    store.sellItem(item, player);
                    System.out.println("Item sold successfully!");
                } else {
                    System.out.println("Item not found in your inventory.");
                }
            } else if (input.equals("3")) {
                store.displayInventory();
            }  else if (input.equals("4")) {
                System.out.println("Exiting the store...");
                store.exit(player);
                break; 
            } else {
                System.out.println("Invalid command!");
            }
        }
    }

    public static void gameSetup(){
        Item sword = new Item("Sword", 10.0);
        Item potion = new Item("Health Potion", 5.0);
        Item hat = new Item("Hat", 1);

        Store store = new Store();
        store.addItem(sword);
        store.addItem(potion);
        store.addItem(hat);

        Player player = new Player(100.0);

        //store.displayInventory();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nEnter a command (1 to enter the store, 4 to exit):");
            String input = scanner.nextLine();

            if (input.equals("1")) {
                store.enter(player);
                //playerTest(player, store);
                storeMenu(scanner, store, player);
            } else if (input.equals("4")) {
                System.out.println("Exiting the program...");
                System.exit(0);
                break;
            }  else {
                System.out.println("Invalid command!");
            }
        }
        scanner.close();
    }

    public static void gamePlay(){
        //Game being driven out of gamesetup, instead of here
        Store store = new Store();
        Player player = new Player();
        Scanner scanner = new Scanner(System.in);
        storeMenu(scanner, store, player);
    }

    public static void gameStop(Store store, Player player){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
         if (input.equals("4")) {
            System.out.println("Exiting the program...");
        }
        scanner.close();
    }

    public static void playerTest(Player player, Store store){
        store.displayInventory();
        System.out.println(player.exposeInventory());
        System.out.println(player.getMoney());
        player.buyUsingEscrow(store, store.getItemByName("Sword"));
        store.displayInventory();
        System.out.println((player.exposeInventory()).get(0).getName());
        System.out.println(player.getMoney());
        player.sellUsingEscrow(store, player.getItemByName("Sword"));
        System.out.println(player.exposeInventory());
        System.out.println(player.getMoney());
        store.displayInventory();
    }
}