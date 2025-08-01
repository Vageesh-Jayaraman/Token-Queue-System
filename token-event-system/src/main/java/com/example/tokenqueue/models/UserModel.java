package com.example.tokenqueue.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    private String username;
    private String password;
    private String email;

    @ManyToOne
    @JoinColumn(name = "role_id")  
    private RoleModel role;

    @ManyToOne(optional = true)  
    private DepartmentModel department;
}
