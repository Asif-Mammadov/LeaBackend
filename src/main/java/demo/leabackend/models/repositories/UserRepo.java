package demo.leabackend.models.repositories;

import demo.leabackend.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByPhoneNumber(String number);
    User findByEmail(String email);
}
