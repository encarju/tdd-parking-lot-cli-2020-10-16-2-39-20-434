package com.oocl.cultivation;

import com.oocl.cultivation.carparking.Car;
import com.oocl.cultivation.carparking.ParkingBoy;
import com.oocl.cultivation.carparking.ParkingLot;
import com.oocl.cultivation.carparking.ParkingTicket;
import com.oocl.cultivation.carparking.ServiceManager;
import com.oocl.cultivation.exceptions.NotEnoughPositionException;
import com.oocl.cultivation.exceptions.NotProvidedTicketException;
import com.oocl.cultivation.exceptions.UnrecognizedTicketException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ServiceManagerTest {
    @Test
    void should_return_true_when_get_management_list_given_size_greater_zero_parking_boy_added_to_list(){
        //Given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        ServiceManager serviceManager = new ServiceManager(new ParkingLot());
        serviceManager.addToManagementList(parkingBoy);
        //When
        List<ParkingBoy> parkingBoyList = serviceManager.getManagementList();
        boolean hasParkingBoy = !parkingBoyList.isEmpty();
        //Then
        assertTrue(hasParkingBoy);

    }

    @Test
    void should_return_ticket_when_parking_given_a_car_to_service_manager_specified_parking_boy(){
        //Given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        ServiceManager serviceManager = new ServiceManager(new ParkingLot());
        serviceManager.addToManagementList(parkingBoy);
        //When
        ParkingTicket parkingTicket = serviceManager.parkCarByParkingBoy(car);
        //Then
        assertNotNull(parkingTicket);

    }

    @Test
    void should_return_correct_car_when_fetching_given_a_ticket_to_service_manager_specified_parking_boy(){
        //Given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        ServiceManager serviceManager = new ServiceManager(new ParkingLot());
        serviceManager.addToManagementList(parkingBoy);
        ParkingTicket parkingTicket = serviceManager.parkCarByParkingBoy(car);
        //When
        Car actualCar = serviceManager.fetchCarByParkingBoy(parkingBoy,parkingTicket);
        //Then
        assertSame(car,actualCar);

    }

    @Test
    void should_return_ticket_when_parking_given_a_car_to_service_manager(){
        //Given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ServiceManager serviceManager = new ServiceManager(parkingLot);
        //When
        ParkingTicket parkingTicket = serviceManager.park(car);
        //Then
        assertNotNull(parkingTicket);

    }
    @Test
    void should_return_car_when_parking_given_a_ticket_to_service_manager(){
        //Given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ServiceManager serviceManager = new ServiceManager(parkingLot);
        ParkingTicket parkingTicket = serviceManager.park(car);
        //When
        Car actualCar = serviceManager.fetchCar(parkingTicket);
        //Then
        assertSame(car,actualCar);

    }

    @Test
    void should_return_exception_with_message_when_fetch_given_wrong_ticket_to_service_manager_to_parking_boy(){
        //Given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        ParkingTicket wrongParkingTicket = new ParkingTicket();
        ParkingTicket rightParkingTicket = parkingBoy.park(car);
        String expectedMessage = "Unrecognized parking ticket";
        ServiceManager serviceManager = new ServiceManager(new ParkingLot());
        serviceManager.addToManagementList(parkingBoy);
        //When
        Car actualRightCar = parkingBoy.fetchCar(rightParkingTicket);
        Executable executable = () -> serviceManager.fetchCarByParkingBoy(parkingBoy,wrongParkingTicket);
        //Then
        Exception exception = assertThrows(UnrecognizedTicketException.class,executable);
        assertEquals(expectedMessage,exception.getMessage());
        assertSame(car,actualRightCar);
    }

    @Test
    void should_return_exception_car_when_fetch_given_no_ticket_to_service_manager_to_parking_boy(){
        //Given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        ServiceManager serviceManager = new ServiceManager(new ParkingLot());
        serviceManager.addToManagementList(parkingBoy);
        String expectedMessage = "Please provide your parking ticket";
        //When
        Executable executable = () -> serviceManager.fetchCarByParkingBoy(parkingBoy,null);
        //Then
        Exception exception = assertThrows(NotProvidedTicketException.class,executable);
        assertEquals(expectedMessage,exception.getMessage());
    }

    @Test
    void should_return_exception_when_fetch_given_parking_ticket_already_used_to_parking_manager_to_parking_boy(){
        //Given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        ParkingTicket parkingTicket = parkingBoy.park(car);
        ServiceManager serviceManager = new ServiceManager(new ParkingLot());
        serviceManager.addToManagementList(parkingBoy);
        String expectedMessage = "Unrecognized parking ticket";
        parkingBoy.fetchCar(parkingTicket);
        //When
        Executable executable = () -> serviceManager.fetchCarByParkingBoy(parkingBoy,parkingTicket);
        //Then
        Exception exception = assertThrows(UnrecognizedTicketException.class,executable);
        assertEquals(expectedMessage,exception.getMessage());
    }

    @Test
    void should_return_exception_ticket_when_parking_another_car_given_parking_lot_capacity_1(){
        //Given
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        ServiceManager serviceManager = new ServiceManager(new ParkingLot());
        serviceManager.addToManagementList(parkingBoy);
        String expectedMessage = "Not enough position";
        serviceManager.parkCarByParkingBoy(car1);
        //When
        Executable executable = () -> serviceManager.parkCarByParkingBoy(car2);
        //Then
        Exception exception = assertThrows(NotEnoughPositionException.class,executable);
        assertEquals(expectedMessage,exception.getMessage());
    }

    @Test
    void should_return_0_capacity_for_parking_boy_2_when_parking_a_car_given_parking_boy_1_capacity_0_and_parking_boy_capacity_1(){
        //Given
        Car car1 = new Car();
        ParkingLot parkingLot = new ParkingLot(0);
        ParkingLot parkingLot2 = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        ParkingBoy parkingBoy2 = new ParkingBoy(parkingLot2);
        ServiceManager serviceManager = new ServiceManager(new ParkingLot());
        serviceManager.addToManagementList(parkingBoy);
        serviceManager.addToManagementList(parkingBoy2);
        //When
        serviceManager.parkCarByParkingBoy(car1);
        //Then
        assertEquals(0,parkingBoy.getParkingLotList().get(0).getAvailableCapacity());
        assertEquals(0,parkingBoy2.getParkingLotList().get(0).getAvailableCapacity());

    }

}