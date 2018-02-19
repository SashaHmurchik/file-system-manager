package com.epam.project.entity;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "person")
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id",
            nullable = false,
            columnDefinition = "bigserial")
    private Long id;

    @Column(name = "user_eMail",
            unique = true,
            nullable = false,
            length = 50)
    private String email;

    @Column(name = "user_password",
            nullable = false,
            length = 70)
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id",
            nullable = false)
    private Role role;
}
