package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

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
}