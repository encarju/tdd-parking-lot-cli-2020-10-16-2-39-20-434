package com.oocl.cultivation;

import java.util.List;

public class SuperSmartParkingBoy extends ParkingBoy {
    public SuperSmartParkingBoy(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
    }

    public SuperSmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    @Override
    public void getParkingLotOutOfList() {
        parkingLot = parkingLotList.stream().
                reduce((higherParkingLot,
                        parkingLot)-> higherParkingLot.getCapacityRate() >= parkingLot.getCapacityRate()
                        ? higherParkingLot : parkingLot).get();
    }
}
