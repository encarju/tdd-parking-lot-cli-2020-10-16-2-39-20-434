package com.oocl.cultivation;

import java.util.List;

public class SmartParking implements ParkingStrategy {
    @Override
    public ParkingLot getParkingLotOutOfList(List<ParkingLot> parkingLotList) {
        ParkingLot parkingLotOutOfList = parkingLotList.stream().
                reduce((higherParkingLot,
                        parkingLot)-> higherParkingLot.getAvailableCapacity() >= parkingLot.getAvailableCapacity()
                        ? higherParkingLot : parkingLot).get();
        if((parkingLotOutOfList == null && parkingLotList.size()>0)) {
            parkingLotOutOfList = parkingLotList.get(0);
        }
        return parkingLotOutOfList;
    }
}
