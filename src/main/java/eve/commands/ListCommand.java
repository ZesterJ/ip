package eve.commands;

import eve.model.TaskList;
import eve.storage.Storage;
import eve.ui.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            ui.showMessage("Your list is currently empty.");
            return;
        }

        ui.showMessage("Here are the tasks in your list:");
        ui.showMessage(tasks.toString());
        ui.printLine();
    }
}
