package com.exalt.mycompany.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table(name = "user_profile")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @MapsId
    private User user;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String image;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "street1")),
            @AttributeOverride(name = "city", column = @Column(name = "city1")),
            @AttributeOverride(name = "state", column = @Column(name = "state1")),
            @AttributeOverride(name = "zipcode", column = @Column(name = "zipcode1")),
            @AttributeOverride(name = "country", column = @Column(name = "country1"))

    })
    private Address address1;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "street2")),
            @AttributeOverride(name = "city", column = @Column(name = "city2")),
            @AttributeOverride(name = "state", column = @Column(name = "state2")),
            @AttributeOverride(name = "zipcode", column = @Column(name = "zipcode2")),
            @AttributeOverride(name = "country", column = @Column(name = "country2"))

    })
    private Address address2;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonBackReference
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Address getAddress1() {
        return address1;
    }

    public void setAddress1(Address address1) {
        this.address1 = address1;
    }

    public Address getAddress2() {
        return address2;
    }

    public void setAddress2(Address address2) {
        this.address2 = address2;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "id=" + id +
                ", user=" + user +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", image='" + image + '\'' +
                ", address1=" + address1 +
                ", address2=" + address2 +
                '}';
    }
}


