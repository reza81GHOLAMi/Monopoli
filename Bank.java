import java.util.ArrayList;

public class Bank extends House
{
    public Bank()
    {
        super(Color.White);
    }

    ArrayList <Player> investors = new ArrayList<Player>();
    ArrayList <Double> investment = new ArrayList<Double>();

    void invest(Player player){
        investors.add(player);
        investment.add(player.budget/2.0);
        player.budget=player.budget/2.0;
    }
    @Override
    void action(Player player)
    {
        if (investors.contains(player))
        {
            int i = investors.indexOf(player);
            player.budget+=2*investment.get(i);
            investors.remove(i);
            investment.remove(i);
        }
    }
}
