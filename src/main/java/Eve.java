import java.util.Scanner;
import java.util.ArrayList;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

import exceptions.EveException;
import exceptions.MissingDescriptionException;
import exceptions.UnknownCommandException;

public class Eve {
    public static void main(String[] args) {

        String logo = " _____             _____ \n"
                + "| ____|  \\     /  | ____|\n"
                + "|  _|     \\   /   |  _|  \n"
                + "| |___     \\ /    | |___ \n"
                + "|_____|     V     |_____|\n";

        System.out.println("Hello! I am\n" + logo);
        System.out.println("How may I be of assistance today?");
        System.out.println("__________________________________\n");

        ArrayList<Task> taskList = new ArrayList<>();
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
                        if (taskList.isEmpty()) {
                            System.out.println("Your Task List is empty.");
                            break;
                        } else {
                            System.out.println("Here are the tasks in your list:");
                            for (int i = 0; i < taskList.size(); i += 1) {
                                System.out.println((i + 1) + ". " + taskList.get(i).toString());
                            }
                            System.out.println("__________________________________\n");
                        }
                        break;

                    case "mark":
                        if (inputParts.length < 2) {
                            throw new EveException("Please specify which task number to mark.");
                        }

                        int index = Integer.parseInt(inputParts[1]) - 1;
                        if (index < 0 || index >= taskList.size()) {
                            throw new EveException("The task number you provided is out of range.");
                        }
                        taskList.get(index).markAsDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(taskList.get(index).toString());
                        System.out.println("__________________________________\n");
                        break;

                    case "unmark":
                        if (inputParts.length < 2) {
                            throw new EveException("Please specify which task number to unmark.");
                        }

                        int unmarkIndex = Integer.parseInt(inputParts[1]) - 1;

                        if (unmarkIndex < 0 || unmarkIndex >= taskList.size()) {
                            throw new EveException("The task number you provided is out of range.");
                        }
                        taskList.get(unmarkIndex).markAsNotDone();
                        System.out.println("The following task will be marked as undone:");
                        System.out.println(taskList.get(unmarkIndex).toString());
                        System.out.println("__________________________________\n");
                        break;

                    case "delete":
                        if (inputParts.length < 2) {
                            throw new EveException("Please specify which task number to delete.");
                        }

                        int deleteIndex = Integer.parseInt(inputParts[1]) - 1;
                        if (deleteIndex < 0 || deleteIndex >= taskList.size()) {
                            throw new EveException("The task number you provided is out of range.");
                        }

                        Task removedTask = taskList.remove(deleteIndex);
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(removedTask.toString());
                        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                        System.out.println("__________________________________\n");
                        break;

                    case "todo":
                        if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
                            throw new MissingDescriptionException("todo");
                        }

                        String todoDescription = inputParts[1];
                        taskList.add(new ToDo(todoDescription));
                        System.out.println("Got it. I have added this task:");
                        System.out.println(taskList.get(taskList.size() - 1).toString());
                        System.out.println("__________________________________\n");
                        break;

                    case "deadline":
                        if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
                            throw new MissingDescriptionException("deadline");
                        }

                        String[] deadlinesParam = inputParts[1].split("/", 2);
                        taskList.add(new Deadline(deadlinesParam[0], deadlinesParam[1]));
                        System.out.println("Got it. I have added this task:");
                        System.out.println(taskList.get(taskList.size() - 1).toString());
                        System.out.println("__________________________________\n");
                        break;

                    case "event":
                        if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
                            throw new MissingDescriptionException("event");
                        }

                        String[] eventsParam = inputParts[1].split("/", 3);
                        taskList.add(new Event(eventsParam[0], eventsParam[1], eventsParam[2]));
                        System.out.println("Got it. I have added this task:");
                        System.out.println(taskList.get(taskList.size() - 1).toString());
                        System.out.println("__________________________________\n");
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
}
