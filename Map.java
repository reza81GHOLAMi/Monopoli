
public class Map
{
    static private Map map = new Map();
    House[] houses = new House[24];

    private Map()
    {
        houses[0]= new Parking() ;
        houses[1]= new Blank(Color.Green);
        houses[2]= new Airport();
        houses[3]= new Cinema(Color.Red);
        houses[4]= new Road();
        houses[5]= new Gift();
        houses[6]= new Blank(Color.Yellow);
        houses[7]= new Cinema(Color.Blue);
        houses[8]= new Blank(Color.Red);
        houses[9]= new Road();
        houses[10]=new Airport();
        houses[11]=new Blank(Color.Green);
        houses[12]=new Prison();
        houses[13]=new Blank(Color.Blue);
        houses[14]=new Cinema(Color.Green);
        houses[15]=new Road();
        houses[16]=new Tax();
        houses[17]=new Blank(Color.Red);
        houses[18]=new Blank(Color.Yellow);
        houses[19]=new Airport();
        houses[20]=new Bank();
        houses[21]=new Cinema(Color.Yellow);
        houses[22]=new Blank(Color.Blue);
        houses[23]=new Chance();
    }

    public House getHouse(int i)
    {
        return houses[i];
    }

    static Map getInstance()
    {
        return map ;
    }
}
