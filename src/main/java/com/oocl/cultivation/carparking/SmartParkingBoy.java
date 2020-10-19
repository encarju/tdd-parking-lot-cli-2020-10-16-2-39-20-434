package com.oocl.cultivation.carparking;

import com.oocl.cultivation.strategy.SmartParking;

import java.util.List;

public class SmartParkingBoy extends ParkingBoy{

    public SmartParkingBoy(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
        parkingStrategyType = new SmartParking();
    }

    public SmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

}
