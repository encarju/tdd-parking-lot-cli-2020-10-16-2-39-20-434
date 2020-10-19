package com.oocl.cultivation.strategy;

import com.oocl.cultivation.carparking.ParkingLot;

import java.util.List;

public interface ParkingStrategy {
    ParkingLot getParkingLotOutOfList(List<ParkingLot> parkingLotList);
}

