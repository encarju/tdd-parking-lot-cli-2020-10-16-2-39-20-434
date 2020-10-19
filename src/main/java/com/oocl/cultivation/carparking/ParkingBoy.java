package com.oocl.cultivation.carparking;

import com.oocl.cultivation.exceptions.NotProvidedTicketException;
import com.oocl.cultivation.exceptions.UnrecognizedTicketException;
import com.oocl.cultivation.strategy.NormalParking;
import com.oocl.cultivation.strategy.ParkingStrategy;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {

    private ParkingLot parkingLot;

    private List<ParkingLot> parkingLotList = new ArrayList<>();

    public ParkingStrategy parkingStrategyType = new NormalParking();

    public ParkingBoy(ParkingLot parkingLot) {

        this.parkingLotList.add(parkingLot);
    }

    public ParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    public List<ParkingLot> getParkingLotList() {
        return parkingLotList;
    }

    public ParkingTicket park(Car car) {
        parkingLot = parkingStrategyType.getParkingLotOutOfList(parkingLotList);
        setDefaultParkingLot();
        return parkingLot.park(car);
    }

    public void getFetchingLotOutOfList(ParkingTicket parkingTicket){
        parkingLot = parkingLotList.stream().
                filter(parkingLot -> parkingLot.hasParkingTicket(parkingTicket)).
                findFirst().orElseThrow(()-> new UnrecognizedTicketException("Unrecognized parking ticket"));
    }

    public Car fetchCar(ParkingTicket parkingTicket) {
        if(hasProvidedTicket(parkingTicket)){
            getFetchingLotOutOfList(parkingTicket);
            return  parkingLot.fetch(parkingTicket);
        }
        else
            throw new NotProvidedTicketException(Constants.NOT_PROVIDED_TICKET_MSG);
    }

    private boolean hasProvidedTicket(ParkingTicket parkingTicket) {
        return parkingTicket!=null;
    }

    private void setDefaultParkingLot(){
        if(parkingLot == null && parkingLotList.size()>0) {
            parkingLot = parkingLotList.get(0);
        }
    }

}
