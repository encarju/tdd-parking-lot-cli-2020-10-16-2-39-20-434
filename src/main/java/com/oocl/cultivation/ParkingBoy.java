package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParkingBoy {

    private static final String NOT_PROVIDED_TICKET_MSG = "Please provide your parking ticket";
    public ParkingLot parkingLot;
    public List<ParkingLot> parkingLotList = new ArrayList<>();

    public ParkingBoy(ParkingLot parkingLot) {

        this.parkingLotList.add(parkingLot);
    }

    public ParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    public void getParkingLotOutOfList() {
        parkingLot = parkingLotList.stream().
                filter(parkingLot -> parkingLot.isNotFullCapacity()).findFirst().orElse(null);
        if((parkingLot == null && parkingLotList.size()>0)) {
            parkingLot = parkingLotList.get(0);
        }
    }

    public ParkingTicket park(Car car) {
        getParkingLotOutOfList();
        return parkingLot.park(car);
    }

    public void getFetchingLotOutOfList(ParkingTicket parkingTicket){
        parkingLot = parkingLotList.stream().
                filter(parkingLot -> parkingLot.hasParkingTicket(parkingTicket)).findFirst().orElse(null);
        if((parkingLot == null && parkingLotList.size()>0)) {
            parkingLot = parkingLotList.get(0);
        }
    }

    public Car fetchCar(ParkingTicket parkingTicket) {
        if(parkingTicket==null){
            throw new NotProvidedTicketException(NOT_PROVIDED_TICKET_MSG);
        }
        getFetchingLotOutOfList(parkingTicket);
        return  parkingLot.fetch(parkingTicket);
    }
}
