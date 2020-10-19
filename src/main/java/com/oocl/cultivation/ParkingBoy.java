package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {

    private static final String NOT_PROVIDED_TICKET_MSG = "Please provide your parking ticket";

    private ParkingLot parkingLot;

    private List<ParkingLot> parkingLotList = new ArrayList<>();

    public ParkingStrategy parkingStrategyType = new NormalParking();

    public ParkingBoy(ParkingLot parkingLot) {

        this.parkingLotList.add(parkingLot);
    }

    public ParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    public ParkingBoy() {

    }

    public List<ParkingLot> getParkingLotList() {
        return parkingLotList;
    }

    public ParkingTicket park(Car car) {
        parkingLot = parkingStrategyType.getParkingLotOutOfList(parkingLotList);
        setDefaultParkingLot();
        return parkingLot.park(car);
    }

    public void getFetchingLotOutOfList(ParkingTicket parkingTicket){
        parkingLot = parkingLotList.stream().
                filter(parkingLot -> parkingLot.hasParkingTicket(parkingTicket)).findFirst().orElse(null);
        setDefaultParkingLot();
    }

    public Car fetchCar(ParkingTicket parkingTicket) {
        if(parkingTicket==null){
            throw new NotProvidedTicketException(NOT_PROVIDED_TICKET_MSG);
        }
        getFetchingLotOutOfList(parkingTicket);
        return  parkingLot.fetch(parkingTicket);
    }

    private void setDefaultParkingLot(){
        if(parkingLot == null && parkingLotList.size()>0) {
            parkingLot = parkingLotList.get(0);
        }
    }

}
