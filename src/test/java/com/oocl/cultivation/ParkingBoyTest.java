package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


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

    @Test
    void should_return_correct_two_cars_when_fetch_given_two_tickets(){
        //Given
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        ParkingTicket parkingTicket1 = parkingBoy.park(car1);
        ParkingTicket parkingTicket2 = parkingBoy.park(car2);
        //When
        Car actualCar1 = parkingBoy.fetchCar(parkingTicket1);
        Car actualCar2 = parkingBoy.fetchCar(parkingTicket2);
        //Then
        assertSame(car1,actualCar1);
        assertSame(car2,actualCar2);
    }

    @Test
    void should_return_null_car_when_fetch_given_wrong_ticket(){
        //Given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        ParkingTicket wrongParkingTicket = new ParkingTicket();
        ParkingTicket rightParkingTicket = parkingBoy.park(car);
        //When
        Car actualWrongCar = parkingBoy.fetchCar(wrongParkingTicket);
        Car actulRightCar = parkingBoy.fetchCar(rightParkingTicket);
        //Then
        assertNull(actualWrongCar);
        assertSame(car,actulRightCar);
    }

    @Test
    void should_return_null_car_when_fetch_given_no_ticket(){
        //Given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        //When
        Car actualCar = parkingBoy.fetchCar(null);
        //Then
        assertNull(actualCar);
    }

    @Test
    void should_return_null_car_when_fetch_given_parking_ticket_already_used(){
        //Given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        ParkingTicket parkingTicket = parkingBoy.park(car);
        parkingBoy.fetchCar(parkingTicket);
        //When
        Car actualCar = parkingBoy.fetchCar(parkingTicket);
        //Then
        assertNull(actualCar);
    }

    @Test
    void should_return_null_ticket_when_parking_another_car_given_parking_lot_capacity_1(){
        //Given
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        parkingBoy.park(car1);
        //When
        ParkingTicket parkingTicket = parkingBoy.park(car2);
        //Then
        assertNull(parkingTicket);
    }
}
