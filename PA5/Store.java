import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Store {
    private List<Item> inventory;
    private Double storeWallet;
    private List<Player> players_in_store; 

    public Store() {
        inventory = new ArrayList<>();
        players_in_store = new ArrayList<>();
        storeWallet = 0.0;
    }

    public void enter(Player player){
        if (check_player_in_store(player) == false){
            players_in_store.add(player);
        } else {
            System.out.println("Player is already in the store.");
        }
    }

    public void exit(Player player){
         if (check_player_in_store(player) == true){
            players_in_store.remove(player);
        } else {
            System.out.println("Player never entered the store.");
        }
    }

    public void addItem(Item item) {
        inventory.add(item);
    }

    public void removeItem(Item item) {
        inventory.remove(item);
    }

    public void deleteItem(Item item){
        removeItem(item);
    }

    public void displayInventory() {
        System.out.println("Store Inventory:");
        for (Item item : inventory) {
            System.out.println(item.getName() + " - $" + item.getPrice());
        }
    }

    private boolean check_player_in_store(Player player){
        int index =  players_in_store.indexOf(player);
        if (index == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Item getItemByName(String name) {
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }

    public boolean buyItem(Item item, Player player) {
        if (check_player_in_store(player) == false){
            System.out.println("Player needs to enter the store before being able to buy anything");
            return false;
        }
        
        if (inventory.contains(item)) {
            if (player.removeMoney(item.getPrice())) {
                inventory.remove(item);
                player.addItem(item);
                return true;
            }
        } else {
            System.out.println("Item not available in the store.");
        }
        return false;
    }

    public boolean sellItem(Item item, Player player) {
        if (check_player_in_store(player) == false){
            System.out.println("Player needs to enter the store before being able to sell anything");
            return false;
        }
        player.removeItem(item);
        player.addMoney(item.getPrice());
        inventory.add(item);
        return true;
    }

    public void customerBuyUsingEscrow(){
        sellUsingEscrow();
    }

    private void sellUsingEscrow(){
        Item item = Escrow.getRequestedItem();
        try{
            if(!inventory.contains(item)){
                throw new Exception("Unstocked Item");
            }
            if(Escrow.getAvailableMoney() >= item.getPrice()){
                Escrow.escrowItem(item);
                inventory.remove(item);
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Returning money");
            Escrow.returnMoney();
        }

    }
   /*  if(inventory.contains(item)){
            if(Escrow.getAvailableMoney() >= item.getPrice()){
                Escrow.escrowItem(item);
                inventory.remove(item);
            } else {
                System.out.println("Returning money");
                Escrow.returnMoney();
            }
        } else {
            System.out.println("Item doesn't exist");
        }
 */
    public void customerSellUsingEscrow(){
        buyUsingEscrow();
    }

    private void buyUsingEscrow(){
        Item item = Escrow.requestedItem;
        double moneyInEscrow = 0;
        if(storeWallet >= item.getPrice()){
             moneyInEscrow = item.getPrice();
        } else {
            return;
        }
        Escrow.escrowMoney(moneyInEscrow);
        storeWallet = storeWallet - moneyInEscrow;
        inventory.add(Escrow.receiveItem());
        try {
            inventory.contains(item);
            finalizeEscrowSell();
        } catch (Exception e) {
            e.getMessage();
            storeWallet = storeWallet + Escrow.returnMoney();
        }

    }

     public void finalizeEscrowBuy(){
        Item item = Escrow.getItem();
        try{
            removeItem(item);
        } catch (Exception e){
            e.getMessage();
        }
        storeWallet = storeWallet + Escrow.receiveMoney();
    }

    public void finalizeEscrowSell(){
        Item item = Escrow.getItem();
        try{
            //addItem(item);
        } catch (Exception e){
            e.getMessage();
        }
    }
}