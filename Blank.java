public class Blank extends House implements Exchangeable{

    int countOfBuilding=0;
    boolean isHotel=false;

    public Blank(Color color) {
        super(color);
    }

    @Override
    public int getPrice()
    {
        if(isHotel)
        {
            return 800 ;//nabayad 600 bashe bayad beporsim
        }
        return 100+countOfBuilding*150;
    }

    @Override
    double getCost(Player player)
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
        player.budget -= getCost(player);
    }

    void build (Player player){
        // TODO
    }
}
