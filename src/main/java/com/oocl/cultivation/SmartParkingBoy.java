package com.oocl.cultivation;

import java.util.List;

public class SmartParkingBoy extends ParkingBoy{

    public SmartParkingBoy(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
    }

    public SmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    @Override
    public void getParkingLotOutOfList() {
        parkingLot = parkingLotList.stream().
                reduce((higherParkingLot,
                        parkingLot)-> higherParkingLot.getAvailableCapacity() >= parkingLot.getAvailableCapacity()
                        ? higherParkingLot : parkingLot).get();
    }

}
