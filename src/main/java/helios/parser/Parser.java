package helios.parser;

import helios.command.*;
import helios.common.Messages;
import helios.exception.HeliosException;
import helios.task.Deadline;
import helios.task.Event;
import helios.task.Task;
import helios.task.Todo;

public class Parser {

    private static final String CMD_BYE = "bye";
    private static final String CMD_LIST = "list";
    private static final String CMD_MARK = "mark";
    private static final String CMD_UNMARK = "unmark";
    private static final String CMD_DELETE = "delete";

    private static final String TYPE_TODO = "todo";
    private static final String TYPE_EVENT = "event";
    private static final String TYPE_DEADLINE = "deadline";

    private static final String DELIMITER_BY = " /by ";
    private static final String DELIMITER_FROM = " /from ";
    private static final String DELIMITER_TO = " /to ";

    public static String[] parse(String input) throws HeliosException {
        String trimmed = input.trim(); // trim to avoid leading/trailing spaces

        if (trimmed.isEmpty()) {
            throw new HeliosException(Messages.MESSAGE_EMPTY_COMMAND);
        }

        return trimmed.split(" ");
    }

    public static Command parseCommand(String input) throws HeliosException {
        String[] parts = parse(input);
        String action = parts[0];

        switch (action) {
        case CMD_BYE:
            return new ExitCommand();
        case CMD_LIST:
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
        default:
            Task task = parseTask(input);
            return new AddCommand(task);
        }
    }

    private static int parseIndex(String str) throws HeliosException {
        try {
            return Integer.parseInt(str) - 1; // converting the index to 0-based
        } catch (NumberFormatException e) {
            throw new HeliosException(Messages.MESSAGE_TASK_NUMBER_NOT_INTEGER);
        }
    }

    private static void checkArgsLength(String[] parts, int expected) throws HeliosException {
        if (parts.length < expected) {
            throw new HeliosException(Messages.MESSAGE_MISSING_ARGUMENTS);
        }
    }

    public static Task parseTask(String input) throws HeliosException {
        String[] parts = input.split(" ", 2);
        if (parts.length < 2) {
            throw new HeliosException(Messages.MESSAGE_TASK_DESCRIPTION_EMPTY); // Invalid input being given
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
            throw new HeliosException(String.format(Messages.MESSAGE_UNKOWN_TASK_TYPE, type)); // Unknown task type
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

