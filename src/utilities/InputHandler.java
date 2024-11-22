package utilities;

import java.util.Random;
import java.util.Scanner;

public abstract class InputHandler {

    public static Scanner scanner = new Scanner(System.in);

    // Returns a boolean.
    public static boolean getBoolean(){
        boolean result;
        while(true){
            String input = scanner.nextLine();
            if(input.toLowerCase().startsWith("y")){
                result = true;
                break;
            }
            else if(input.toLowerCase().startsWith("n")){
                result = false;
                break;
            }
            System.out.println("Invalid input. Please enter Y(es) or N(o).");
        }
        return result;
    }

    // Returns a string
    public static String getString(){
        return scanner.nextLine();
    }

    // Returns a random integer in the specified range.
    public static int getRandomIntInRange(int min, int max){
        Random rand = new Random();
        return rand.nextInt(min, max+1);
    }

    // Returns an integer in the specified range
    public static int getIntInRange(int min, int max){
        while(true){
            while(!scanner.hasNextInt()){
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine();
            }
            int value = scanner.nextInt();
            scanner.nextLine();
            if(value >= min && value <= max){
                return value;
            }
            System.out.println("Invalid input. Please enter a valid integer.");
        }
    }
}
