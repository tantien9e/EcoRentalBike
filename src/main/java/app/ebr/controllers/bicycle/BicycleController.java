package app.ebr.controllers.bicycle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import app.ebr.domains.models.Bicycle;
import app.ebr.domains.models.User;
import app.ebr.repositories.BicycleRepository;
import app.ebr.services.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping(value = "/api/bicycles")
public class BicycleController {

    @Autowired
    private BicycleRepository bicycleRepository;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/")
    private ResponseEntity<List<Bicycle>> getAll() {
        Iterable<Bicycle> bicycles = bicycleRepository.findAll();
        List<Bicycle> _bicycles = new ArrayList<>();
        for (Bicycle bicycle : bicycles) {
            _bicycles.add(bicycle);
        }
        return new ResponseEntity<List<Bicycle>>(_bicycles, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    private ResponseEntity<Bicycle> get(@RequestParam(required = true) long id) {
        Optional<Bicycle> bicycle = this.bicycleRepository.findById(id);
        if (bicycle.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Bicycle>(bicycle.get(), HttpStatus.OK);
    }

    @PostMapping(value = "/{id}/borrow")
    private ResponseEntity<?> borrowBicycle(
            @RequestHeader(required = true, name = HttpHeaders.AUTHORIZATION) String authorization,
            @RequestParam(required = true) long id) {
        String[] _authorization = authorization.split(" ");
        if (_authorization.length != 2 && !_authorization[0].equals("Bearer")) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Algorithm algorithm = Algorithm.HMAC256("secret");
        JWTVerifier verifier = JWT.require(algorithm).build();
        try {
            DecodedJWT decodedJWT = verifier.verify(_authorization[1]);
            long uuid = decodedJWT.getClaim("uuid").asLong();
            User user = this.userService.viewUserById(uuid);
            if (user == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            /// Replace
            Optional<Bicycle> bicycle = this.bicycleRepository.findById(id);
            if (bicycle.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            Bicycle _bicycle = bicycle.get();
            if (_bicycle.isUsing()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            _bicycle.setUsing(true);
            _bicycle.setUser(user);
            _bicycle.setTimeStarted(new Date());
            this.bicycleRepository.save(_bicycle);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

}
