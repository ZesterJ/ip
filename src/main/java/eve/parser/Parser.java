package eve.parser;

import eve.commands.*;
import eve.exceptions.*;

public class Parser {

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
            if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
                throw new MissingDescriptionException("deadline");
            }
            return new DeadlineCommand(inputParts[1].split("/", 2)[0].trim(), inputParts[1].split("/", 2)[1].trim());

        case "event":
            if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
                throw new MissingDescriptionException("event");
            }
            return new EventCommand(inputParts[1].split("/", 2)[0].trim(), inputParts[1].split("/", 2)[1].trim(),
                    inputParts[1].split("/", 2)[2].trim());

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
