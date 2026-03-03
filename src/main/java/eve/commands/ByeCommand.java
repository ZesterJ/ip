package eve.commands;

import eve.model.TaskList;
import eve.storage.Storage;
import eve.ui.Ui;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printGoodbyeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
