package co.id.model;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "fristname")
    private String fristname;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "city")
    private String city;
    @Column(name = "dob")
    private Date dob;
    @Column(name = "address")
    private String address;
    @Column(name = "gender")
    private String gender;
    @Column(name = "hobby")
    private String hobby;


    public Customer() {
    }

    public Customer(long id, String fristname, String lastname, String city, Date dob, String address, String gender, String hobby) {
        this.id = id;
        this.fristname = fristname;
        this.lastname = lastname;
        this.city = city;
        this.dob = dob;
        this.address = address;
        this.gender = gender;
        this.hobby = hobby;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFristname() {
        return fristname;
    }

    public void setFristname(String fristname) {
        this.fristname = fristname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
}