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
        if(hasParkingTicket(parkingTicket)){
            Car car = parkingTicketCarMap.get(parkingTicket);
            parkingTicketCarMap.remove(parkingTicket);
            return car;
        }
        else{
            throw new UnrecognizedTicketException(UNRECOGNIZED_TICKET_MSG);
        }
    }

    public ParkingTicket park(Car car) {
        if(isNotFullCapacity()){
            ParkingTicket parkingTicket = new ParkingTicket();
            parkingTicketCarMap.put(parkingTicket,car);
            return parkingTicket;
        }
        else
        {
            throw new NotEnoughPositionException(NOT_ENOUGH_POSITION_MSG);
        }
    }

    public boolean isNotFullCapacity() {
        return parkingTicketCarMap.size()<capacity;
    }

    public boolean hasParkingTicket(ParkingTicket parkingTicket){
        return parkingTicketCarMap.containsKey(parkingTicket);
    }

    public int getAvailableCapacity(){
        return capacity-parkingTicketCarMap.size();
    }

    public Double getCapacityRate(){
        return Double.valueOf(getAvailableCapacity()/capacity);
    }

}
