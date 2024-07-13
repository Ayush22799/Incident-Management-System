package com.assesment.Incident_Management.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user_detail")
public class UserDetails {
    @Id
    @Column(name = "username")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String userEmailId;
    @Column(name = "phone")
    private long phoneNumber;
    @Column(name = "pincode")
    private int pincode;
    @Column(name = "city")
    private String city;
    @Column(name = "country")
    private String country;
}
