package player;

import gameboard.GameBoard;
import utilities.InputHandler;

// This class is abstract, since I don't want any instances of it.
public abstract class PlayerService {

    // Let the human player enter their name
    public static String inputName(String string){
        System.out.println(string + "Enter your name: ");
        return InputHandler.getString();
    }

    // Marker selection. Lets the player choose their marker. Takes an int as argument to decide which if statement is
    // used. As i have set it up player 1 gets to choose from the first if statement,
    // player 2 from the second. CPU1 and CPU2 has hardcoded markers.
    public static char markerSelection(int i){
        char marker = 0;
        int choice;
        System.out.println("Select your marker:");
        if(i == 1){
            System.out.println("1 - O");
            System.out.println("2 - @");
            System.out.println("3 - C");
            choice = InputHandler.getIntInRange(1,3);
            // Apparently this is another way to write a switch statement (IntelliJ suggested it).
            // When it's a simple statement like this, I think it looks a lot cleaner.
            marker = switch (choice) {
                case 1 -> 'O';
                case 2 -> '@';
                case 3 -> 'C';
                default -> marker;
            };
        }
        if(i == 2) {
            System.out.println("1 - X");
            System.out.println("2 - &");
            System.out.println("3 - %");
            choice = InputHandler.getIntInRange(1,3);
            marker = switch (choice) {
                case 1 -> 'X';
                case 2 -> '&';
                case 3 -> '%';
                default -> marker;
            };
        }
        return marker;
    }

    // Handle the player placing their marker. Check if the square is occupied and place the marker if it is not.
    public static void placeMarker(GameBoard gameboard, Player currentPlayer, Player otherPlayer){
        System.out.println(currentPlayer.getName() + ", it is your turn.");
        if (currentPlayer instanceof HumanPlayer){
            System.out.println("Enter marker placement as integer: ");
            int placement;
            while(true){
                placement = InputHandler.getIntInRange(1,9);
                if(!gameboard.getGrid().get(placement-1).isOccupied()){
                    break;
                }
                System.out.println("Square already occupied");
            }
            gameboard.getGrid().get(placement-1).setMarker(currentPlayer.getMarker());
            gameboard.getGrid().get(placement-1).toggleOccupied();
        }
        if(currentPlayer instanceof CpuPlayer){
            ((CpuPlayer) currentPlayer).placeMarker(gameboard, otherPlayer);
        }
    }

    // Difficulty level selection when playing against CPU opponent
    public static boolean chooseDifficulty(){
        System.out.println("Please select CPU skill level: ");
        System.out.println("1 - Noob");
        System.out.println("2 - Pro");
        int i = InputHandler.getIntInRange(1,2);
        boolean bool = false;
        switch(i){
            case 1:
                bool = false;
                break;
            case 2:
                bool = true;
                break;
        }
        return bool;
    }
}