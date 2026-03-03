package eve.commands;

import eve.model.TaskList;
import eve.storage.Storage;
import eve.tasks.Deadline;
import eve.tasks.Task;
import eve.ui.Ui;

public class DeadlineCommand extends Command {

    private final String description;
    private final String by;

    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {

        Task task = new Deadline(description, by);
        taskList.add(task);

        storage.save(taskList);

        ui.printTaskAdded(task, taskList.size());
    }
}
