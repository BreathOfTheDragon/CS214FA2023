import java.util.ArrayList;
import java.util.List;
//import java.util.Scanner;
class Player {

    private double money;
    private List<Item> inventory;

    public void acquireItem(Item item){
        inventory.add(item);
    }
    
    public void relinquishItem(Item item){
        inventory.remove(item);
    }
    
    public Player (double money){
        inventory = new ArrayList<>();
        this.money = money;
    }

    public void addMoney(double addMoney){
        money += addMoney;
    }

    public void addItem(Item item){
        acquireItem(item);
    }

    public boolean removeMoney(double removeMoney){
        if (removeMoney > money){
            System.out.print("you broke dawg");
            return false;
        }
        
        else{
            money -= removeMoney;
            return true;
        }
    }

    public void removeItem(Item item){
        relinquishItem(item);
    }

    public Item getItemByName(String name){
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }

    public void Wear(){
        System.out.print("Item had been worn");
    }

    public void Hold(){
        System.out.print("Item is in hand");
    }

    public void Eat(){
        System.out.print("Item has been consumed. MMMMM");
    }

    public void Drink(){
        System.out.print("You drank this item. GULP GULP GULP");
    }

    public void equipItem(){
        System.out.print("Item has been consumed");
    }

    public void consume(Item item){
        if(inventory.contains(item)){
            inventory.remove(item);
        }

        else{
            System.out.print("Item is not in your inventory!");
        }
    }
    
    public void useEquippedItem(){

    }

    public Item sellItem(Item item){
        Item a = new Item(null, money);
        return a;
    }
}        