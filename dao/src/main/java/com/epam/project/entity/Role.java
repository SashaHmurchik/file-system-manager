package com.epam.project.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@ToString(exclude = {"persons"})
@EqualsAndHashCode(exclude = {"persons"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role")
public class Role {

    @Id
    @Column(name = "role_id",
            nullable = false,
            columnDefinition = "bigserial")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "role_name",
            nullable = false,
            length = 36)
    private String roleName;

    @OneToMany(mappedBy = "role",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @Fetch(value = FetchMode.JOIN)
    private Set<Person> persons;
}
