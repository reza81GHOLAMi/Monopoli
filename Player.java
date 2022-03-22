import java.util.ArrayList;
import java.util.Objects;

public class Player {
    final String name;
    int index;
    double budget = 1500;
    ArrayList<Integer> properties = new ArrayList<Integer>();
    ArrayList<Card> cards = new ArrayList<Card>();

    public Player(String name)
    {
        this.name = name;
    }
//    public double getBudget() {
//        return budget;
//    }
//
//    public void setBudget(double budget) {
//        this.budget = budget;
//    }
//
//    public ArrayList<Integer> getProperties() {
//        return properties;
//    }
//
//    public void setProperties(ArrayList<Integer> properties) {
//        this.properties = properties;
//    }
//
//    public ArrayList<Card> getCards() {
//        return cards;
//    }
//
//    public void setCards(ArrayList<Card> cards) {
//        this.cards = cards;
//    }

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
