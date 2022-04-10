public abstract class House
{
    //final static Banker banker=Banker.getInstance();
    Color color;
    String owner = "Banker";
    abstract void action (Player player) ;

    public House(Color color)
    {
        this.color = color;
    }
}

