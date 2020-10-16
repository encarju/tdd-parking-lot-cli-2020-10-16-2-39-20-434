package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;


class ParkingBoyTest {
    @Test
    void should_return_parking_ticket_when_parking_given_a_car_to_parking_boy(){
        //Given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        //When
        ParkingTicket parkingTicket = parkingBoy.park(car);
        //Then
        assertNotNull(parkingTicket);

    }

    @Test
    void should_return_correct_car_when_fetch_given_parking_ticket(){
        //Given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        ParkingTicket parkingTicket = parkingBoy.park(car);
        //When
        Car actualCar = parkingBoy.fetchCar(parkingTicket);
        //Then
        assertSame(car,actualCar);
    }
}
