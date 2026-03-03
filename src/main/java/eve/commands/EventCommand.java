package eve.commands;

import eve.model.TaskList;
import eve.storage.Storage;
import eve.tasks.Event;
import eve.tasks.Task;
import eve.ui.Ui;

public class EventCommand extends Command {

    private final String description;
    private final String from;
    private final String to;

    public EventCommand(String description, String from, String to) {
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
