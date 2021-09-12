package demo.leabackend.models.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "home")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Home {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean hasChildren;
    private boolean hasPets;
    private boolean hasWifi;
    private Integer numRooms;
    private Integer durationOfStay; //in minutes
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date createdDate;
    private boolean isActive;
    private String location;

//    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
//@JsonIdentityReference(alwaysAsId = true)
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="owner_id")
    private User owner;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="reservant_id")
    private User reservant;

    public Home () {}

    public Home(boolean hasChildren, boolean hasPets, boolean hasWifi, Integer numRooms, Integer durationOfStay, Date createDate, boolean isActive, String location) {
        this.hasChildren = hasChildren;
        this.hasPets = hasPets;
        this.hasWifi = hasWifi;
        this.numRooms = numRooms;
        this.durationOfStay = durationOfStay;
        this.createdDate = createDate;
        this.isActive = isActive;
        this.location = location;
    }

    public Home(boolean hasChildren, boolean hasPets, boolean hasWifi, Integer numRooms, Integer durationOfStay, Date createDate, boolean isActive, String location, User owner, User reservant) {
        this.hasChildren = hasChildren;
        this.hasPets = hasPets;
        this.hasWifi = hasWifi;
        this.numRooms = numRooms;
        this.durationOfStay = durationOfStay;
        this.createdDate = createDate;
        this.isActive = isActive;
        this.location = location;
        this.owner = owner;
        this.reservant = reservant;
    }

    public Home(boolean hasChildren, boolean hasPets, boolean hasWifi, Integer numRooms, Integer durationOfStay, Date createDate, boolean isActive, String location, User owner) {
        this.hasChildren = hasChildren;
        this.hasPets = hasPets;
        this.hasWifi = hasWifi;
        this.numRooms = numRooms;
        this.durationOfStay = durationOfStay;
        this.createdDate = createDate;
        this.isActive = isActive;
        this.location = location;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public boolean isHasPets() {
        return hasPets;
    }

    public void setHasPets(boolean hasPets) {
        this.hasPets = hasPets;
    }

    public boolean isHasWifi() {
        return hasWifi;
    }

    public void setHasWifi(boolean hasWifi) {
        this.hasWifi = hasWifi;
    }

    public Integer getNumRooms() {
        return numRooms;
    }

    public void setNumRooms(Integer numRooms) {
        this.numRooms = numRooms;
    }

    public Integer getDurationOfStay() {
        return durationOfStay;
    }

    public void setDurationOfStay(Integer durationOfStay) {
        this.durationOfStay = durationOfStay;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createDate) {
        this.createdDate = createDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @JsonIdentityReference(alwaysAsId = true)
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @JsonIdentityReference(alwaysAsId = true)
    public User getReservant() {
        return reservant;
    }

    public void setReservant(User reservant) {
        this.reservant = reservant;
    }
}
