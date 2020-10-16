package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private Map<ParkingTicket,Car> parkingTicketCarMap= new HashMap<>();
    private int capacity = 10;

    public ParkingLot() {

    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
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
        if(isFullCapacity()){
            ParkingTicket parkingTicket = new ParkingTicket();
            parkingTicketCarMap.put(parkingTicket,car);
            return parkingTicket;
        }
        return null;
    }

    private boolean isFullCapacity() {
        return parkingTicketCarMap.size()<capacity;
    }

}
