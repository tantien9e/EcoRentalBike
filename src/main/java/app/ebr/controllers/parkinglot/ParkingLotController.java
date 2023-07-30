package app.ebr.controllers.parkinglot;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.ebr.domains.models.ParkingLot;
import app.ebr.repositories.ParkingLotRepository;

@RestController
@RequestMapping(value = "/api/parkinglots")
public class ParkingLotController {

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @GetMapping(value = "/")
    private ResponseEntity<List<ParkingLot>> getAll() {
        Iterable<ParkingLot> parkingLots = this.parkingLotRepository.findAll();
        List<ParkingLot> _parkingLots = new ArrayList<>();
        for (ParkingLot parkingLot : parkingLots) {
            _parkingLots.add(parkingLot);
        }
        return new ResponseEntity<List<ParkingLot>>(_parkingLots, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    private ResponseEntity<ParkingLot> get(@RequestParam(required = true) long id) {
        Optional<ParkingLot> parkingLot = this.parkingLotRepository.findById(id);
        if (parkingLot.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<ParkingLot>(parkingLot.get(), HttpStatus.OK);
    }
}
