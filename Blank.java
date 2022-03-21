public class Blank extends House {
    int countOfBuilding=0;
    boolean isHotel=false;
    int price=100;

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
