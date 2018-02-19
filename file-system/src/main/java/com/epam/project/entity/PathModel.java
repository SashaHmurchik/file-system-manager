package com.epam.project.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.nio.file.Path;

/**
 * The Class PathModel contains the absolute path to file or directory and mark (file or directory).
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class PathModel {

    @JsonProperty(value = "path")
    private String path;

    @JsonProperty(value = "folder")
    private boolean folder;

}
