package eve.commands;

import eve.exceptions.EveException;
import eve.model.TaskList;
import eve.storage.Storage;
import eve.tasks.Task;
import eve.ui.Ui;

/**
 * Command to unmark a task as not done.
 */
public class UnmarkCommand extends Command {

    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws EveException {

        if (index < 0 || index >= taskList.size()) {
            throw new EveException("The task number you provided is out of range.");
        }

        Task task = taskList.get(index);
        task.markAsNotDone();

        storage.save(taskList);

        ui.printTaskUnmarked(task);
    }
}
