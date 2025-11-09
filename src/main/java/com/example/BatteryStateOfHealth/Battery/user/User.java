package com.example.BatteryStateOfHealth.Battery.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password;

    String firstName;
    String lastName;
    String mobileNumber;
    String email;
    int zipcodeId;
}
