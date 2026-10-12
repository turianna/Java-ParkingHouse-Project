package parking.facility;
import java.util.ArrayList;

public class Gate{
    final private ArrayList<vehicle.Car> cars;
    final private parking.ParkingLot parkingLot;

    public Gate(parking.ParkingLot parkingLot){
        this.parkingLot = parkingLot;
        this.cars = new ArrayList<vehicle.Car>();
    }

    private parking.facility.Space findTakenSpaceByCar(vehicle.Car c){
        parking.facility.Space[][] parkolohaz = parkingLot.getFloorPlan();
        for(int i = 0; i <parkolohaz.length; i++){
            for(int j = 0; j <parkolohaz[i].length; j++){
                if (parkolohaz[i][j].getCarLicensePlate() != null && parkolohaz[i][j].getCarLicensePlate().equals(c.getLicensePlate())){
                    return parkolohaz[i][j];
                }
            }
        }
        return null;
    }

    private parking.facility.Space findAvailableSpaceOnFloor(int floor, vehicle.Car c){
        parking.facility.Space[][] parkolohaz = parkingLot.getFloorPlan();
        if(floor < 0 || floor >= parkolohaz.length){
            return null;
        }
        for(int i = 0; i < parkolohaz.length; i++){ 
            if(i == floor){
                for (int j = 0; j < parkolohaz[i].length; j++){
                    if (!(parkolohaz[floor][j].isTaken()) && c.getSpotOccupation() == vehicle.Size.SMALL){
                        return parkolohaz[floor][j];
                    }
                    else if(j+1 < parkolohaz[i].length){
                        if (!(parkolohaz[floor][j].isTaken()) && c.getSpotOccupation() == vehicle.Size.LARGE && !(parkolohaz[floor][j+1].isTaken())){
                            return parkolohaz[floor][j+1];
                        }
                    }
                }
            
            }
        }        
        return null;
    }
        

    public parking.facility.Space findAnyAvailableSpaceForCar(vehicle.Car c){
        int height = parkingLot.getFloorPlan().length;
        for (int i = 0; i < height; i++){
            if(findAvailableSpaceOnFloor(i, c) != null){
                return findAvailableSpaceOnFloor(i, c);
            }
        }

        return null;
    }
    

    public parking.facility.Space findPreferredAvailableSpaceForCar(vehicle.Car c){
        parking.facility.Space[][] parkolohaz = parkingLot.getFloorPlan();
        if(findAvailableSpaceOnFloor(c.getPreferredFloor(), c) != null){
            return findAvailableSpaceOnFloor(c.getPreferredFloor(), c);
        }

        for(int i = 0; i < parkolohaz.length; i++){
            if(c.getPreferredFloor() - i >= 0){
                if(findAvailableSpaceOnFloor(c.getPreferredFloor()-1, c) != null){
                    return findAvailableSpaceOnFloor(c.getPreferredFloor()-1, c);
                }
            }
            if(c.getPreferredFloor() + i <= parkolohaz.length){
                if(findAvailableSpaceOnFloor(c.getPreferredFloor()+1, c) != null){
                    return findAvailableSpaceOnFloor(c.getPreferredFloor()+1, c);
                }
            }
        }

        return null;

    }

    public boolean registerCar(vehicle.Car c){
        if(findPreferredAvailableSpaceForCar(c)!=null){
            c.setTicketId(c.getLicensePlate());
            return true;
        }else{
            return false;
        }
    }

    public void registerCars(vehicle.Car... cars){
        for(vehicle.Car c : cars){
            if(findAnyAvailableSpaceForCar(c) == null){
                throw new IllegalArgumentException();
            }

            findAnyAvailableSpaceForCar(c);
        }
    }

    public void deRegisterCar(String ticketId){
        for(vehicle.Car c : cars){
            if(c.getTicketId() == ticketId){
                if(c.getSpotOccupation() == vehicle.Size.SMALL){
                    findTakenSpaceByCar(c).removeOccupyingCar();
                    cars.remove(c);
                }if(c.getSpotOccupation() == vehicle.Size.LARGE){
                    findTakenSpaceByCar(c).removeOccupyingCar();
                    findTakenSpaceByCar(c).removeOccupyingCar();
                    cars.remove(c);
                }
                
            }
        }
    }

}