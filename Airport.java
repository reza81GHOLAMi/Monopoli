public class Airport extends House {
    public Airport() {
        super(Color.White);
    }

    @Override
    void action(Player player) {
        return;//Do Nothing;
    }

    void fly(int index, Player player){
        if (index==player.index){
            System.out.println("you already here");
            return;
        }
        if ((index==3 || index==11 || index==20)){
            if (player.budget>=50) {
                player.index = index-1;
                player.budget -= 50;
                return;
            }
            else System.out.println("not enough budget");
        }
        else System.out.println("you must fly to another airport\n please enter airport index");
    }
    
}
