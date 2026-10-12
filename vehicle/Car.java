package vehicle;

public class Car{

    final private String licensePlate;
    final private vehicle.Size spotOccupation;
    private int preferredFloor; // nem biztos, h. kell a final, lehet elég annyi, h. itt van setter
    private String ticketId;

    public Car(String licensePlate, vehicle.Size spotOccupation, int preferredFloor){
        this.licensePlate = licensePlate;
        this.spotOccupation = spotOccupation;
        this.preferredFloor = preferredFloor;
    }

    public String getLicensePlate(){
        return licensePlate;
    }

    public vehicle.Size getSpotOccupation(){
        return spotOccupation;
    }

    public int getPreferredFloor(){
        return preferredFloor;
    }

    public void setPreferredFloor(int preferredFloor){
        this.preferredFloor = preferredFloor;
    }

    public String getTicketId(){
        return ticketId;
    }

    public void setTicketId(String ticketId){
        this.ticketId = ticketId;
    }

}