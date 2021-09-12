package demo.leabackend.models.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@Table(name = "user")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String details;
    private String location;

    @OneToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.REMOVE, orphanRemoval = true,
            mappedBy = "owner")
    private Home home;

    @OneToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.REMOVE, orphanRemoval = true,
            mappedBy = "reservant")
    private Home reservedHome;

    @OneToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.REMOVE, orphanRemoval = true,
            mappedBy = "user")
    private Spoof spoof;

    public User() {
    }

    public User(String firstName, String lastName, String email, String phoneNumber, String details, String location, Home home, Home reservedHome) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.details = details;
        this.location = location;
        this.home = home;
        this.reservedHome = reservedHome;
    }

    public User(String firstName, String lastName, String email, String phoneNumber, String details, String location) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.details = details;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }

    public Home getReservedHome() {
        return reservedHome;
    }

    public void setReservedHome(Home reservedHome) {
        this.reservedHome = reservedHome;
    }

    public Spoof getSpoof() {
        return spoof;
    }

    public void setSpoof(Spoof spoof) {
        this.spoof = spoof;
    }
}
