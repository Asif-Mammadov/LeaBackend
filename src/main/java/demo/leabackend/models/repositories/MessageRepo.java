package demo.leabackend.models.repositories;

import demo.leabackend.models.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepo extends JpaRepository<Message, Long> {
    List<Message> findAllByCreatorId(Long creator_id);
    List<Message> findAllByReceiverId(Long receiver_id);
}
