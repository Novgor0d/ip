package helios.common;

/**
 * Container class for user visible messages. (inspired from class structure of 'addressbook-level2')
 */
public class Messages {

    // General
    public static final String LINE_SEPARATOR = "_________________________________________";
    public static final String MESSAGE_WELCOME = "Hello!, I'm Helios\nWhat can I do for you?";
    public static final String MESSAGE_GOODBYE = "Bye. Hope to see you again soon!";
    public static final String MESSAGE_LOADING_ERROR = "Error loading data. Starting with empty task list";
    public static final String MESSAGE_INVALID_TASK_NUMBER = "Invalid task number: %d";
    public static final String MESSAGE_TASK_DESCRIPTION_EMPTY = "Task description cannot be empty";
    public static final String MESSAGE_SAVING_ERROR = "Error saving data";

    // Parser / input error
    public static final String MESSAGE_EMPTY_COMMAND = "Command cannot be empty";
    public static final String MESSAGE_TASK_NUMBER_NOT_INTEGER = "Task number must be a valid integer.";
    public static final String MESSAGE_MISSING_ARGUMENTS = "Missing argument(s) for command.";
    public static final String MESSAGE_UNKOWN_TASK_TYPE = "Unkown task type: %s";


    // AddCommand
    public static final String MESSAGE_TASK_ADDED = "Got it. I've added this task:\n %s\nNow you have %d tasks in the list.";

    // DeleteCommand
    public static final String MESSAGE_TASK_DELETED = "Noted. I've removed this task:\n%s\nNow you have %d tasks in the list.";

    // MarkCommand
    public static final String MESSAGE_TASK_MARKED = "Nice! I've marked this task as done:\n%s";

    // UnmarkCommand
    public static final String MESSAGE_TASK_UNMARKED = "OK, I've marked this task as not done yet:\n%s";
}
