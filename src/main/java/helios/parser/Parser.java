package helios.parser;

import helios.exception.HeliosException;

public class Parser {

    public static String[] parse(String input) throws HeliosException {
        String trimmed = input.trim(); // trim to avoid leading/trailing spaces

        if (trimmed.isEmpty()) {
            throw new HeliosException("Command cannot be empty");
        }

        return trimmed.split(" ");
    }
}

