package com.epam.project;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class RoleModel {

    @JsonProperty(value = "role_id")
    @NotNull
    private Long id;

    @JsonProperty(value = "role_name")
    @NotNull
    @Pattern(regexp = "ADMIN|USER", message = "Not valid role name")
    private String roleName;

}
