package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private Map<ParkingTicket,Car> parkingTicketCarMap= new HashMap<>();
    private int capacity = 10;
    private int slots = 1;

    public ParkingLot() {

    }

    public void setCapacity(int capacity){
        this.capacity = capacity;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        Car car = parkingTicketCarMap.get(parkingTicket);
        parkingTicketCarMap.remove(parkingTicket);
        return car;
    }

    public ParkingTicket park(Car car) {
        if(slots>capacity){
            ParkingTicket parkingTicket = new ParkingTicket();
            parkingTicketCarMap.put(parkingTicket,car);
            slots++;
            return parkingTicket;
        }
        return null;
    }
}
