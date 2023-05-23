package ru.clevertec.checkservlets.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;

@UtilityClass
public class ServletUtil {

    /**
     * Reads all content in request body.
     *
     * @return content of request body
     */
    public static String readRequestBody(HttpServletRequest request) throws IOException {
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while (Objects.nonNull(line = reader.readLine())) {
            buffer.append(line);
        }
        return buffer.toString();
    }

    /**
     * Retrieves last block in uri and trys to parse it as integer.
     *
     * @return last number in uri or null if it's not a number
     */
    public static Integer retrieveIdFromUri(String uri) {
        String[] pathParts = uri.split("/");
        try {
            return Integer.valueOf(pathParts[pathParts.length - 1]);
        } catch (NumberFormatException ex) {
            return null;
        }
    }
}
