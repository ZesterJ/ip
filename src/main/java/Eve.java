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

        String[] tasks = new String[100];
        int taskCount = 0;
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            String userInput = sc.nextLine().trim();
            System.out.println("__________________________________\n");

            switch (userInput.toLowerCase()){
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
                    for(int i = 0; i < taskCount; i += 1){
                        System.out.println((i+1) + ". " + tasks[i]);
                    }
                    System.out.println("__________________________________\n");
                }
                break;
            default:
                System.out.println("added: " + userInput);
                System.out.println("__________________________________\n");
                tasks[taskCount] = userInput;
                taskCount += 1;
                break;
            }
        }
    }
}
