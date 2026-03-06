# Helios User Guide ☀️

**Helios** is a lightweight, text-based task management chatbot designed to help you stay organized through a simple command-line interface. Whether you have daily chores, looming deadlines, or upcoming events, Helios keeps track of them all.

---

## Quick Start
1. Ensure you have **Java 17** installed.
2. Download the latest `helios.jar` from the [releases page](https://github.com/Novgor0d/ip/releases).
3. Open your terminal, navigate to the folder, and run:
 ```
java -jar helios.jar
 ```
4. Type your commands in the prompt `> ` and press **Enter**.

---

## Command Format Notes
* **UPPER_CASE**: Parameters to be supplied by the user (e.g., `todo DESCRIPTION`).
* **Square Brackets `[]`**: Optional items (e.g., `[TIME]`).
* **Task Numbers**: Refers to the index shown in the `list` command.
* **Case Sensitivity**: Commands are case-sensitive.

---

## Features

### 1. Adding a Todo: `todo`
Adds a simple task without any specific date.
* **Format:** `todo DESCRIPTION`
* **Example:** `todo Read Chapter 3`
* **Output:** 
```
> Got it. I've added this task:
> [T][ ] Read Chapter 3  
> Now you have 1 task in the list.
```
### 2. Adding a Deadline: `deadline`
Adds a task that needs to be done before a specific date/time.
* **Format:** `deadline DESCRIPTION /by YYYY-MM-DD [HHmm]`
* **Example:** `deadline Submit assignment /by 2026-04-10 1800`

### 3. Adding an Event: `event`
Adds a task that occurs within a specific time range.
* **Format:** `event DESCRIPTION /from START /to END`
* **Example:** `event Project meeting /from Monday 2pm /to 4pm`

### 4. Listing All Tasks: `list`
Displays all tasks currently saved in your list.
* **Format:** `list`
* **Example Output:**
```
> Here are the tasks in your list:  
> 1.[T][ ] Read Chapter 3  
> 2.[D][ ] Submit assignment (by: Apr 10 2026 18:00)
```
### 5. Listing Tasks on a Specific Date: `listOnDate`
Filters and displays only the deadlines occurring on a specific date.
* **Format:** `listOnDate YYYY-MM-DD`
* **Example:** `listOnDate 2026-04-10`
* **Output:**
```
> Tasks on 2026-04-10:
> 1. [D][ ] Submit assignment (by: Apr 10 2026 18:00)
```
### 6. Marking a Task: `mark`
Marks the task at the specified index as completed.
* **Format:** `mark TASK_NUMBER`
* **Example:** `mark 1`
* **Output:** 
```
> Nice! I've marked this task as done:
> [T][X] Read Chapter 3
```

### 7. Unmarking a Task: `unmark`
Reverts a completed task back to "not done".
* **Format:** `unmark TASK_NUMBER`

### 8. Deleting a Task: `delete`
Removes a task from the list permanently.
* **Format:** `delete TASK_NUMBER`
* **Example:** `delete 2`

### 9. Finding Tasks: `find`
Searches for tasks containing a keyword in their description (case-insensitive).
* **Format:** `find KEYWORD`
* **Example:** `find assignment`

### 10. Exiting Helios: `bye`
Closes the chatbot and saves all data.
* **Format:** `bye`

---

## Data Management
Helios automatically saves and loads your tasks from a local file.
* **File Path:** `./data/helios.txt`

> [!IMPORTANT]
> **Manual Editing:** Do not manually edit the `helios.txt` file. If the file format is corrupted, Helios may skip corrupted lines or fail to load.

---

## FAQ
**Q: Can I use different date formats?** A: For deadlines and date filtering, Helios strictly requires `YYYY-MM-DD` for dates and `HHmm` (24-hour format) for time to ensure proper sorting and storage.

**Q: Where is my data saved?** A: Your data is saved in a folder named `data` in the same directory where you run the application.