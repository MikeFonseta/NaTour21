package com.example.NaTour21.User.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "\"user\"",uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class User {

    private String email;
    @Id
    private String username;
    private String password;
    private String auth;
    private String role;
    private String facebookId;

}
