package helios.storage;

import helios.exception.HeliosException;
import helios.task.*;

import java.io.*;
import java.nio.file.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles reading from and writing tasks to a local file.
 * This class ensures that the task list persists between sessions.
 */
public class Storage {

    private final Path filePath;

    /**
     * Constructs a Storage object with a specified file path.
     *
     * @param filePath The path to the data file.
     */
    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    /**
     * Loads the task list from the file specified in the constructor.
     * If the file does not exist, an empty list is returned.
     *
     * @return             A list of Task objects loaded from the file.
     * @throws IOException If there is an error reading from the file system.
     */
    public List<Task> load() throws IOException {
        List<Task> tasks = new ArrayList<>();

        if (!Files.exists(filePath)) {
            return tasks;
        }

        List<String> lines = Files.readAllLines(filePath);
        for (String line : lines) {
            try {
                tasks.add(parseLine(line));
            } catch (HeliosException e) {
                System.out.println("Skipping corrupted line: " + line);
            }
        }

        return tasks;
    }

    /**
     * Saves the provided list of tasks to the file.
     * Creates the parent directory if it does not already exist.
     *
     * @param tasks        The list of tasks to be written to disk.
     * @throws IOException If there is an error writing to the file system.
     */
    public void save(List<Task> tasks) throws IOException {

        // Ensuring the directory exists
        Files.createDirectories(filePath.getParent());

        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            for (Task task : tasks) {
                writer.write(serialize(task));
                writer.newLine();
            }
        }
    }

    /**
     * arses a single line of data from the file into a Task object.
     *
     * @param line             The pipe-separated string from the storage file.
     * @return                 The corresponding Task (Todo, Deadline, or Event)
     * @throws HeliosException If the task type is unknown or the format is invalid.
     */
    private Task parseLine(String line) throws HeliosException {
        String[] parts = line.split(" \\| ");

        String type = parts[0];
        boolean isDone = parts[1].equals("1");

        switch (type) {
        case "T":
            Todo todo = new Todo(parts[2]);
            if (isDone) {
                todo.markDone();
            }
            return todo;

        case "D":
            Deadline deadline = new Deadline(parts[2], parts[3]);
            if (isDone) {
                deadline.markDone();
            }
            return deadline;

        case "E":
            Event event = new Event(parts[2], parts[3], parts[4]);
            if (isDone) {
                event.markDone();
            }
            return event;

        default:
            throw new HeliosException("Unknown task type");
        }
    }

    /**
     * Converts a Task object into a formatted string for file storage.
     *
     * @param task The task to be serialized.
     * @return     A pipe-separated string representation of the task.
     */
    private String serialize(Task task) {
        String status = task.isDone() ? "1" :"0";

        if (task instanceof Todo) {
            return "T | " + status + " | " + task.getDescription();
        }

        if (task instanceof Deadline) {
            Deadline d = (Deadline) task;
            return "D | " + status + " | " + d.getDescription() + " | " + d.getBy().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        }

        if (task instanceof Event) {
            Event e = (Event) task;
            return "E | " + status + " | " + e.getDescription() + " | " + e.getFrom() + " | " + e.getTo();
        }

        return "";
    }
}
