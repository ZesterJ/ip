package eve.commands;

import eve.model.TaskList;
import eve.storage.Storage;
import eve.ui.Ui;
import eve.exceptions.EveException;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws EveException;

    public boolean isExit() {
        return false;
    }
}
