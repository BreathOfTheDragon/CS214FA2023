import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

class Player {
    private List<Item> playerInventory;
    private List<Item> consumed;
    private List<Item> equipped;
    private List<Item> used;
    private Double playerWallet = 0.0;
    Item bag = new Item("bag", 0);

    public Player(){
        playerInventory = new ArrayList<>();
    }

    public Player(double money){
        playerInventory = new ArrayList<>();
        this.playerWallet = money;
    }

    public boolean removeMoney(Double price){
        if(playerWallet >= price){
            playerWallet -= price;
            return true;
        } else
            System.out.println("Player cannot affod that item");
            return false;
    }

    public void addItem(Item item){
        acquireItem(item);
    }

    public void removeItem(Item item){
        relinquishItem(item);
    }

    public void addMoney(Double money){
        playerWallet += money;
    }

    public void wear(Item item){
        use(item);
        System.out.println("Wearing");
    }

    public void eat(Item item){
        consume(item);
        System.out.println("Eating");
    }

    public void drink(Item item){
        consume(item);
        System.out.println("Drinking");
    }

    public void hold(Item item){
        use(item);
        System.out.println("Holding");
    }

    public void use(Item item){
        used.add(item);
        System.out.println(item + "is being used");
    }

    public void consume(Item item){
        consumed.add(item);
        //might want to check if its in invenotry first, but can do later 
        playerInventory.remove(item);
        System.out.println(item + "is being consumed");
    }

    public void equip(Item item){
        equipped.add(item);
        System.out.println(item + "is equipped");
    }

    public Item getItemByName(String itemName){
        for (Item item : playerInventory) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    public void acquireItem(Item item){
        if(removeMoney(item.getPrice())){
            playerInventory.add(item);
        } else {
            return;
        }
    }

    public void relinquishItem(Item item){
        playerInventory.remove(item);
        addMoney(item.getPrice());
    }

    public void buyUsingEscrow(Store store, Item item){
        double moneyInEscrow = 0;

        try {
            if(playerWallet >= item.getPrice()){
                moneyInEscrow = item.getPrice();
            } else {
                throw new Exception("Insufficient Funds");
            }
        } catch (Exception e) {
            e.getMessage();
        }
        
        try {
            Escrow.escrowMoney(moneyInEscrow);
            //playerWallet = playerWallet - moneyInEscrow;
            Escrow.requestItem(item);

            store.customerBuyUsingEscrow();
            //playerInventory.add(item);
            acquireItem(item);
            store.finalizeEscrowBuy();
        } catch (Exception e) {
            e.getMessage();
            playerWallet = playerWallet + Escrow.returnMoney();
        }
        
    }

    public void sellUsingEscrow(Store store, Item item){
        if(playerInventory.contains(item)){
            Escrow.escrowItem(item);
            playerInventory.remove(item);
        }
        store.customerSellUsingEscrow();
        playerWallet = playerWallet + Escrow.receiveMoney();
    }

    public double getMoney(){
        return playerWallet;
    }

    public List<Item> exposeWearInventory(){
        return used;
    }

    public List<Item> exposeHoldInventory(){
        return used;
    }

    public List<Item> exposeEatInventory(){
        return consumed;
    }

    public List<Item> exposeDrinkInventory(){
        return consumed;
    }

    public List<Item> exposeConsumeInventory(){
        return consumed;
    }

    public List<Item> exposeUse(){
        return used;
    }

    public List<Item> exposeEquipInventory(){
        return equipped;
    }

    public List<Item> exposeInventory(){
        return playerInventory;
    }

    public void exposeCommonMethodConsume(){
        exposeConsumeInventory();
    }

    public void exposeCommonMethodEquip(){
        exposeEquipInventory();
    }

    public void exposeCommonMethodUse(){
        exposeUse();
    }

}  