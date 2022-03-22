
public class Map
{
    static Map map = new Map();
    House[] houses = new House[24];
    private Map()
    {
        houses[0]= new Blank() ;
        // TODO initialize houses
    }

    static Map getInstance()
    {
        return map ;
    }
}
