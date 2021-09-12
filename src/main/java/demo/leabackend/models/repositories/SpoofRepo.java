package demo.leabackend.models.repositories;

import demo.leabackend.models.entities.Spoof;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpoofRepo extends JpaRepository<Spoof, Long> {
    Spoof findByUserId(Long user_id);
}
