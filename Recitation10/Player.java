import java.util.ArrayList;
import java.util.List;

//import java.util.Scanner;
class Player {

    private double money;
    private static List<Item> inventory;
    public static List<Item> wearable;
    public static List<Item> holdable;
    public static List<Item> eatable;
    public static List<Item> drinakable;
    public static List<Item> equipable;
    public static List<Item> consumable;

    public void acquireItem(Item item) {
        inventory.add(item);
    }

    public void relinquishItem(Item item) {
        inventory.remove(item);
    }

    public Player(double money) {
        inventory = new ArrayList<>();
        this.money = money;
    }

    public void addMoney(double addMoney) {
        money += addMoney;
    }

    public void addItem(Item item) {
        acquireItem(item);
    }

    // errors here. fix return type.
    public void removeMoney(double removeMoney) throws Exception {
        if (removeMoney <= money) {
            money -= removeMoney;
        } else {
            throw new Exception("not enough money");
        }
    }

    public void removeItem(Item item) {
        relinquishItem(item);
    }

    public Item getItemByName(String name) {
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }

    public void wear(Item item) {
        wearable = new ArrayList<>();
        wearable.add(item);
        System.out.print("Item had been worn");
    }

    public void hold(Item item) {
        holdable = new ArrayList<>();
        holdable.add(item);
        System.out.print("Item is in hand");
    }

    public void eat(Item item) {
        eatable = new ArrayList<>();
        eatable.add(item);
        System.out.print("Item has been consumed. MMMMM");
    }

    public void drink(Item item) {
        drinakable = new ArrayList<>();
        drinakable.add(item);
        System.out.print("You drank this item. GULP GULP GULP");
    }

    public void equip(Item item) {
        equipable = new ArrayList<>();
        equipable.add(item);
        System.out.print("Item has been equiped");
    }

    public void consume(Item item) {
        consumable = new ArrayList<>();
        if (inventory.contains(item)) {
            inventory.remove(item);
            consumable.add(item);
        } else {
            System.out.print("Item is not in your inventory!");
        }
    }

    public void use(Item item) {

    }

    public Item sellItem(Item item) {
        Item a = new Item(null, money);
        return a;
    }

    public List<Item> exposeInventory() {
        return inventory;
    }

    public List<Item> exposeWearInventory() {
        return wearable;
    }

    public List<Item> exposeHoldInventory() {
        return holdable;
    }

    public List<Item> exposeEatInventory() {
        return eatable;
    }

    public List<Item> exposeDrinkInventory() {
        return drinakable;
    }

    public List<Item> exposeConsumeInventory() {
        return consumable;
    }

    public List<Item> exposeEquipInventory() {
        return equipable;
    }

    public void exposeCommonMethodConsume(Item item) {
        consume(item);
    }

    public void exposeCommonMethodEquip(Item item) {
        equip(item);
    }

    public void exposeCommonMethodUse(Item item) {
        use(item);
    }
}