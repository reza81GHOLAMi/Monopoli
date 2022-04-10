import java.util.ArrayList;
import java.util.Random;

public class Chance extends House
{

    public Chance() {
        super(Color.White);
    }

    static Card[] cards = new Card[7];

    static
    {
        cards[0] = new Card("200$");
        cards[1] = new Card("Imprison");
        cards[2] = new Card ("Tax");
        cards[3] = new Card("3Forward");
        cards[4] = new Card("Free");
        cards[5] = new Card("TaxFree");
        cards[6] = new Card("Charity");
    }

    public Card getCard()
    {
        Random rand = new Random();
        return cards[rand.nextInt(7)];
    }

    void executeCard (Card card,Player player){
        switch (card.name){
            case "200$":
            {
                player.budget+=200;
                break;
            }
            case "Imprison":
            {
                player.index=12;
                player.inPrison=true;
            }
            case "Tax":
            {
                player.budget*=0.9;
            }
            case "3Forward":{
                player.index+=3;
                Map map=Map.getInstance();
                map.houses[player.index].action(player);
            }
            case "Free":{
                player.cards.add(card);
                break;
            }
            case "TaxFree":{
                player.cards.add(card);
                break;
            }
            case "Charity":{
                Banker banker = Banker.getInstance();
                int charity=(banker.players.size()-1)*10;
                if (player.budget>=charity){
                    for (Player tempPlayer : banker.players){
                        tempPlayer.budget+=10;
                    }
                    player.budget-=(charity+10);
                }
                else {
                    banker.debt(charity,player);
                }
                break;
            }
        }
    }


    @Override
    void action(Player player) {
        Card card = getCard();
        System.out.println(card.name);
        executeCard(card,player);
    }
}
