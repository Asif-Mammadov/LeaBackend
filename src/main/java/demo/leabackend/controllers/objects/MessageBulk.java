package demo.leabackend.controllers.objects;

import demo.leabackend.models.entities.Message;

import java.util.List;

public class MessageBulk {
    private List<Message> sent;
    private List<Message> received;

    public MessageBulk(List<Message> sent, List<Message> received) {
        this.sent = sent;
        this.received = received;
    }

    public List<Message> getSent() {
        return sent;
    }

    public void setSent(List<Message> sent) {
        this.sent = sent;
    }

    public List<Message> getReceived() {
        return received;
    }

    public void setReceived(List<Message> received) {
        this.received = received;
    }
}
