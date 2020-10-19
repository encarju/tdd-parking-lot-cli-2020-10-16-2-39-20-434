package com.oocl.cultivation;

import java.util.List;

public class NormalParking implements ParkingStrategy{
    @Override
    public ParkingLot getParkingLotOutOfList(List<ParkingLot> parkingLotList) {
        ParkingLot parkingLotOutOfList = parkingLotList.stream().
                filter(parkingLot -> parkingLot.isNotFullCapacity()).findFirst().orElse(null);
        if((parkingLotOutOfList == null && parkingLotList.size()>0)) {
            parkingLotOutOfList = parkingLotList.get(0);
        }
        return parkingLotOutOfList;
    }
}
