public class Tax extends House{

    public Tax() {
        super(Color.White);
    }

    @Override
    void action(Player player) {
        Card card = new Card("TaxFree");
        if (player.cards.contains(card)){
            player.cards.remove(player.cards.indexOf(card));
            return;
        }
        player.budget=player.budget*9/10;
    }

}
