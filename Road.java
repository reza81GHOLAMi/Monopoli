public class Road extends House{

    public Road() {
        super(Color.White);
    }
    Banker banker = Banker.getInstance();
    @Override
    void action(Player player) {
        if (player.budget<100){
            banker.debt(100,player);
        }
        player.budget -= 100;
    }
}
