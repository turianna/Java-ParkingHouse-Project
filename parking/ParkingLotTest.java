package parking;

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
import parking.facility.Space;

public class ParkingLotTest {
    @Test
    public void testConstructorWithInvalidValues() {
        assertThrows(IllegalArgumentException.class, () -> new ParkingLot(0,0));
        assertThrows(IllegalArgumentException.class, () -> new ParkingLot(0,-5));
        assertThrows(IllegalArgumentException.class, () -> new ParkingLot(4,0));
        assertThrows(IllegalArgumentException.class, () -> new ParkingLot(-4,-5));
    }

    @Test
    public void testTextualRepresentation() {
        ParkingLot p = new ParkingLot(3, 4);
        Car c1 = new Car("ABC111", Size.SMALL, 0);
        Car c2 = new Car("ABC222", Size.LARGE, 0);
        Car c3 = new Car("ABC333", Size.SMALL, 1);
        Car c4 = new Car("ABC444", Size.SMALL, 1);
        Car c5 = new Car("ABC555", Size.LARGE, 2);
        Space[][] parkolohaz = p.getFloorPlan();

        parkolohaz[0][0].addOccupyingCar(c1);
        parkolohaz[0][2].addOccupyingCar(c2);
        parkolohaz[1][1].addOccupyingCar(c3);
        parkolohaz[1][3].addOccupyingCar(c4);
        parkolohaz[2][1].addOccupyingCar(c5);

        assertEquals("X L L X \nX S X S \nS X L L \n", p.toString());

        parkolohaz[1][3].removeOccupyingCar();
        parkolohaz[0][2].removeOccupyingCar();

        assertEquals("X L L X \nX S X X \nS X X X \n", p.toString());



    }

    
    
    
}

