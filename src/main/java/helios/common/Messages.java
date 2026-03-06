package helios.common;

/**
 * Container class used to stores user-visible strings and message templates used throughout the application.
 * (inspired from class structure of 'addressbook-level2')
 */
public class Messages {

    // Horizontal line used to visually separate UI components.
    public static final String LINE_SEPARATOR = "_________________________________________";

    // General Messages
    public static final String MESSAGE_WELCOME = "Hello!, I'm Helios\nWhat can I do for you?";
    public static final String MESSAGE_GOODBYE = "Bye. Hope to see you again soon!";
    public static final String MESSAGE_LOADING_ERROR = "Error loading data. Starting with empty task list";
    public static final String MESSAGE_INVALID_TASK_NUMBER = "Invalid task number: %d";
    public static final String MESSAGE_TASK_DESCRIPTION_EMPTY = "Task description cannot be empty";
    public static final String MESSAGE_SAVING_ERROR = "Error saving data";

    // Parser and Input Error Messages
    public static final String MESSAGE_EMPTY_COMMAND = "Command cannot be empty";
    public static final String MESSAGE_TASK_NUMBER_NOT_INTEGER = "Task number must be a valid integer.";
    public static final String MESSAGE_MISSING_ARGUMENTS = "Missing argument(s) for command.";
    public static final String MESSAGE_UNKNOWN_TASK_TYPE = "Unknown task type: %s";


    // Command Confirmation Messages
    public static final String MESSAGE_TASK_ADDED = "Got it. I've added this task:\n %s\nNow you have %d tasks in the list.";
    public static final String MESSAGE_TASK_DELETED = "Noted. I've removed this task:\n%s\nNow you have %d tasks in the list.";
    public static final String MESSAGE_TASK_MARKED = "Nice! I've marked this task as done:\n%s";
    public static final String MESSAGE_TASK_UNMARKED = "OK, I've marked this task as not done yet:\n%s";
}
