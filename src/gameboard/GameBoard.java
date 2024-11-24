package gameboard;

import java.util.ArrayList;
import java.util.List;

public class GameBoard {
    List<Square> grid = new ArrayList<>();
    List<String> separations = new ArrayList<>();

    // Adds instances of Square to fill a 3x3 grid to the grid List.
    // (In retrospect, the Square class might have been a longer than necessary route to take. It might have sufficed
    // having a list of chars, and checking if they were the default value or one of the markers. Oh well, at least I
    // got the chance to practice working with objects. )
    public void generateGameBoard(){
        for(int i = 0; i <=8; i++){
            grid.add(new Square(' '));
        }
        separations.add("----+---+----"); // These might seem unnecessary, I just added them to practice using lists
        separations.add("-------------");
    }

    //Print a 3x3 grid. square() gets each individual square's marker-state to present a placed marker or empty square.

    public void printGameBoard() {
        System.out.println( "\n" + separations.get(1) +
                            "\n| " + square(0) + " | " + square(1) + " | " + square(2) + " |" +
                            "\n" + separations.get(0) +
                            "\n| " + square(3) + " | " + square(4) + " | " + square(5) + " |" +
                            "\n" + separations.get(0) +
                            "\n| " + square(6) + " | " + square(7) + " | " + square(8) + " |" +
                            "\n" + separations.get(1));
    }

    public List<Square> getGrid() {
        return grid;
    }

    //Checks if all squares of the grid are occupied. Returns false if there are still unoccupied squares.
    public boolean checkIfAllOccupied(){
        boolean result = true;
        for(Square square : grid){
            if (!square.isOccupied()) {
                result = false;
                break;
            }
        }
        return result;
    }

    //Checks if the most recent input resulted in the player getting three in a row. Returns true if it is a win
    public boolean checkIfWin(char marker){
        boolean isWin = false;
        // Check rows
        for(int i = 0; i <=6; i+= 3){
            if(isARow(i,i+1,i+2, marker)) {
                isWin = true;
                break;
            }
        }
        // Check Columns
        for(int i = 0; i <=2; i++){
            if(isARow(i,i+3,i+6, marker)) {
                isWin = true;
                break;
            }
        }
//
//        //Row 1
//        if(isARow(0,1,2, marker)){
//            isWin = true;
//        }
//        //Row 2
//        if(isARow(3,4,5, marker)){
//            isWin = true;
//        }
//        //Row 3
//        if(isARow(6,7,8, marker)){
//            isWin = true;
//        }
//        //Column 1
//        if(isARow(0,3,6, marker)){
//            isWin = true;
//        }
//        //Column 2
//        if(isARow(1,4,7, marker)){
//            isWin = true;
//        }
//        //Column 3
//        if(isARow(2,5,8, marker)){
//            isWin = true;
//        }
        //Diagonal from upper left
        if(isARow(0,4,8, marker)){
            isWin = true;
        }
        //Diagonal from lower left
        if(isARow(6,4,2, marker)){
            isWin = true;
        }
        return isWin;
    }

    // Takes three ints and a char as arguments to check if the char is in the square represented by each int.
    // Used to check if there are three identical chars in a row.
    public boolean isARow(int first, int second, int third, char marker){
        return (square(first) == marker && square(second) == marker && square(third) == marker);
    }

    // Increases readability for printGameBoard().
    public char square(int num){
    return grid.get(num).getMarker();
    }

    // Create a list of available squares, used to check which turn the game is in to determine if the CPU
    // starts first or second
    public int getOccupied(){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < getGrid().size(); i++) {
            if (!getGrid().get(i).isOccupied()) {
                list.add(i);
            }
        }
        return list.size();
    }

}
