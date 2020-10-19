package com.oocl.cultivation;

import java.util.List;

public class SuperSmartParking implements ParkingStrategy {
    @Override
    public ParkingLot getParkingLotOutOfList(List<ParkingLot> parkingLotList) {
        ParkingLot parkingLotOutOfList = parkingLotList.stream().
                reduce((parkingLotWithHigherCapacity,
                        parkingLot)-> parkingLotWithHigherCapacity.getCapacityRate() >= parkingLot.getCapacityRate()
                        ? parkingLotWithHigherCapacity : parkingLot).get();
        if((parkingLotOutOfList == null && parkingLotList.size()>0)) {
            parkingLotOutOfList = parkingLotList.get(0);
        }
        return parkingLotOutOfList;
    }
}
