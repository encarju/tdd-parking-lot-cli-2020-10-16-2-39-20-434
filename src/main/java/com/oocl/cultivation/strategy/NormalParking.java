package com.oocl.cultivation.strategy;

import com.oocl.cultivation.carparking.Constants;
import com.oocl.cultivation.carparking.ParkingLot;
import com.oocl.cultivation.exceptions.NotEnoughPositionException;

import java.util.List;

public class NormalParking implements ParkingStrategy{
    @Override
    public ParkingLot getParkingLotOutOfList(List<ParkingLot> parkingLotList) {
        return parkingLotList.stream().
                filter(ParkingLot::isNotFullCapacity).findFirst().orElseThrow(
                        ()->new NotEnoughPositionException(Constants.NOT_ENOUGH_POSITION_MSG));
    }
}
