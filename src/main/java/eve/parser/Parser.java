package eve.parser;

import eve.commands.*;
import eve.exceptions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("d-M-yyyy HHmm");

    public Command parse(String input) throws EveException {
        String[] inputParts = input.trim().split(" ", 2);
        String commandWord = inputParts[0].toLowerCase();

        switch (commandWord) {
        case "find":
            if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
                throw new EveException("Please specify a keyword to find. Example: find book");
            }
            return new FindCommand(inputParts[1].trim());
        case "todo":
            if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
                throw new MissingDescriptionException("todo");
            }
            return new TodoCommand(inputParts[1].trim());

        case "deadline":
            try {
                String[] dParts = inputParts[1].split("/", 2);
                LocalDateTime by = LocalDateTime.parse(dParts[1].trim(), DATE_FORMAT);
                return new DeadlineCommand(dParts[0].trim(), by);
            } catch (Exception e) {
                throw new EveException("Use format: deadline <desc>/d-M-yyyy HHmm");
            }

        case "event":
            try {
                String[] eParts = inputParts[1].split("/", 3);
                LocalDateTime from = LocalDateTime.parse(eParts[1].trim(), DATE_FORMAT);
                LocalDateTime to = LocalDateTime.parse(eParts[2].trim(), DATE_FORMAT);
                return new EventCommand(eParts[0].trim(), from, to);
            } catch (Exception e) {
                throw new EveException("Use format: event <desc>/start/end (d-M-yyyy HHmm)");
            }
        case "list":
            return new ListCommand();

        case "bye":
            return new ByeCommand();

        case "delete":
            if (inputParts.length < 2) {
                throw new EveException("Please specify the task number to delete.");
            }
            return new DeleteCommand(Integer.parseInt(inputParts[1].trim()));

        case "mark":
            return new MarkCommand(Integer.parseInt(inputParts[1].trim()));

        case "unmark":
            if (inputParts.length < 2) {
                throw new EveException("Please specify the task number to unmark.");
            }
            return new UnmarkCommand(Integer.parseInt(inputParts[1].trim()));

        default:
            throw new UnknownCommandException();
        }

    }
}
