package com.oocl.cultivation.strategy;

import com.oocl.cultivation.carparking.ParkingLot;

import java.util.List;

public class SmartParking implements ParkingStrategy {
    @Override
    public ParkingLot getParkingLotOutOfList(List<ParkingLot> parkingLotList) {
        ParkingLot parkingLotOutOfList = parkingLotList.stream().
                reduce((parkingLotWithHigherCapacity,
                        parkingLot)-> parkingLotWithHigherCapacity.getAvailableCapacity()
                        >= parkingLot.getAvailableCapacity() ? parkingLotWithHigherCapacity : parkingLot).get();
        if((parkingLotOutOfList == null && parkingLotList.size()>0)) {
            parkingLotOutOfList = parkingLotList.get(0);
        }
        return parkingLotOutOfList;
    }
}
