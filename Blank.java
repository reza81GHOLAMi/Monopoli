public class Blank extends Exchangeable
{

    int countOfBuilding = 0;
    boolean isHotel = false;
    int[] blanks = {1, 6, 8, 11, 13, 17, 18, 22};

    public Blank(Color color)
    {
        super(color);
    }

    @Override
    public void reset()
    {
        owner = "Banker";
        isHotel = false;
        countOfBuilding = 0;

    }

    @Override
    public int getPrice()
    {
        if (isHotel)
        {
            return 800;//nabayad 600 bashe bayad beporsim
        }
        return 100 + countOfBuilding * 150;
    }

    @Override
    protected double getCost(Player player)
    {
        int cost = 0;
        if (!(owner.equals("Banker") || owner.equals(player.name)))
        {
            if (isHotel)
            {
                cost = 600;
            } else
            {
                cost = 50 + countOfBuilding * 100;
            }
            if (sameColor(owner, this.color))
                cost *= 2;
        }
        return cost;
    }

    @Override
    void action(Player player)
    {
        Banker banker = Banker.getInstance();
        if (player.budget < getCost(player))
        {
            banker.debt(getCost(player), player);
        }
        player.budget -= getCost(player);
        if (!owner.equals("Banker"))
            banker.getOwner(owner).budget += getCost(player);
    }

    int mapBulidings()
    {
        int count = 0;
        for (int i : blanks)
        {
            if (((Blank) (Map.getInstance().houses[i])).countOfBuilding == 5)
            {
                count += 1;
                continue;
            }
            count += ((Blank) (Map.getInstance().houses[i])).countOfBuilding;
        }
        return count;
    }

    boolean canBuild(Player player)
    {
        for (int i : blanks)
        {
            if (Map.getInstance().houses[i].owner.equals(player.name))
            {
                if (this.countOfBuilding > ((Blank) Map.getInstance().houses[i]).countOfBuilding)
                {
                    return false;
                }
            }
        }
        return true;
    }

    void build(Player player)
    {
        if (owner.equals(player.name))
        {
            if (canBuild(player))
            {
                if (mapBulidings() >= 20)
                {
                    System.out.println("The number of building in the map exceeds 20");
                    return;
                }
                if (countOfBuilding < 4)
                {
                    if (player.budget > 150)
                    {
                        player.budget -= 150;
                        countOfBuilding += 1;
                    } else System.out.println("not enough budget to build ");
                } else if (countOfBuilding == 4) //build hotel
                {
                    if (player.budget > 100)
                    {
                        player.budget -= 100;
                        countOfBuilding += 1;
                        isHotel = true;
                    } else System.out.println("not enough budget to establish a hotel ");
                } else System.out.println("you already have a hotel in this blank");
                return;
            } else System.out.println("divide your buildings equally");
        } else System.out.println("you are not the owner of this blank");
    }

}
