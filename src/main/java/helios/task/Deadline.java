package helios.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import helios.exception.HeliosException;

public class Deadline extends Task {

        protected LocalDateTime by;
        private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        private static final DateTimeFormatter ALT_INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");


    /**
     * Constructs a Deadline task.
     * @param description The description of the task.
     * @param by The deadline date/time.
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

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(OUTPUT_FORMAT) + ")";
    }

    public LocalDateTime getBy() {
        return by;
    }
}
