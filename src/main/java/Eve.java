import java.util.Scanner;

public class Eve {
    public static void main(String[] args) {

        String logo =  " _____             _____ \n"
                    + "| ____|  \\     /  | ____|\n"
                    + "|  _|     \\   /   |  _|  \n"
                    + "| |___     \\ /    | |___ \n"
                    + "|_____|     V     |_____|\n";

        System.out.println("Hello! I am\n" + logo);
        System.out.println("How may I be of assistance today?");
        System.out.println("__________________________________\n");

        Task[] tasks = new Task[100];
        int taskCount = 0;
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            String userInput = sc.nextLine().trim();
            String[] inputParts = userInput.split(" ", 2);
            String command = inputParts[0].toLowerCase();
            System.out.println("__________________________________\n");

            switch (command){
            case "bye":
                System.out.println("Goodbye. Have a nice day!");
                System.out.println("__________________________________\n");
                sc.close();
                return;

            case "list":
                if (taskCount == 0){
                  System.out.println("Your Task List is empty.");
                  break;  
                } else {
                    System.out.println("Here are the tasks in your list:");
                    for(int i = 0; i < taskCount; i += 1){
                        System.out.println((i+1) + "[" + tasks[i].GetStatusIcon() + "] " + tasks[i].description);
                    }
                    System.out.println("__________________________________\n");
                }
                break;
            
            case "mark":
                int index = Integer.parseInt(inputParts[1]) - 1;
                tasks[index].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[" + tasks[index].GetStatusIcon() + "] " + tasks[index].description);
                System.out.println("__________________________________\n");
                break;

            case "unmark":
                int unmarkIndex = Integer.parseInt(inputParts[1]) - 1;
                tasks[unmarkIndex].markAsNotDone();
                System.out.println("The following task will be marked as undone:");
                System.out.println(".[" + tasks[unmarkIndex].GetStatusIcon() + "] " + tasks[unmarkIndex].description);
                System.out.println("__________________________________\n");
                break;

            default:
                System.out.println("added: " + userInput);
                System.out.println("__________________________________\n");
                tasks[taskCount] = new Task(userInput);
                taskCount += 1;
                break;
            }
        }
    }
}
