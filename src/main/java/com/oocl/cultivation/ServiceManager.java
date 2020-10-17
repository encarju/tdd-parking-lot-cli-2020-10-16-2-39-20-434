package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

public class ServiceManager {
    private List<ParkingBoy> managementList;

    public ServiceManager() {
        managementList = new ArrayList<>();
    }

    public void addToManagementList(ParkingBoy parkingBoy) {
        managementList.add(parkingBoy);
    }

    public List<ParkingBoy> getManagementList() {
        return managementList;
    }

    public ParkingTicket parkCarByParkingBoy(ParkingBoy parkingBoy, Car car) {
        if(containsParkingBoy(parkingBoy)){
            return parkingBoy.park(car);
        }
        return null;

    }

    private boolean containsParkingBoy(ParkingBoy parkingBoy){
        return managementList.contains(parkingBoy);
    }

    public Car fetchkCarByParkingBoy(ParkingBoy parkingBoy, ParkingTicket parkingTicket) {
        if(containsParkingBoy(parkingBoy)){
            return parkingBoy.fetchCar(parkingTicket);
        }
        return null;
    }
}
