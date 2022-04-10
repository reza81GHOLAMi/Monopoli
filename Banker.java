import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Banker
{

    private Banker() {
    }

    static private Banker banker = new Banker();

    static Banker getInstance()
    {
        return banker ;
    }


    final String name = "Banker";
    ArrayList<Player> players = new ArrayList<Player>();
    ArrayList<Player> losers = new ArrayList<Player>();
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

    void build (Player player){
        Blank blank =(Blank) map.houses[player.index];
        blank.build(player);
    }

    void free(Player player){
        Prison prison = (Prison) map.houses[player.index];
        prison.free(player);
    }

    void swtich(String player1 , String player2)
    {
        Player a = banker.getOwner(player1);
        Player b = banker.getOwner(player2);

        if(a!=null && b!=null)
        {
            ArrayList<Integer> templist = new ArrayList<Integer>(a.properties);
            a.properties.clear();
            a.properties.addAll(b.properties);
            b.properties.clear();
            b.properties.addAll(templist);
        }
        for (int i = 0; i <a.properties.size() ; i++) {
            map.houses[a.properties.get(i)].owner = a.name;
        }
        for (int i = 0; i <b.properties.size() ; i++) {
            map.houses[b.properties.get(i)].owner = b.name;
        }
    }

    void invest(Player player){
        Bank bank = (Bank)map.houses[player.index];
        bank.invest(player);
    }

    void sell (int index, Player player)
    {
        if(isOwner(index , player))
        {
            if(map.houses[index] instanceof Exchangeable)
            {
                player.budget += ((Exchangeable) map.houses[index]).getPrice()/2.0;
                ((Exchangeable)map.houses[index]).reset();
                player.properties.remove((Integer)index );
            }
        }
        else System.out.println("you are not owner of this block");
    }

    void buy (Player player)
    {
        Exchangeable house=(Exchangeable)map.houses[player.index];
        if (player.budget>house.getPrice() && (house.owner.equals("Banker"))){
            player.budget-=house.getPrice();
            house.owner=player.name;
            player.properties.add(player.index);
        }
        else {
            System.out.println("you can not buy this block");
        }
        return;
    }

    void fly(int index,Player player){
        Airport airport = (Airport) map.houses[player.index];
        airport.fly(index,player);
    }

    boolean isOwner(int index , Player player)
    {
        if(!player.properties.contains(index))
        {
            System.out.format("Player %s is not the owner of block number %d\n",player.name , index+1);
        }
        return true ;
    }

    void debt (double debt,Player player){
        if (player.totalPropertyPrice()/2.0+player.budget<debt){
            player.lost=true;
            player.resetProperties();
            losers.add(player);
            players.remove(player);
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("please enter index of house that you want to sell");
        int index = scanner.nextInt();
        sell(index , player);
        while (player.budget<debt)
        {
            System.out.println("you should sell more properties. you are still in debt.");
            System.out.println("please enter index of house that you want to sell");
            index = scanner.nextInt();
            sell(index , player);
        }
    }

    Player getOwner(String name){
        for (int i = 0; i <players.size() ; i++)
        {
            if (players.get(i).name.equals(name)){
                return players.get(i);
            }
        }
        return null;
    }

    Player[] rank()
    {
        ArrayList<Player> sortedPlayers = new ArrayList<Player>(players);
        Player[] ranking = new Player[players.size()+ losers.size()];
        for (int i = 0; i <losers.size() ; i++)
        {
            ranking[ranking.length-1-i]=losers.get(i);
        }
        for (int i = 0; i <players.size()-1 ; i++)
        {
            for (int j = 0; j <players.size()-1-i ; j++)
            {
                if(sortedPlayers.get(j).totalPropertyPrice()+sortedPlayers.get(j).budget<sortedPlayers.get(j+1).totalPropertyPrice()+sortedPlayers.get(j+1).budget)
                {
                    Collections.swap(sortedPlayers ,j , j+1 );
                }
            }
        }
        for (int i = 0; i < players.size() ; i++)
        {
            ranking[i] = (Player) sortedPlayers.get(i);
        }
        return ranking;
    }


}
