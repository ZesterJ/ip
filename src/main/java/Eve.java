import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

import exceptions.EveException;
import exceptions.MissingDescriptionException;
import exceptions.UnknownCommandException;

public class Eve {
    private static final String FILE_PATH = "./data.eve.txt";

    public static void main(String[] args) {

        String logo = " _____             _____ \n"
                + "| ____|  \\     /  | ____|\n"
                + "|  _|     \\   /   |  _|  \n"
                + "| |___     \\ /    | |___ \n"
                + "|_____|     V     |_____|\n";

        System.out.println("Hello! I am\n" + logo);
        System.out.println("How may I be of assistance today?");
        System.out.println("__________________________________\n");

        Task[] tasks = new Task[100];
        int taskCount = loadTasks(tasks);
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                String userInput = sc.nextLine().trim();
                String[] inputParts = userInput.split(" ", 2);
                String command = inputParts[0].toLowerCase();
                System.out.println("__________________________________\n");

                switch (command) {
                    case "bye":
                        System.out.println("Goodbye. Have a nice day!");
                        System.out.println("__________________________________\n");
                        sc.close();
                        return;

                    case "list":
                        if (taskCount == 0) {
                            System.out.println("Your Task List is empty.");
                            break;
                        } else {
                            System.out.println("Here are the tasks in your list:");
                            for (int i = 0; i < taskCount; i += 1) {
                                System.out.println((i + 1) + ". " + tasks[i].toString());
                            }
                            System.out.println("__________________________________\n");
                        }
                        break;

                    case "mark":
                        if (inputParts.length < 2) {
                            throw new EveException("Please specify which task number to mark.");
                        }

                        int index = Integer.parseInt(inputParts[1]) - 1;
                        if (index < 0 || index >= taskCount) {
                            throw new EveException("The task number you provided is out of range.");
                        }
                        tasks[index].markAsDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(tasks[index].toString());
                        System.out.println("__________________________________\n");
                        saveTasks(tasks, taskCount);
                        break;

                    case "unmark":
                        if (inputParts.length < 2) {
                            throw new EveException("Please specify which task number to unmark.");
                        }
                        int unmarkIndex = Integer.parseInt(inputParts[1]) - 1;
                        if (unmarkIndex < 0 || unmarkIndex >= taskCount) {
                            throw new EveException("The task number you provided is out of range.");
                        }
                        tasks[unmarkIndex].markAsNotDone();
                        System.out.println("The following task will be marked as undone:");
                        System.out.println(tasks[unmarkIndex].toString());
                        System.out.println("__________________________________\n");
                        saveTasks(tasks, taskCount);
                        break;

                    case "todo":
                        if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
                            throw new MissingDescriptionException("todo");
                        }

                        String todoDescription = inputParts[1];
                        tasks[taskCount] = new ToDo(todoDescription);
                        System.out.println("Got it. I have added this task:");
                        System.out.println(tasks[taskCount].toString());
                        System.out.println("__________________________________\n");
                        taskCount++;
                        saveTasks(tasks, taskCount);
                        break;

                    case "deadline":
                        if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
                            throw new MissingDescriptionException("deadline");
                        }

                        String[] deadlinesParam = inputParts[1].split("/", 2);
                        tasks[taskCount] = new Deadline(deadlinesParam[0], deadlinesParam[1]);
                        System.out.println("Got it. I have added this task:");
                        System.out.println(tasks[taskCount].toString());
                        System.out.println("__________________________________\n");
                        taskCount++;
                        saveTasks(tasks, taskCount);
                        break;

                    case "event":
                        if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
                            throw new MissingDescriptionException("event");
                        }

                        String[] eventsParam = inputParts[1].split("/", 3);
                        tasks[taskCount] = new Event(eventsParam[0], eventsParam[1], eventsParam[2]);
                        System.out.println("Got it. I have added this task:");
                        System.out.println(tasks[taskCount].toString());
                        System.out.println("__________________________________\n");
                        taskCount++;
                        saveTasks(tasks, taskCount);
                        break;

                    default:
                        throw new UnknownCommandException();
                }
            } catch (EveException e) {
                System.out.println("⚠️  " + e.getMessage());
                System.out.println("__________________________________\n");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(
                        "EVE ERROR: It seems like some information is missing. Please check your command and try again.");
                System.out.println("__________________________________\n");
            } catch (NumberFormatException e) {
                System.out.println("EVE ERROR: Please enter a valid task number.");
                System.out.println("__________________________________\n");
            }
        }
    }

    private static int loadTasks(Task[] tasks) {
        int count = 0;
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return 0;
        }

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(" \\| ");

                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];

                Task task = null;
                switch (type) {
                    case "T":
                        task = new ToDo(description);
                        break;
                    case "D":
                        task = new Deadline(description, parts[3]);
                        break;
                    case "E":
                        task = new Event(description, parts[3], parts[4]);
                        break;
                }

                if (task != null) {
                    if (isDone)
                        task.markAsDone();
                    tasks[count] = task;
                    count++;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No existing data found.");
        } catch (Exception e) {
            System.out.println("⚠️ Warning: Data file corrupted. Starting fresh.");
        }
        return count;
    }

    private static void saveTasks(Task[] tasks, int taskCount) {
        try {
            File file = new File(FILE_PATH);
            File parentDir = file.getParentFile();

            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }

            FileWriter writer = new FileWriter(file);
            for (int i = 0; i < taskCount; i++) {
                writer.write(tasks[i].toFileFormat() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("⚠️ Error saving data: " + e.getMessage());
        }
    }
}
