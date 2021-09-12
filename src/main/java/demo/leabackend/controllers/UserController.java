package demo.leabackend.controllers;

import demo.leabackend.exceptions.UserNotFoundException;
import demo.leabackend.models.entities.User;
import demo.leabackend.models.repositories.UserRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user/")
public class UserController {
    private UserRepo userRepo;

    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return userRepo.findById(id)
                .map(user -> {
                    return ResponseEntity.ok(user);
                })
                .orElseThrow( () -> new UserNotFoundException(id));
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User newUser) {
        return ResponseEntity.ok().body(userRepo.save(newUser));
    }


    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        User user  = userRepo.findById(id).orElse(null);
        if (user == null)
            throw new UserNotFoundException(id);
        else {
            userRepo.delete(user);
            return ResponseEntity.ok().body(user);
        }
    }
}
