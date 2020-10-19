package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SmartParkingBoyTest {
    @Test
    void should_return_true_when_parking_lot_2_has_ticket_given_smart_parking_boy_parking_lot_2_more_capacity(){
        //Given
        Car car1 = new Car();
        Car car2 = new Car();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(new ParkingLot(3));
        parkingLotList.add(new ParkingLot(3));
        SmartParkingBoy parkingBoy = new SmartParkingBoy(parkingLotList);
        ParkingTicket parkingTicket1 = parkingBoy.park(car1);
        ParkingTicket parkingTicket2 = parkingBoy.park(car2);
        //When
        boolean hasParkingTicket1 = parkingLotList.get(0).hasParkingTicket(parkingTicket1);
        boolean hasParkingTicket2 = parkingLotList.get(1).hasParkingTicket(parkingTicket2);
        //Then
        assertTrue(hasParkingTicket1);
        assertTrue(hasParkingTicket2);
    }
}