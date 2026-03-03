package eve.commands;

import eve.model.TaskList;
import eve.storage.Storage;
import eve.tasks.Task;
import eve.tasks.ToDo;
import eve.ui.Ui;

/**
 * Command to add a todo task to the task list.
 */
public class TodoCommand extends Command {

    private final String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {

        Task task = new ToDo(description);
        taskList.add(task);

        storage.save(taskList);

        ui.printTaskAdded(task, taskList.size());
    }
}
