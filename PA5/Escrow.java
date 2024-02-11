import java.util.NoSuchElementException;

class Escrow{

    static double money = 0;
    static Item item;
    static Item requestedItem;

    public Escrow(){

    }

    public static void escrowMoney(Double cost){
        money = cost;
    }

    public static double receiveMoney(){
        double temp = money;
        money = 0;
        return temp;
    }

    public static double returnMoney(){
        Double temp = money;
        money = 0;
        item = null;
        return temp;
    }

    public static void escrowItem(Item escrowedItem){
        item = escrowedItem;
    }

    public static Item receiveItem(){
        Item temp = item;
        item = null;
        return temp;
    }

    public static void requestItem(Item reqItem){
        requestedItem = reqItem;
    }

    public static double getAvailableMoney(){
        return money;
    }

    public static Item getItem(){
        return item;
    }

    public static Item getRequestedItem(){
        return requestedItem;
    }

}