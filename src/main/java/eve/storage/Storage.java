package eve.storage;

import eve.tasks.*;
import eve.model.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Handles loading and saving of tasks to a file.
 */

public class Storage {

    private final String filePath;
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("d-M-yyyy HHmm");

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the {@code TaskList} from the hard disk. * @return The TaskList
     * containing tasks from the storage file.
     */
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
                    LocalDateTime by = LocalDateTime.parse(p[3], DATE_FORMAT);
                    t = new Deadline(p[2], by);
                    break;
                case "E":
                    LocalDateTime from = LocalDateTime.parse(p[3], DATE_FORMAT);
                    LocalDateTime to = LocalDateTime.parse(p[4], DATE_FORMAT);
                    t = new Event(p[2], from, to);
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

    /**
     * Saves the given {@code TaskList} to the hard disk.
     * 
     * @param list The TaskList to be saved to the storage file.
     */
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
