package demo.leabackend.controllers;

import demo.leabackend.models.entities.Home;
import demo.leabackend.models.entities.User;
import demo.leabackend.models.repositories.HomeRepo;
import demo.leabackend.models.repositories.UserRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/list")
public class ListController {
    HomeRepo homeRepo;
    UserRepo userRepo;

    public ListController(HomeRepo homeRepo, UserRepo userRepo) {
        this.homeRepo = homeRepo;
        this.userRepo = userRepo;
    }

    @GetMapping("/home")
    public ResponseEntity<List<Home>> listHome() {
        return ResponseEntity.ok().body(homeRepo.findAll());
    }

    @GetMapping("/user")
    public ResponseEntity<List<User>> listUser() {
        return ResponseEntity.ok().body(userRepo.findAll());
    }

}
