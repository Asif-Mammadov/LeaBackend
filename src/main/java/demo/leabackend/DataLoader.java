package demo.leabackend;


import demo.leabackend.models.entities.Home;
import demo.leabackend.models.entities.User;
import demo.leabackend.models.repositories.HomeRepo;
import demo.leabackend.models.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class DataLoader implements ApplicationRunner {

    private UserRepo userRepo;
    private HomeRepo homeRepo;

    public DataLoader(UserRepo userRepo, HomeRepo homeRepo) {
        this.userRepo = userRepo;
        this.homeRepo = homeRepo;
    }

    public void run(ApplicationArguments args) {
        userRepo.save(new User("Jon", "Snow", "john.snow@gmail.com", "0515553212", "Hello, I am Jon Snow", "40.4093;49.8671"));
        User user = userRepo.findByEmail("john.snow@gmail.com");
        homeRepo.save(new Home(true, false, true, 2, 300, new Date(2021, Calendar.SEPTEMBER, 11, 20, 0, 0), true, "49.4524;50.3235", user));
//        homeRepo.findById(1L).map((home) -> {
//                    home.setReservant(user);
//                    return homeRepo.save(home);
//                })
//                .orElseGet(() -> {return null;});
    }
}
