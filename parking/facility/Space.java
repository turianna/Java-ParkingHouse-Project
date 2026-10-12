package parking.facility;

public class Space{
    final private int floorNumber;
    final private int spaceNumber;
    private vehicle.Car occupyingCar;

    public Space(int floorNumber, int spaceNumber){
        this.floorNumber = floorNumber;
        this.spaceNumber = spaceNumber;
    }

    public void addOccupyingCar(vehicle.Car c){
        occupyingCar = c;
    }

    public void removeOccupyingCar(){
        occupyingCar = null;
    }

    public boolean isTaken() {
        if (occupyingCar == null){
            return false;
        }else{
            return true;
        }
    }

    public String getCarLicensePlate(){
        if(this.isTaken()){
            return occupyingCar.getLicensePlate();
        }
        return null;        
    }

    public vehicle.Size getOccupyingCarSize(){
        if(this.isTaken()){
            return occupyingCar.getSpotOccupation();
        }
        return null;
        
    }

    public int getFloorNumber(){
        return floorNumber;
    }

    public int getSpaceNumber(){
        return spaceNumber;
    }

}