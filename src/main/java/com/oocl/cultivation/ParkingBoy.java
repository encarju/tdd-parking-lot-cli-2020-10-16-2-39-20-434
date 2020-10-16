package com.oocl.cultivation;

public class ParkingBoy {

    private ParkingLot parkingLot;

    public ParkingBoy(ParkingLot parkingLot) {

        this.parkingLot = parkingLot;
    }


    public ParkingTicket park(Car car) {
        return parkingLot.park(car);
    }

    public Car fetchCar(ParkingTicket parkingTicket) {
        if(parkingTicket==null){
            throw new NotProvidedTicketException("Please provide your parking ticket");
        }
        return  parkingLot.fetch(parkingTicket);
    }
}
