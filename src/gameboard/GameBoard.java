package gameboard;

import java.util.ArrayList;
import java.util.List;

public class GameBoard {
    List<Square> grid = new ArrayList<>();
    List<String> separation = new ArrayList<>();

    // Adds instances of Square to fill a 3x3 grid to the grid List.
    public void generateGameBoard(){
        grid.add(new Square(' '));
        grid.add(new Square(' '));
        grid.add(new Square(' '));
        grid.add(new Square(' '));
        grid.add(new Square(' '));
        grid.add(new Square(' '));
        grid.add(new Square(' '));
        grid.add(new Square(' '));
        grid.add(new Square(' '));
        separation.add("----+---+----");
        separation.add("-------------");
    }
    //Print a 3x3 grid. .getMarker() gets each individual square's marker-state to present a placed marker or empty.
    public String printGameBoard() {
        return  "\n" + separation.get(1) +
                "\n| " + grid.get(0).getMarker() + " | " + grid.get(1).getMarker() + " | " +  grid.get(2).getMarker() + " |" +
                "\n" + separation.get(0) +
                "\n| " + grid.get(3).getMarker() + " | " +  grid.get(4).getMarker() + " | " +  grid.get(5).getMarker() + " |"  +
                "\n" + separation.get(0) +
                "\n| " + grid.get(6).getMarker() + " | " + grid.get(7).getMarker() + " | " + grid.get(8).getMarker() + " |" +
                "\n" + separation.get(1);
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
        //Row 1
        if(grid.get(0).getMarker() == marker && grid.get(1).getMarker() == marker && grid.get(2).getMarker() == marker){
            isWin = true;
        }
        //Row 2
        if(grid.get(3).getMarker() == marker && grid.get(4).getMarker() == marker && grid.get(5).getMarker() == marker){
            isWin = true;
        }
        //Row 3
        if(grid.get(6).getMarker() == marker && grid.get(7).getMarker() == marker && grid.get(8).getMarker() == marker){
            isWin = true;
        }
        //Column 1
        if(grid.get(0).getMarker() == marker && grid.get(3).getMarker() == marker && grid.get(6).getMarker() == marker){
            isWin = true;
        }
        //Column 2
        if(grid.get(1).getMarker() == marker && grid.get(4).getMarker() == marker && grid.get(7).getMarker() == marker){
            isWin = true;
        }
        //Column 3
        if(grid.get(2).getMarker() == marker && grid.get(5).getMarker() == marker && grid.get(8).getMarker() == marker){
            isWin = true;
        }
        //Diagonal from lower left
        if(grid.get(0).getMarker() == marker && grid.get(4).getMarker() == marker && grid.get(8).getMarker() == marker){
            isWin = true;
        }
        //Diagonal from upper left
        if(grid.get(6).getMarker() == marker && grid.get(4).getMarker() == marker && grid.get(2).getMarker() == marker){
            isWin = true;
        }
        return  isWin;
    }
}
