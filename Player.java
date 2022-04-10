import java.util.ArrayList;
import java.util.Objects;

public class Player {
    final String name;
    int index=0;
    int lastDice;
    boolean lost = false;
    boolean inPrison = false;
    double budget = 1500;
    ArrayList<Integer> properties = new ArrayList<Integer>();
    ArrayList<Card> cards = new ArrayList<Card>();

    Map map = Map.getInstance();

    public Player(String name)
    {
        this.name = name;
    }

    void resetProperties(){
        for (int i :properties){
            ((Exchangeable)map.houses[i]).reset();
        }
    }

    Player copy()
    {
        Player player = new Player(name);
        player.budget = budget;
        player.lost = lost ;
        player.inPrison = inPrison;
        player.index = index;
        player.lastDice = lastDice;
        player.cards.addAll(cards);
        player.properties.addAll(properties);
        return player ;
    }

    int totalPropertyPrice(){
        int totalPrice=0;
        for (int i :properties){
            totalPrice+= ((Exchangeable)map.houses[i]).getPrice();
        }
        return totalPrice;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

}
