package com.oocl.cultivation.carparking;

import com.oocl.cultivation.exceptions.NotEnoughPositionException;
import com.oocl.cultivation.exceptions.UnrecognizedTicketException;

import java.util.concurrent.ConcurrentHashMap;

public class ParkingLot {
    public static final int DEFAULT_CAPACITY = 10;
    private static final String UNRECOGNIZED_TICKET_MSG = "Unrecognized parking ticket";
    private static final String NOT_ENOUGH_POSITION_MSG = "Not enough position";
    private ConcurrentHashMap<ParkingTicket,Car> parkingTicketCarMap= new ConcurrentHashMap<>();
    private int capacity = DEFAULT_CAPACITY ;

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
