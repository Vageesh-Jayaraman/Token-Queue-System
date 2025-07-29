package com.example.tokenqueue.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TokenModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private DepartmentModel department;

    private Long counter;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

    private String status = "PENDING";
}
