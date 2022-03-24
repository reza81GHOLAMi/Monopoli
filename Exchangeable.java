public interface Exchangeable
{
    int getPrice();
    double getCost(Player player);

    default boolean sameColor(String name, Color color)
    {
        int count = 0;
        for (int i = 0; i < 24; i++)
        {
            if (Map.getInstance().houses[i].owner.equals(name))
            {
                if (Map.getInstance().houses[i].color == color)
                {
                    count += 1;
                }
            }
        }
        return count == 3;
    }

}
