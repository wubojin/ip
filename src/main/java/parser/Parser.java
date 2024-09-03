package parser;

import exception.JadeException;
import task.*;
import ui.Ui;

import java.time.format.DateTimeParseException;

/**
 * Handles the parsing of user commands and executes the appropriate actions.
 */
public class Parser {

    public Task parseTaskCommand(String command) throws JadeException {
        if (command.startsWith("todo")) {
            if (command.substring(4).trim().isEmpty()) {
                throw new JadeException("The todo task cannot be empty!");
            }
            return new Todo(command.substring(5));
        } else if (command.startsWith("deadline")) {
            if (command.substring(8).trim().isEmpty()) {
                throw new JadeException("The deadline task cannot be empty!");
            }
            return parseDeadline(command);
        } else if (command.startsWith("event")) {
            if (command.substring(5).trim().isEmpty()) {
                throw new JadeException("The event task cannot be empty!");
            }
            return parseEvent(command);
        }
        return null;
    }

    private Task parseDeadline(String command) throws JadeException {
        String[] parts = command.substring(9).split(" /by ", 2);
        if (parts.length < 2) {
            throw new JadeException("Please provide a deadline in the format:\n"
                    + Ui.INDENT + "  deadline <task> /by <time>");
        } else {
            try {
                return new Deadline(parts[0], parts[1]);
            } catch (DateTimeParseException e) {
                throw new JadeException("Please use yyyy-MM-dd HHmm format for time.\n"
                        + Ui.INDENT + "  eg. 2024-12-25 2130");
            }
        }
    }

    private Task parseEvent(String command) throws JadeException {
        String[] parts = command.substring(6).split(" /from ", 2);
        if (parts.length < 2) {
            throw new JadeException("Please provide an event in the format:\n"
                    + Ui.INDENT + "  event <task> /from <time>");
        } else {
            String[] timeParts = parts[1].split(" /to ", 2);
            if (timeParts.length < 2) {
                throw new JadeException("Please provide an end time in the format:\n"
                        + Ui.INDENT + "  event <task> /from <start time> /to <end time>");
            } else {
                try {
                    return new Event(parts[0], timeParts[0], timeParts[1]);
                } catch (DateTimeParseException e) {
                    throw new JadeException("Please use yyyy-MM-dd HHmm format for time.\n"
                            + Ui.INDENT + "  eg. 2024-12-25 2130");
                }
            }
        }
    }
}