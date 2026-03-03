package eve.storage;

import eve.tasks.*;
import eve.model.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public TaskList load() {
        TaskList list = new TaskList();
        File file = new File(filePath);

        if (!file.exists()) {
            return list;
        }

        try (Scanner s = new Scanner(file)) {
            while (s.hasNext()) {
                String[] p = s.nextLine().split(" \\| ");
                Task t = null;
                switch (p[0]) {
                    case "T":
                        t = new ToDo(p[2]);
                        break;
                    case "D":
                        t = new Deadline(p[2], p[3]);
                        break;
                    case "E":
                        t = new Event(p[2], p[3], p[4]);
                        break;
                }
                if (t != null && p[1].equals("1")) {
                    t.markAsDone();
                }
                list.add(t);
            }
        } catch (Exception e) {
        }

        return list;
    }

    public void save(TaskList list) {
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            FileWriter fw = new FileWriter(file);
            for (Task t : list.getAllTasks()) {
                fw.write(t.toFileFormat() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
        }
    }
}
