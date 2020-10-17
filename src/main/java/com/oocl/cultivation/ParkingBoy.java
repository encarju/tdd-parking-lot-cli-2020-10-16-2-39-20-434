package com.oocl.cultivation;

import java.util.List;

public class ParkingBoy {

    private static final String NOT_PROVIDED_TICKET_MSG = "Please provide your parking ticket";
    private ParkingLot parkingLot;
    private List<ParkingLot> parkingLotList;

    public ParkingBoy(ParkingLot parkingLot) {

        this.parkingLot = parkingLot;
    }

    public ParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }


    public ParkingTicket park(Car car) {
        return parkingLot.park(car);
    }

    public Car fetchCar(ParkingTicket parkingTicket) {
        if(parkingTicket==null){
            throw new NotProvidedTicketException(NOT_PROVIDED_TICKET_MSG);
        }
        return  parkingLot.fetch(parkingTicket);
    }
}
