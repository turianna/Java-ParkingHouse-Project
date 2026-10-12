package parking;
import vehicle.Size;

public class ParkingLot{
    final private parking.facility.Space[][] floorPlan;

    public ParkingLot(int floorNumber, int spaceNumber){
        if (floorNumber < 1 || spaceNumber < 1){
            throw new IllegalArgumentException();
        }
        
        this.floorPlan = new parking.facility.Space[floorNumber][spaceNumber];

        for(int i = 0; i < floorNumber; i++){
            for(int j = 0; j <spaceNumber;j++){
                floorPlan[i][j] = new parking.facility.Space(i,j);
            }
        }
    }

    public parking.facility.Space[][] getFloorPlan(){
        return floorPlan;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (int i = floorPlan.length-1; i >= 0; i--){
            for(int j = 0; j < floorPlan[i].length; j++){
                if(floorPlan[i][j].getOccupyingCarSize() == null){
                    sb.append("X");
                }
                if(floorPlan[i][j].getOccupyingCarSize() == Size.SMALL){
                        sb.append("S");
                    }
                if(floorPlan[i][j].getOccupyingCarSize() == Size.LARGE){
                    sb.append("L L");
                    j++;
                }
                if (j < floorPlan[i].length) {
                    sb.append(" ");
                }
            }
            sb.append('\n');                
        }
        return sb.toString();
    }
}