package com.oocl.cultivation;

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
        ServiceManager serviceManager = new ServiceManager();
        serviceManager.addToManagementList(parkingBoy);
        //When
        List<ParkingBoy> parkingBoyList = serviceManager.getManagementList();
        boolean hasParkingBoy = parkingBoyList.size()>0;
        //Then
        assertTrue(hasParkingBoy);

    }

    @Test
    void should_return_ticket_when_parking_given_a_car_to_service_manager_specified_parking_boy(){
        //Given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        ServiceManager serviceManager = new ServiceManager();
        serviceManager.addToManagementList(parkingBoy);
        //When
        ParkingTicket parkingTicket = serviceManager.parkCarByParkingBoy(parkingBoy,car);
        //Then
        assertNotNull(parkingTicket);

    }

    @Test
    void should_return_correct_car_when_fetching_given_a_ticket_to_service_manager_specified_parking_boy(){
        //Given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        ServiceManager serviceManager = new ServiceManager();
        serviceManager.addToManagementList(parkingBoy);
        ParkingTicket parkingTicket = serviceManager.parkCarByParkingBoy(parkingBoy,car);
        //When
        Car actualCar = serviceManager.fetchkCarByParkingBoy(parkingBoy,parkingTicket);
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
    void should_return_null_when_parking_given_a_car_to_service_manager_no_parking_lot(){
        //Given
        Car car = new Car();
        ServiceManager serviceManager = new ServiceManager();
        //When
        ParkingTicket parkingTicket = serviceManager.park(car);
        //Then
        assertNull(parkingTicket);

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
        ServiceManager serviceManager = new ServiceManager();
        serviceManager.addToManagementList(parkingBoy);
        //When
        Car actualRightCar = parkingBoy.fetchCar(rightParkingTicket);
        Executable executable = () -> serviceManager.fetchkCarByParkingBoy(parkingBoy,wrongParkingTicket);
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
        ServiceManager serviceManager = new ServiceManager();
        serviceManager.addToManagementList(parkingBoy);
        String expectedMessage = "Please provide your parking ticket";
        //When
        Executable executable = () -> serviceManager.fetchkCarByParkingBoy(parkingBoy,null);
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
        ServiceManager serviceManager = new ServiceManager();
        serviceManager.addToManagementList(parkingBoy);
        String expectedMessage = "Unrecognized parking ticket";
        parkingBoy.fetchCar(parkingTicket);
        //When
        Executable executable = () -> serviceManager.fetchkCarByParkingBoy(parkingBoy,parkingTicket);
        //Then
        Exception exception = assertThrows(UnrecognizedTicketException.class,executable);
        assertEquals(expectedMessage,exception.getMessage());
    }
}