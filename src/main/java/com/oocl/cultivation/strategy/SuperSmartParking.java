package com.oocl.cultivation.strategy;

import com.oocl.cultivation.carparking.Constants;
import com.oocl.cultivation.carparking.ParkingLot;
import com.oocl.cultivation.exceptions.NotEnoughPositionException;

import java.util.Comparator;
import java.util.List;

public class SuperSmartParking implements ParkingStrategy {
    @Override
    public ParkingLot getParkingLotOutOfList(List<ParkingLot> parkingLotList) {
        return parkingLotList.stream()
                .max(Comparator.comparingDouble(ParkingLot::getCapacityRate))
                .orElseThrow(()-> new NotEnoughPositionException(Constants.NOT_ENOUGH_POSITION_MSG));
    }
}
