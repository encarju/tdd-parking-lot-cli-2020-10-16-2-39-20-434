package com.oocl.cultivation.strategy;

import com.oocl.cultivation.carparking.Constants;
import com.oocl.cultivation.carparking.ParkingLot;
import com.oocl.cultivation.exceptions.NotEnoughPositionException;

import java.util.List;

public class SmartParking implements ParkingStrategy {
    @Override
    public ParkingLot getParkingLotOutOfList(List<ParkingLot> parkingLotList) {
        ParkingLot parkingLotOutOfList = parkingLotList.stream().
                reduce((parkingLotWithHigherCapacity,
                        parkingLot)-> parkingLotWithHigherCapacity.getAvailableCapacity()
                        >= parkingLot.getAvailableCapacity() ? parkingLotWithHigherCapacity : parkingLot)
                .orElseThrow(()-> new NotEnoughPositionException(Constants.NOT_ENOUGH_POSITION_MSG));
        return parkingLotOutOfList;
    }
}
