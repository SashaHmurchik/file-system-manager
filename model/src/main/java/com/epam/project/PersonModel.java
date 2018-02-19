package com.epam.project;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class PersonModel implements Serializable{

    @JsonProperty(value = "id")
    @NotNull
    private Long id;

    @JsonProperty(value = "email")
    @NotNull
    @Pattern(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$", message = "Not valid email address")
    private String email;

    @JsonProperty(value = "role")
    @NotNull
    private RoleModel userRole;
}
