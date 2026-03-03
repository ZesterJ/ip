package eve.ui;

import java.util.ArrayList;
import eve.tasks.Task;

public class Ui {

    public void printLine() {
        System.out.println("__________________________________\n");
    }

    public void printWelcomeMessage() {
        String logo = " _____             _____ \n" + "| ____|  \\     /  | ____|\n" + "|  _|     \\   /   |  _|  \n"
                + "| |___     \\ /    | |___ \n" + "|_____|     V     |_____|\n";

        System.out.println("Hello! I am\n" + logo);
        System.out.println("How may I be of assistance today?");
        printLine();
    }

    public void printGoodbyeMessage() {
        System.out.println("Goodbye. Have a nice day!");
        printLine();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showError(String errorMessage) {
        System.out.println("⚠️  " + errorMessage);
        printLine();
    }

    public void printTaskAdded(Object task, int totalTasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
        printLine();
    }

    public void printTaskDeleted(Object task, int totalTasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
        printLine();
    }

    public void printTaskMarked(Object task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
        printLine();
    }

    public void printTaskUnmarked(Object task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
        printLine();
    }

    public void showFindResults(ArrayList<Task> matchingTasks) {
        printLine();
        System.out.println("Here are the matching tasks in your list:");

        if (matchingTasks.isEmpty()) {
            System.out.println("No matching tasks found.");
        } else {
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println((i + 1) + "." + matchingTasks.get(i).toString());
            }
        }
        printLine();
    }
}
