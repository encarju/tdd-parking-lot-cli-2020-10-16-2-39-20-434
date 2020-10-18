package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParkingBoy {

    private static final String NOT_PROVIDED_TICKET_MSG = "Please provide your parking ticket";

    private ParkingLot parkingLot;

    private List<ParkingLot> parkingLotList = new ArrayList<>();

    public ParkingBoy(ParkingLot parkingLot) {

        this.parkingLotList.add(parkingLot);
    }

    public ParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    public ParkingBoy() {

    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public List<ParkingLot> getParkingLotList() {
        return parkingLotList;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }


    public void getParkingLotOutOfList() {
        parkingLot = parkingLotList.stream().
                filter(parkingLot -> parkingLot.isNotFullCapacity()).findFirst().orElse(null);
        setDefaultParkingLot();
    }

    public ParkingTicket park(Car car) {
        getParkingLotOutOfList();
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
        if((parkingLot == null && parkingLotList.size()>0)) {
            parkingLot = parkingLotList.get(0);
        }
    }

}
