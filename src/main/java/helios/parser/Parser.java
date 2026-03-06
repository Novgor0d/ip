package helios.parser;

import java.time.LocalDate;
import helios.command.*;
import helios.common.Messages;
import helios.exception.HeliosException;
import helios.task.Deadline;
import helios.task.Event;
import helios.task.Task;
import helios.task.Todo;

/**
 * Parses user input strings into executable Command objects or Task objects.
 * This class handles the logic of validating arguments and identifying command types.
 *
 */
public class Parser {

    private static final String CMD_BYE = "bye";
    private static final String CMD_LIST = "list";
    private static final String CMD_MARK = "mark";
    private static final String CMD_UNMARK = "unmark";
    private static final String CMD_DELETE = "delete";
    private static final String CMD_LIST_ON_DATE = "listOnDate";
    private static final String CMD_FIND = "find";
    private static final String TYPE_TODO = "todo";
    private static final String TYPE_EVENT = "event";
    private static final String TYPE_DEADLINE = "deadline";

    private static final String DELIMITER_BY = " /by ";
    private static final String DELIMITER_FROM = " /from ";
    private static final String DELIMITER_TO = " /to ";

    /**
     * Splits the raw input into an array of words, trimmed of leading/trailing spaces.
     *
     * @param input            The raw string from user input.
     * @return                 An array of strings split by spaces.
     * @throws HeliosException If the input is empty or blank.
     */
    public static String[] parse(String input) throws HeliosException {
        String trimmed = input.trim(); // trim to avoid leading/trailing spaces

        if (trimmed.isEmpty()) {
            throw new HeliosException(Messages.MESSAGE_EMPTY_COMMAND);
        }

        return trimmed.split(" ");
    }

    /**
     * Interprets user input and returns the corresponding Command object.
     * @param input            The full user input string.
     * @return                 A Command subclass for execution.
     * @throws HeliosException If arguments are missing, invalid, or the command is unknown.
     */
    public static Command parseCommand(String input) throws HeliosException {
        String[] parts = parse(input);
        String action = parts[0];

        switch (action) {
        case CMD_BYE:
            return new ExitCommand();
        case CMD_LIST:
            checkArgsLength(parts, 1);
            return new ListCommand();
        case CMD_MARK:
            checkArgsLength(parts, 2);
            int markIndex = parseIndex(parts[1]);
            return new MarkCommand(markIndex);
        case CMD_UNMARK:
            checkArgsLength(parts, 2);
            int unmarkIndex = parseIndex(parts[1]);
            return new UnmarkCommand(unmarkIndex);
        case CMD_DELETE:
            checkArgsLength(parts, 2);
            int deleteIndex = parseIndex(parts[1]);
            return new DeleteCommand(deleteIndex);
        case CMD_LIST_ON_DATE:
            checkArgsLength(parts, 2);
            LocalDate queryDate;
            try {
                queryDate = LocalDate.parse(parts[1].trim());
            } catch (Exception e) {
                throw new HeliosException("Invalid date format. Use yyyy-MM-dd (ensure date and year are correct");
            }
            return new ListOnDateCommand(queryDate);
        case CMD_FIND:
            checkArgsLength(parts, 2);
            String keyword = input.substring(5); // removing "find" from query
            return new FindCommand(keyword);
        default:
            Task task = parseTask(input);
            return new AddCommand(task);
        }
    }

    /**
     * Converts a string representation of a task index into a zero-based integer.
     *
     * @param str              The string containing the number (e.g., "1").
     * @return                 The zero-based index
     * @throws HeliosException If the string is not a valid integer.
     */
    private static int parseIndex(String str) throws HeliosException {
        try {
            return Integer.parseInt(str) - 1; // converting the index to 0-based
        } catch (NumberFormatException e) {
            throw new HeliosException(Messages.MESSAGE_TASK_NUMBER_NOT_INTEGER);
        }
    }

    /**
     * erifies that the input parts array has at least the expected number of elements.
     *
     * @param parts            The array of input words.
     * @param expected         The minimum required length.
     * @throws HeliosException If the array is shorter than expected.
     */
    private static void checkArgsLength(String[] parts, int expected) throws HeliosException {
        if (parts.length != expected) {
            throw new HeliosException(Messages.MESSAGE_IMPROPER_ARGUMENTS);
        }
    }

    /**
     * Parses input to identify and create the appropriate Task object (Todo, Event, or Deadline).
     *
     * @param input            The full user input string.
     * @return                 A Task object.
     * @throws HeliosException If the task type is unknown or the description is missing.
     */
    public static Task parseTask(String input) throws HeliosException {
        String[] parts = input.split(" ", 2);
        if (parts.length < 2) {
            throw new HeliosException(Messages.MESSAGE_TASK_DESCRIPTION_EMPTY); // Invalid input being given
        }

        String type = parts[0];
        String description = (parts.length > 1) ? parts[1] : "";

        switch (type) {
        case TYPE_TODO:
            return new Todo(description);
        case TYPE_DEADLINE:
            return createDeadlineTask(description);
        case TYPE_EVENT:
            return createEventTask(description);
        default:
            throw new HeliosException(String.format(Messages.MESSAGE_UNKNOWN_TASK_TYPE, type)); // Unknown task type
        }
    }

    /**
     * Helper method to handle the specific string splitting required for a Deadline task.
     *
     * @param description      The part of the input after the 'deadline' command.
     * @return                 A new Deadline task.
     * @throws HeliosException If the /by delimiter is missing.
     */
    private static Task createDeadlineTask(String description) throws HeliosException {
        String[] dlParts = description.split(DELIMITER_BY, 2);
        if (dlParts.length < 2) {
            throw new HeliosException(" Deadline task must be in the format: deadline <desc> /by <date>");
        }
        return new Deadline(dlParts[0].trim(), dlParts[1].trim());
    }

    /**
     * Helper method to handle the complex splitting required for an Event task.
     *
     * @param description      The part of the input after the 'event' command.
     * @return                 A new Event task.
     * @throws HeliosException If /from or /to delimiters are missing.
     */
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

