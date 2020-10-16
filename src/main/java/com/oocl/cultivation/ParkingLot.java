package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private Map<ParkingTicket,Car> parkingTicketCarMap= new HashMap<>();
    public ParkingLot() {

    }

    public Car fetch(ParkingTicket parkingTicket) {
        return parkingTicketCarMap.get(parkingTicket);
    }

    public ParkingTicket park(Car car) {
        ParkingTicket parkingTicket = new ParkingTicket();
        parkingTicketCarMap.put(parkingTicket,car);
        return parkingTicket;
    }
}
