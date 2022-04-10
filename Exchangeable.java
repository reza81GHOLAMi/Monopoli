public abstract class Exchangeable extends House
{
    public Exchangeable(Color color) {
        super(color);
    }
    public abstract int getPrice();
    protected abstract double  getCost(Player player);

    abstract void reset();

    protected boolean sameColor(String name, Color color)
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
