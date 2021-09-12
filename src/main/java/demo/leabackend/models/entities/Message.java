package demo.leabackend.models.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "message")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="creator_id")
    private User creator;
//
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="receiver_id")
    private User receiver;

    private String body;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date createdDate;

    public Message() {
    }

    public Message(String body, Date createdDate) {
        this.body = body;
        this.createdDate = createdDate;
    }

    public Message(User creator, User receiver, String body, Date createdDate) {
        this.creator = creator;
        this.receiver = receiver;
        this.body = body;
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonIdentityReference(alwaysAsId = true)
    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    @JsonIdentityReference(alwaysAsId = true)
    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
