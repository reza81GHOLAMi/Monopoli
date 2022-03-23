
public class Map
{
    static Map map = new Map();
    House[] houses = new House[24];
    private Map()
    {
        houses[0]= new Parking() ;
        houses[1]= new Blank();
        houses[2]= new Airport();
        houses[3]= new Cinema();
        houses[4]= new Road();
        houses[5]= new Gift();
        houses[6]= new Blank();
        houses[7]= new Cinema();
        houses[8]= new Blank();
        houses[9]= new Road();
        houses[10]=new Airport();
        houses[11]=new Blank();
        houses[12]=new Prison();
        houses[13]=new Blank();
        houses[14]=new Cinema();
        houses[15]=new Road();
        houses[16]=new Tax();
        houses[17]=new Blank();
        houses[18]=new Blank();
        houses[19]=new Airport();
        houses[20]=new Bank();
        houses[21]=new Cinema();
        houses[22]=new Blank();
        houses[23]=new Chance();
    }

    static Map getInstance()
    {
        return map ;
    }
}
