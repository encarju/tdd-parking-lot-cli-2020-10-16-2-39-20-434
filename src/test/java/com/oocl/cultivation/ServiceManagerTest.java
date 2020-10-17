package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

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
}