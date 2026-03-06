package helios.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import helios.exception.HeliosException;

/**
 * Represents a task with a deadline.
 * This class handles parsing of date strings into LocalDateTime objects and
 * supports multiple input formats (date-only or date-time).
 */
public class Deadline extends Task {

        protected LocalDateTime by;
        private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        private static final DateTimeFormatter ALT_INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");


    /**
     * Constructs a Deadline task.
     * Supports formats "yyyy-MM-dd" and "yyyy-MM-dd HHmm".
     * @param description The description of the task.
     * @param by The deadline date/time.
     * @throws HeliosException If the date/time format is invalid or the date does not exist.
     */
    public Deadline(String description, String by) throws HeliosException {
        super(description);
        try {
            if (by.trim().length() <= 10) { // only date part provided
                LocalDate date = LocalDate.parse(by, ALT_INPUT_FORMAT);
                this.by = date.atStartOfDay(); // convert to LocalDatTime
            } else {
                // parse date + time
                this.by = LocalDateTime.parse(by, INPUT_FORMAT);
            }
        } catch (Exception e) {
            throw new HeliosException("""
                    Deadline task must be in the format: deadline <desc> /by <date> [HHmm]
                    Example (date only): deadline submit report /by 2026-03-06
                    Example (date + time): deadline submit report /by 2026-03-06 1800
                    Make sure the date is valid, e.g., month 1-12 and correct days per month."""
            );
        }
    }

    /**
     * Returns the string representation of the Deadline task,
     * including the formatted date/time.
     * @return Formatted string including type, status, description, and deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(OUTPUT_FORMAT) + ")";
    }

    public LocalDateTime getBy() {
        return by;
    }
}
