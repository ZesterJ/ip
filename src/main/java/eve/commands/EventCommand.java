package eve.commands;

import eve.model.TaskList;
import eve.storage.Storage;
import eve.tasks.Event;
import eve.tasks.Task;
import eve.ui.Ui;
import java.time.LocalDateTime;

/**
 * Command to add an event task to the task list.
 */
public class EventCommand extends Command {

    private final String description;
    private final LocalDateTime from;
    private final LocalDateTime to;

    public EventCommand(String description, LocalDateTime from, LocalDateTime to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {

        Task task = new Event(description, from, to);
        taskList.add(task);

        storage.save(taskList);

        ui.printTaskAdded(task, taskList.size());
    }
}
