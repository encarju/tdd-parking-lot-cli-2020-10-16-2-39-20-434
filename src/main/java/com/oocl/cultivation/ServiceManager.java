package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

public class ServiceManager extends ParkingBoy{
    private List<ParkingBoy> managementList;

    public ServiceManager() {
        super();
        managementList = new ArrayList<>();
    }

    public ServiceManager(ParkingLot parkingLot) {
        super(parkingLot);

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

    @Override
    public ParkingTicket park(Car car) {
        if(hasParkingLot()){
            super.getParkingLotOutOfList();
            return super.getParkingLot().park(car);
        }
        return null;

    }

    private boolean hasParkingLot(){
        return super.getParkingLotList().size()>0;
    }
}
