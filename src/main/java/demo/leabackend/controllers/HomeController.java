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

import java.util.Date;

@RestController
@RequestMapping("/api/v1/user/{user_id}/home")
public class HomeController {
    private UserRepo userRepo;
    private HomeRepo homeRepo;

    public HomeController(UserRepo userRepo, HomeRepo homeRepo) {
        this.userRepo = userRepo;
        this.homeRepo = homeRepo;
    }

    @GetMapping
    public ResponseEntity<Home> getHome(@PathVariable Long user_id) {
        Home home = homeRepo.findByOwnerId(user_id);
        if (home == null)
            throw new HomeNotFoundException(user_id);
        else
            return ResponseEntity.ok().body(home);
    }

    @PostMapping
    public ResponseEntity<Home> addHome(@PathVariable Long user_id, @RequestBody Home newHome) {
        User owner = userRepo.findById(user_id).orElse(null);
        if (owner == null) {
            throw new UserNotFoundException(user_id);
        } else {
            Home home = homeRepo.findByOwnerId(owner.getId());
            if (home != null)
                throw new GeneralException("User already has a home");
            else {
                newHome.setOwner(owner);
                if (newHome.getCreatedDate() == null)
                    newHome.setCreatedDate(new Date());
                return ResponseEntity.ok().body(homeRepo.save(newHome));
            }
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteHome(@PathVariable Long user_id) {
        Home home = homeRepo.findByOwnerId(user_id);
        if (home == null)
            throw new HomeNotFoundException(user_id);
        else {
            homeRepo.deleteById(home.getId());
            return ResponseEntity.ok().body(home);
        }
    }
}
