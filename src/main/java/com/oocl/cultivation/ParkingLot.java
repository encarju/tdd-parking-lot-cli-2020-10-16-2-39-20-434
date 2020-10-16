package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private static final String UNRECOGNIZED_TICKET_MSG = "Unrecognized parking ticket";
    private static final String NOT_ENOUGH_POSITION_MSG = "Not enough position";
    private Map<ParkingTicket,Car> parkingTicketCarMap= new HashMap<>();
    private int capacity = 10;

    public ParkingLot() {

    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        Car car = parkingTicketCarMap.get(parkingTicket);
        if(car==null){
            throw new UnrecognizedTicketException(UNRECOGNIZED_TICKET_MSG);
        }else{
            parkingTicketCarMap.remove(parkingTicket);
        }
        return car;
    }

    public ParkingTicket park(Car car) {
        if(isFullCapacity()){
            ParkingTicket parkingTicket = new ParkingTicket();
            parkingTicketCarMap.put(parkingTicket,car);
            return parkingTicket;
        }
        else
        {
            throw new NotEnoughPositionException(NOT_ENOUGH_POSITION_MSG);
        }
    }

    private boolean isFullCapacity() {
        return parkingTicketCarMap.size()<capacity;
    }

}
