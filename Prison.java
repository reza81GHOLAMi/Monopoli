public class Prison extends House{
    public Prison()
    {
        super(Color.White);
    }

    @Override
    void action(Player player) {
        return;//Do Nothing
    }

    void free (Player player){
        Card card = new Card("Free");
        if (!player.inPrison){
            System.out.println("you are not in prison");
            return;
        }
        if (player.cards.contains(card)){
            player.cards.remove(player.cards.indexOf(card));
            player.inPrison=false;
            return;
        }
        if (player.budget>=50){
            player.budget-=50;
            player.inPrison=false;
        }
        else System.out.println("not enough budget");
    }
}
