package com.epam.project.web.converter;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Aliaksandr_Khmurchyk on 15-Feb-18.
 */
public class UriConverter {

    private UriConverter() {}

    public static Path convertUriToPath(String uri, String notNeedPrefix) {
        return Paths.get(uri.substring(notNeedPrefix.length()));
    }

}
