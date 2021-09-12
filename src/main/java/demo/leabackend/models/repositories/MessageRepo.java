package demo.leabackend.models.repositories;

import demo.leabackend.models.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepo extends JpaRepository<Message, Long> {

}
