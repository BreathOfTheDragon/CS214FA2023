public class Main {
    public static Store store = new Store();
    static Player player = new Player();
    static Game game = new Game();

    public void exposeGameSetup(){
            game.gameSetup();
        }
    
        public void exposeGamePlay(){
            game.gamePlay();
        }
    
        public void exposeGameStop(){
            Store store = new Store();
            Player player = new Player();
            game.gameStop(store, player);
        }
    
    public static void main(String[] args) {
        game.gameSetup();
        game.gamePlay();
        game.gameStop(store, player);

    }
}