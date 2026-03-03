package eve.commands;

import eve.model.TaskList;
import eve.storage.Storage;
import eve.tasks.Deadline;
import eve.tasks.Task;
import eve.ui.Ui;
import java.time.LocalDateTime;

public class DeadlineCommand extends Command {

    private final String description;
    private final LocalDateTime by;

    public DeadlineCommand(String description, LocalDateTime by) {
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
