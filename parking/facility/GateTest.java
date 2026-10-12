package parking.facility;

import static check.CheckThat.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import check.*;

import vehicle.Car;
import vehicle.Size;
import parking.ParkingLot;

public class GateTest {
    @Test
    public void testFindAnyAvailableSpaceForCar() {
        Car test1 = new Car("ABC111", Size.SMALL, 0);
        Car csmall = new Car("ABC123", Size.SMALL, 0);
        Car clarge = new Car("DEF456", Size.LARGE, 0);
        ParkingLot p = new ParkingLot(3,4);
        Gate g = new Gate(p);

        p.getFloorPlan()[0][0].addOccupyingCar(test1);

        assertEquals(0, g.findAnyAvailableSpaceForCar(csmall).getFloorNumber());
        assertEquals(1, g.findAnyAvailableSpaceForCar(csmall).getSpaceNumber());
        p.getFloorPlan()[0][1].addOccupyingCar(csmall);

        assertEquals(0, g.findAnyAvailableSpaceForCar(clarge).getFloorNumber());
        assertEquals(3, g.findAnyAvailableSpaceForCar(clarge).getSpaceNumber());
    }

    @ParameterizedTest
    @CsvSource({
        "ABC111, LARGE, 0",
        "ABC222, SMALL, 1",
        "ABC333, SMALL, 2",
        "ABC444, LARGE, 2"
    })

    public void testFindPreferredAvailableSpaceForCar(String plate, vehicle.Size size, int preferredFloor) {
        Car c = new Car(plate, size, preferredFloor);
        ParkingLot p = new ParkingLot(4, 5);
        Gate g = new Gate(p);

        Car test1 = new Car("DEF111", Size.LARGE, 0);
        Car test2 = new Car("DEF222", Size.SMALL, 3);
        p.getFloorPlan()[0][0].addOccupyingCar(test1);
        p.getFloorPlan()[3][0].addOccupyingCar(test2);

        assertEquals(preferredFloor, g.findPreferredAvailableSpaceForCar(c).getFloorNumber());
    }

    @ParameterizedTest
    @CsvSource({
        "ABC111, LARGE, 0",
        "ABC222, SMALL, 1",
        "ABC333, SMALL, 2",
        "ABC444, LARGE, 2"
    })

    public void testRegisterCar(String plate, Size size, int preferredFloor) {
        Car c = new Car(plate, size, preferredFloor);
        ParkingLot p = new ParkingLot(3, 4);
        Gate g = new Gate(p);

        assertTrue(g.registerCar(c));

    }

    @ParameterizedTest
    @CsvSource({
        "ABC111, LARGE, 0",
        "ABC222, SMALL, 1",
        "ABC333, SMALL, 2"
    })

    public void testDeRegisterCar(String plate, Size size, int preferredFloor) {
        Car c = new Car(plate, size, preferredFloor);
        ParkingLot p = new ParkingLot(3, 4);
        Gate g = new Gate(p);
        Space[][] parkolohaz = p.getFloorPlan();

        g.registerCar(c);
        g.deRegisterCar(c.getTicketId());

        for(int i = 0; i<parkolohaz.length; i++){
            for(int j = 0; j < parkolohaz[i].length; j++){
                assertEquals(false, parkolohaz[i][j].isTaken());
            }
        }
        
    }

    
    
}