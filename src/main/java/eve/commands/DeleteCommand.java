package eve.commands;

import eve.model.TaskList;
import eve.storage.Storage;
import eve.tasks.Task;
import eve.ui.Ui;
import eve.exceptions.EveException;

public class DeleteCommand extends Command {

    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws EveException {

        if (index < 0 || index >= taskList.size()) {
            throw new EveException("The task number you provided is out of range.");
        }

        Task removed = taskList.delete(index);

        storage.save(taskList);

        ui.printTaskDeleted(removed, taskList.size());
    }
}
