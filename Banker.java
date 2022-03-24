import java.util.ArrayList;

public class Banker
{
    final String name = "Banker";
    ArrayList<Player> players = new ArrayList<Player>();
    Map map = Map.getInstance();

    void addPlayer(String name)
    {
        if(players.size()<4)
        {
            players.add(new Player(name));
        }
        else
        {
            System.out.println("Maximum Players reached."+name+" was not added.please type 'start_game'");
        }
    }

    int survivors()
    {
        int survivor = players.size() ;
        for (Player player : players)
        {
            if (player.lost)
            {
                survivor -= 1;
            }
        }
        return survivor;
    }

    void sell (int index, Player player)
    {
        if(isOwner(index , player))
        {
            if(map.houses[index] instanceof Exchangeable)
            {
                player.budget += ((Exchangeable) map.houses[index]).getPrice()/2.0;
                map.houses[index].owner = "Banker" ;
                // agar blank bood che konim ??
            }
        }

    }

    void buy ()
    {
        //TODO
        return;
    }

    void actionCost()
    {
        //TODO
        return;
    }

    boolean isSufficinet (double price , Player player)
    {
        if(price > player.budget)
        {
            return false;
        }
        return true ;
    }

    boolean isOwner(int index , Player player)
    {
        if(!player.properties.contains(index))
        {
            System.out.format("Player %s is not the owner of block number %d\n",player.name , index);
        }
        return true ;
    }
}
