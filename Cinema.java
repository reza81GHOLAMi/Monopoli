public class Cinema extends House implements Exchangeable
{
    public Cinema(Color color)
    {
        super(color);
    }

    @Override
    double getCost()
    {
        return 0;
    }

    @Override
    public int getPrice()
    {
        return 200;
    }
}
