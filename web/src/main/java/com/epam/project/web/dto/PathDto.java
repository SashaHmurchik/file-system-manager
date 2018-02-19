package com.epam.project.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;


@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class PathDto {

    @JsonProperty(value = "path")
    @NotNull
    private String path;

    @JsonProperty(value = "name")
    @NotNull
    private String name;

}
