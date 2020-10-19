package com.oocl.cultivation.carparking;

import com.oocl.cultivation.strategy.SuperSmartParking;

import java.util.List;

public class SuperSmartParkingBoy extends ParkingBoy {
    public SuperSmartParkingBoy(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
        parkingStrategyType = new SuperSmartParking();
    }

    public SuperSmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

}
