package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.List;

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
    void should_return_exception_with_message_when_fetch_given_wrong_ticket(){
        //Given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        ParkingTicket wrongParkingTicket = new ParkingTicket();
        ParkingTicket rightParkingTicket = parkingBoy.park(car);
        String expectedMessage = "Unrecognized parking ticket";
        //When
        Car actualRightCar = parkingBoy.fetchCar(rightParkingTicket);
        Executable executable = () -> parkingBoy.fetchCar(wrongParkingTicket);
        //Then
        Exception exception = assertThrows(UnrecognizedTicketException.class,executable);
        assertEquals(expectedMessage,exception.getMessage());
        assertSame(car,actualRightCar);
    }

    @Test
    void should_return_null_car_when_fetch_given_no_ticket(){
        //Given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        String expectedMessage = "Please provide your parking ticket";
        //When
        Executable executable = () -> parkingBoy.fetchCar(null);
        //Then
        Exception exception = assertThrows(NotProvidedTicketException.class,executable);
        assertEquals(expectedMessage,exception.getMessage());
    }

    @Test
    void should_return_null_car_when_fetch_given_parking_ticket_already_used(){
        //Given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        ParkingTicket parkingTicket = parkingBoy.park(car);
        String expectedMessage = "Unrecognized parking ticket";
        parkingBoy.fetchCar(parkingTicket);
        //When
        Executable executable = () -> parkingBoy.fetchCar(parkingTicket);
        //Then
        Exception exception = assertThrows(UnrecognizedTicketException.class,executable);
        assertEquals(expectedMessage,exception.getMessage());
    }

    @Test
    void should_return_null_ticket_when_parking_another_car_given_parking_lot_capacity_1(){
        //Given
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        String expectedMessage = "Not enough position";
        parkingBoy.park(car1);
        //When
        Executable executable = () -> parkingBoy.park(car2);
        //Then
        Exception exception = assertThrows(NotEnoughPositionException.class,executable);
        assertEquals(expectedMessage,exception.getMessage());
    }


    @Test
    void should_return_ticket_when_parking_another_car_given_multiple_parking_lot_and_parking_lot_1_full(){
        //Given
        Car car1 = new Car();
        Car car2 = new Car();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(new ParkingLot(1));
        parkingLotList.add(new ParkingLot(2));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        parkingBoy.park(car1);
        //When
        ParkingTicket parkingTicket = parkingBoy.park(car2);
        //Then
        assertNotNull(parkingTicket);
    }

    @Test
    void should_return_correct_car_when_fetching_given_multiple_car_with_different_parking_lot(){
        //Given
        Car car1 = new Car();
        Car car2 = new Car();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(new ParkingLot(1));
        parkingLotList.add(new ParkingLot(2));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        ParkingTicket parkingTicket1 = parkingBoy.park(car1);
        ParkingTicket parkingTicket2 = parkingBoy.park(car2);
        //When
        Car actualCar1 = parkingBoy.fetchCar(parkingTicket1);
        Car actualCar2 = parkingBoy.fetchCar(parkingTicket2);
        //Then
        assertSame(car1,actualCar1);
        assertSame(car2,actualCar2);
    }
}
