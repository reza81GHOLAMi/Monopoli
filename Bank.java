import java.util.ArrayList;

public class Bank extends House
{
    public Bank()
    {
        super(Color.White);
    }

    ArrayList <Player> investors = new ArrayList<Player>();
    ArrayList <Double> investment = new ArrayList<Double>();

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
