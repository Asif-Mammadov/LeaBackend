package demo.leabackend.controllers;

import demo.leabackend.controllers.objects.MessageBulk;
import demo.leabackend.exceptions.GeneralException;
import demo.leabackend.exceptions.UserNotFoundException;
import demo.leabackend.models.entities.Message;
import demo.leabackend.models.entities.User;
import demo.leabackend.models.repositories.MessageRepo;
import demo.leabackend.models.repositories.UserRepo;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user/{user_id}/message")
public class MessageController {
    private MessageRepo messageRepo;
    private UserRepo userRepo;

    public MessageController(MessageRepo messageRepo, UserRepo userRepo) {
        this.messageRepo = messageRepo;
        this.userRepo = userRepo;
    }
    @GetMapping
    public ResponseEntity<MessageBulk> getMessages(@PathVariable Long user_id) {
        List<Message> sent = messageRepo.findAllByCreatorId(user_id);
        List<Message> received = messageRepo.findAllByReceiverId(user_id);

        MessageBulk messageBulk = new MessageBulk(sent, received);
        return ResponseEntity.ok().body(messageBulk);
    }

    @PostMapping("/{receiver_id}")
    public ResponseEntity<Message> sendMessage(@PathVariable Long user_id, @PathVariable Long receiver_id, @RequestBody Message message) {
        User creator = userRepo.findById(user_id).orElse(null);
        User receiver = userRepo.findById(receiver_id).orElse(null);

        if (creator == null) {
            throw new UserNotFoundException(user_id);
        } else if (receiver == null)
            throw new UserNotFoundException(receiver_id);
        else {
            if (message.getCreatedDate() == null)
                message.setCreatedDate(new Date());
            message.setCreator(creator);
            message.setReceiver(receiver);
            return ResponseEntity.ok().body(messageRepo.save(message));
        }
    }

    @DeleteMapping("/delete/{message_id}")
    public ResponseEntity<?> deleteMessage(@PathVariable Long user_id, @PathVariable Long message_id) {
        User user = userRepo.findById(user_id).orElse(null);
        if (user == null)
            throw new UserNotFoundException(user_id);
        else {
            Message messageToDelete = messageRepo.findById(message_id).orElse(null);
            if (messageToDelete == null)
                throw new GeneralException("Message not found");
            List<Message> messages = messageRepo.findAllByCreatorId(user_id);
            if (messages.contains(messageToDelete)){
                messageRepo.delete(messageToDelete);
                return ResponseEntity.ok(messageToDelete);
            } else {
                throw new GeneralException("No such message for user id " + user_id);
            }
        }
    }
}
