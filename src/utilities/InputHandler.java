package utilities;

import java.util.Scanner;

public class InputHandler {

    Scanner scanner = new Scanner(System.in);


    public boolean getBoolean(){
        boolean result = false;
        while(true){
            String input = scanner.nextLine();
            scanner.nextLine();
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


    public String getString(){
        String input = scanner.nextLine();
        scanner.nextLine();
        return input;
    }


    public String getmarkerPlacement(){
        while(true){
            String input = scanner.nextLine();
            scanner.nextLine();
            if(input.length() == 2){
                if(input.toLowerCase().startsWith("y")){}
            }

            System.out.println("Please enter a valid coordinate.");

        }


    }


}
