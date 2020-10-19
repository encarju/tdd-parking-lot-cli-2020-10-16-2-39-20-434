package com.oocl.cultivation;

import java.util.List;

public interface ParkingStrategy {
    ParkingLot getParkingLotOutOfList(List<ParkingLot> parkingLotList);
}

