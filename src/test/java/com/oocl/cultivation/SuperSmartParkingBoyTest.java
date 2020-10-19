package com.oocl.cultivation;

import com.oocl.cultivation.carparking.Car;
import com.oocl.cultivation.carparking.ParkingLot;
import com.oocl.cultivation.carparking.ParkingTicket;
import com.oocl.cultivation.carparking.SuperSmartParkingBoy;
import com.oocl.cultivation.exceptions.NotEnoughPositionException;
import com.oocl.cultivation.exceptions.NotProvidedTicketException;
import com.oocl.cultivation.exceptions.UnrecognizedTicketException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SuperSmartParkingBoyTest {
    @Test
    void should_return_parking_ticket_when_parking_given_a_car_to_super_parking_boy(){
        //Given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLot);
        //When
        ParkingTicket parkingTicket = superSmartParkingBoy.park(car);
        //Then
        assertNotNull(parkingTicket);

    }

    @Test
    void should_return_correct_car_when_fetch_given_parking_ticket(){
        //Given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLot);
        ParkingTicket parkingTicket = superSmartParkingBoy.park(car);
        //When
        Car actualCar = superSmartParkingBoy.fetchCar(parkingTicket);
        //Then
        assertSame(car,actualCar);
    }

    @Test
    void should_return_correct_two_cars_when_fetch_given_two_tickets(){
        //Given
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingLot parkingLot = new ParkingLot();
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLot);
        ParkingTicket parkingTicket1 = superSmartParkingBoy.park(car1);
        ParkingTicket parkingTicket2 = superSmartParkingBoy.park(car2);
        //When
        Car actualCar1 = superSmartParkingBoy.fetchCar(parkingTicket1);
        Car actualCar2 = superSmartParkingBoy.fetchCar(parkingTicket2);
        //Then
        assertSame(car1,actualCar1);
        assertSame(car2,actualCar2);
    }

    @Test
    void should_return_exception_with_message_when_fetch_given_wrong_ticket(){
        //Given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLot);
        ParkingTicket wrongParkingTicket = new ParkingTicket();
        ParkingTicket rightParkingTicket = superSmartParkingBoy.park(car);
        String expectedMessage = "Unrecognized parking ticket";
        //When
        Car actualRightCar = superSmartParkingBoy.fetchCar(rightParkingTicket);
        Executable executable = () -> superSmartParkingBoy.fetchCar(wrongParkingTicket);
        //Then
        Exception exception = assertThrows(UnrecognizedTicketException.class,executable);
        assertEquals(expectedMessage,exception.getMessage());
        assertSame(car,actualRightCar);
    }

    @Test
    void should_return_null_car_when_fetch_given_no_ticket(){
        //Given
        ParkingLot parkingLot = new ParkingLot();
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLot);
        String expectedMessage = "Please provide your parking ticket";
        //When
        Executable executable = () -> superSmartParkingBoy.fetchCar(null);
        //Then
        Exception exception = assertThrows(NotProvidedTicketException.class,executable);
        assertEquals(expectedMessage,exception.getMessage());
    }

    @Test
    void should_return_null_car_when_fetch_given_parking_ticket_already_used(){
        //Given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLot);
        ParkingTicket parkingTicket = superSmartParkingBoy.park(car);
        String expectedMessage = "Unrecognized parking ticket";
        superSmartParkingBoy.fetchCar(parkingTicket);
        //When
        Executable executable = () -> superSmartParkingBoy.fetchCar(parkingTicket);
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
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLot);
        String expectedMessage = "Not enough position";
        superSmartParkingBoy.park(car1);
        //When
        Executable executable = () -> superSmartParkingBoy.park(car2);
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
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLotList);
        superSmartParkingBoy.park(car1);
        //When
        ParkingTicket parkingTicket = superSmartParkingBoy.park(car2);
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
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLotList);
        ParkingTicket parkingTicket1 = superSmartParkingBoy.park(car1);
        ParkingTicket parkingTicket2 = superSmartParkingBoy.park(car2);
        //When
        Car actualCar1 = superSmartParkingBoy.fetchCar(parkingTicket1);
        Car actualCar2 = superSmartParkingBoy.fetchCar(parkingTicket2);
        //Then
        assertSame(car1,actualCar1);
        assertSame(car2,actualCar2);
    }

    @Test
    void should_return_true_when_parking_lot_1_has_ticket_given_super_smart_parking_boy_parking_lot_1_has_more_capacity_rate(){
        //Given
        Car car1 = new Car();
        Car car2 = new Car();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(new ParkingLot(4));
        parkingLotList.add(new ParkingLot(3));
        SuperSmartParkingBoy parkingBoy = new SuperSmartParkingBoy(parkingLotList);
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