public class Blank extends House implements Exchangeable{
    int countOfBuilding=0;
    boolean isHotel=false;

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
    double getCost() {
        if (isHotel) {
            return 50 + countOfBuilding * 100;
        }
        return 600;
    }
    
    void build (Player player){
        // TODO
    }
}
