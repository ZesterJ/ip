package eve.commands;

import eve.exceptions.EveException;
import eve.model.TaskList;
import eve.storage.Storage;
import eve.tasks.Task;
import eve.ui.Ui;
import java.util.ArrayList;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws EveException {
        ArrayList<Task> matchingTasks = taskList.findTasks(keyword);

        ui.showFindResults(matchingTasks);
    }

}
