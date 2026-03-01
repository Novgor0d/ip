package helios.parser;

import helios.exception.HeliosException;
import helios.task.Deadline;
import helios.task.Event;
import helios.task.Task;
import helios.task.Todo;

public class Parser {

    private static final String TYPE_TODO = "todo";
    private static final String TYPE_EVENT = "event";
    private static final String TYPE_DEADLINE = "deadline";
    private static final String DELIMITER_BY = " /by ";
    private static final String DELIMITER_FROM = " /from ";
    private static final String DELIMITER_TO = " /to ";

    public static String[] parse(String input) throws HeliosException {
        String trimmed = input.trim(); // trim to avoid leading/trailing spaces

        if (trimmed.isEmpty()) {
            throw new HeliosException("Command cannot be empty");
        }

        return trimmed.split(" ");
    }

    public static Task parseTask(String input) throws HeliosException {
        String[] parts = input.split(" ", 2);
        if (parts.length < 2) {
            throw new HeliosException("Task description cannot be empty"); // Invalid input being given
        }

        String type = parts[0];
        String description = parts[1];

        switch (type) {
        case TYPE_TODO:
            return new Todo(description);
        case TYPE_DEADLINE:
            return createDeadlineTask(description);
        case TYPE_EVENT:
            return createEventTask(description);
        default:
            throw new HeliosException("Unknown task type: " + type); // Unknown task type
        }
    }

    private static Task createDeadlineTask(String description) throws HeliosException {
        String[] dlParts = description.split(DELIMITER_BY, 2);
        if (dlParts.length < 2) {
            throw new HeliosException("Deadline task must be in the format: deadline <desc> /by <date>");
        }
        return new Deadline(dlParts[0], dlParts[1]);
    }

    private static Task createEventTask(String description) throws HeliosException {
        String[] fromParts = description.split(DELIMITER_FROM, 2);
        if (fromParts.length < 2) {
            throw new HeliosException("Event task must include /from <start>");
        }

        String[] toParts = fromParts[1].split(DELIMITER_TO, 2);
        if (toParts.length < 2) {
            throw new HeliosException("Event task must include /to <end>");
        }

        return new Event(fromParts[0], toParts[0], toParts[1]);
    }
}

