package demo.leabackend.controllers;

import demo.leabackend.exceptions.GeneralException;
import demo.leabackend.exceptions.UserNotFoundException;
import demo.leabackend.models.entities.Spoof;
import demo.leabackend.models.entities.User;
import demo.leabackend.models.repositories.SpoofRepo;
import demo.leabackend.models.repositories.UserRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user/{user_id}/spoof")
public class SpoofController {
    private SpoofRepo spoofRepo;
    private UserRepo userRepo;

    public SpoofController(SpoofRepo spoofRepo, UserRepo userRepo) {
        this.spoofRepo = spoofRepo;
        this.userRepo = userRepo;
    }

    @GetMapping
    public ResponseEntity<Spoof> getSpoof(@PathVariable Long user_id) {
        User user = userRepo.findById(user_id).orElse(null);
        if (user == null)
            throw new UserNotFoundException(user_id);
        else {
            Spoof spoof = spoofRepo.findByUserId(user_id);
            if (spoof == null)
                throw new GeneralException("Spoof not found for user id : " + user_id);
            else
                return ResponseEntity.ok().body(spoof);
        }
    }

    @PostMapping
    public ResponseEntity<Spoof> addSpoof(@PathVariable Long user_id, @RequestBody Spoof newSpoof) {
        User user = userRepo.findById(user_id).orElse(null);
        if (user == null) {
            throw new UserNotFoundException(user_id);
        } else {
            Spoof spoof = spoofRepo.findByUserId(user.getId());
            if (spoof != null)
                throw new GeneralException("User already has a spoof");
            else {
                newSpoof.setUser(user);
                return ResponseEntity.ok().body(spoofRepo.save(newSpoof));
            }
        }
    }

    @PutMapping
    public ResponseEntity<Spoof> updateSpoof(@PathVariable Long user_id, @RequestBody Spoof newSpoof) {
        User user = userRepo.findById(user_id).orElse(null);
        if (user == null) {
            throw new UserNotFoundException(user_id);
        } else {
            Spoof spoof = spoofRepo.findByUserId(user.getId());
            if (spoof == null)
                throw new GeneralException("Spoof not found for user id : " + user_id);
            else {
                spoof.setUser(user);
                spoof.setEndDate(newSpoof.getEndDate());
                spoof.setStartLocation(newSpoof.getStartLocation());
                spoof.setEndLocation(newSpoof.getEndLocation());
                return ResponseEntity.ok().body(spoofRepo.save(spoof));
            }
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteHome(@PathVariable Long user_id) {
        Spoof spoof = spoofRepo.findByUserId(user_id);
        if (spoof == null)
            throw new GeneralException("Spoof not found for user id : " + user_id);
        else {
            spoofRepo.deleteById(spoof.getId());
            return ResponseEntity.ok().body(spoof);
        }
    }
}
