public abstract class House
{
    Color color;
    String owner = "Banker";
    abstract void action () ;

    public House(Color color)
    {
        this.color = color;
    }
}

enum Color
{
    Green, Red, Blue, White, Yellow
}
