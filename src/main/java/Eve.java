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
            default:
                System.out.println(userInput);
                System.out.println("__________________________________\n");
                break;
            }
        }
    }
}
