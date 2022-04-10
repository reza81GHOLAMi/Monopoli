public class Cinema extends Exchangeable
{
    public Cinema(Color color)
    {
        super(color);
    }
    @Override
    public void reset() {
        owner="Banker";
    }

    int cinemaCount(String name)
    {
        int count = 0;
        int[] index = {3 , 21 , 14};
        for (int i:index)
        {
            if(Map.getInstance().houses[i].owner.equals(name))
                count += 1;
        }
        return count;
    }

    @Override
    public double getCost(Player player) // nabayad public bashad
    {
        int cost = 0 ;
        if(!(owner.equals("Banker") || owner.equals(player.name)))
        {
            cost = 25*cinemaCount(owner);
            if(sameColor(owner ,this.color))
            {
                cost =cost*2 ;
            }
        }
        return cost;
    }

    @Override
    void action(Player player)
    {
        Banker banker = Banker.getInstance();
        if (player.budget<getCost(player)){
            banker.debt(getCost(player),player);
        }
        player.budget -= getCost(player);
        if(!owner.equals("Banker"))
        {
            banker.getOwner(owner).budget+=getCost(player);
        }
    }


    @Override
    public int getPrice()
    {
        return 200;
    }
}
