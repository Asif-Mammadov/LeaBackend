package demo.leabackend.controllers;

import demo.leabackend.exceptions.GeneralException;
import demo.leabackend.exceptions.HomeNotFoundException;
import demo.leabackend.exceptions.UserNotFoundException;
import demo.leabackend.models.entities.Home;
import demo.leabackend.models.entities.User;
import demo.leabackend.models.repositories.HomeRepo;
import demo.leabackend.models.repositories.UserRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user/{user_id}/reservation/")
public class ReservationController {
    private UserRepo userRepo;
    private HomeRepo homeRepo;

    public ReservationController(UserRepo userRepo, HomeRepo homeRepo) {
        this.userRepo = userRepo;
        this.homeRepo = homeRepo;
    }

    @GetMapping
    public ResponseEntity<Home> getReservation(@PathVariable("user_id") Long reservant_id) {
        Home home = homeRepo.findByReservantId(reservant_id);
        if (home == null) {
            throw new HomeNotFoundException(reservant_id);
        }else {
            return ResponseEntity.ok().body(home);
        }
    }

    @PostMapping("{owner_id}")
    public ResponseEntity<Home> addReservation(@PathVariable("user_id") Long reservant_id, @PathVariable Long owner_id) {
        User owner = userRepo.findById(owner_id).orElse(null);
        User reservant = userRepo.findById(reservant_id).orElse(null);
        if (reservant == null)
            throw new UserNotFoundException(reservant_id);
         else if (owner == null)
            throw new UserNotFoundException(owner_id);
        else {
            Home home = homeRepo.findByOwnerId(owner_id);
            if (home == null)
                throw new HomeNotFoundException(owner_id);
            else if (home.getReservant() != null)
                throw new GeneralException("Home is reserved by user id : " + home.getReservant().getId());
            else {
                home.setReservant(reservant);
                return ResponseEntity.ok().body(homeRepo.save(home));
            }
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteReservation(@PathVariable("user_id") Long reservant_id) {
        Home home = homeRepo.findByReservantId(reservant_id);
        if (home == null)
            throw new GeneralException("No reservation for user id : " + reservant_id);
        else {
            homeRepo.deleteById(home.getId());
            return ResponseEntity.ok().body(home);
        }
    }
}
