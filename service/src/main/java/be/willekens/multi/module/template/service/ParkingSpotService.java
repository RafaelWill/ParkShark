package be.willekens.multi.module.template.service;

import be.willekens.multi.module.template.domain.models.parking_spot.ParkingSpot;
import be.willekens.multi.module.template.domain.repository.ParkingSpotRepository;
import be.willekens.multi.module.template.infrastructure.exceptions.InvalidLicenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ParkingSpotService {

    private ParkingSpotRepository parkingSpotRepository;
    private ParkingLotService parkingLotService;

    @Autowired
    public ParkingSpotService(ParkingSpotRepository parkingSpotRepository, ParkingLotService parkingLotService) {
        this.parkingSpotRepository = parkingSpotRepository;
        this.parkingLotService = parkingLotService;
    }


    public ParkingSpot createParkingSpot(ParkingSpot parkingSpot) {
        parkingLotService.checkIfIsThereParkingSpotAvailable(parkingSpot.getParkingLotId());
        if (parkingSpot.getMemberId().getLicencePlate().equals(parkingSpot.getLicencePlate())) {
            throw new InvalidLicenceException("Provided licence plate is invalid");
        }
        return parkingSpotRepository.save(parkingSpot);
    }

}