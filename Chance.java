import java.util.Random;

public class Chance
{
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
}
