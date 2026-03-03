package eve;

import eve.ui.Ui;
import eve.model.TaskList;
import eve.storage.Storage;
import eve.parser.Parser;
import eve.commands.Command;

import java.util.Scanner;

import eve.exceptions.EveException;

public class Eve {
    private static final String FILE_PATH = "./data.eve.txt";
    private static final Ui ui = new Ui();
    private static final Parser parser = new Parser();

    public static void main(String[] args) {

        ui.printWelcomeMessage();

        Storage storage = new Storage(FILE_PATH);
        TaskList taskList = storage.load();

        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                String input = sc.nextLine();
                Command command = parser.parse(input);
                command.execute(taskList, ui, storage);

                if (command.isExit()) {
                    sc.close();
                    break;
                }
            } catch (EveException e) {
                ui.showError(e.getMessage());
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showError("It seems like some information is missing. Please check your command and try again.");
            } catch (NumberFormatException e) {
                ui.showError("EVE ERROR: Please enter a valid task number.");
            }
        }
    }
}
