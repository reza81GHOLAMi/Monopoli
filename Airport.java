public class Airport extends House {
    public Airport() {
        super(color.White);
    }

    void fly(int index, Player player){
        if (index==player.index){
            System.out.println("***");
            return;
        }
        if ((index==3 || index==11 || index==20) && player.budget>=50){
            player.index=index;
            player.budget-=50;
            return;
        }
        System.out.println("can not fly");
        //2 dalil vogod dard;
    }
    
}
