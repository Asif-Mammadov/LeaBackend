package demo.leabackend.models.repositories;

import demo.leabackend.models.entities.Home;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HomeRepo extends JpaRepository<Home, Long> {
//    @Query("SELECT ")
    Home findByOwnerId(Long owner_id);
    Home findByReservantId(Long reservant_id);
}
