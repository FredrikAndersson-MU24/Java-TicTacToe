package GameBoard;

import java.util.ArrayList;
import java.util.List;

public class GameBoard {
    List<Square> squares = new ArrayList<>();
    List<String> separation = new ArrayList<>();


    public void generateGameBoardSquares(){
        Square square1 = new Square('1');
        Square square2 = new Square('2');
        Square square3 = new Square('3');
        Square square4 = new Square('4');
        Square square5 = new Square('5');
        Square square6 = new Square('6');
        Square square7 = new Square('7');
        Square square8 = new Square('8');
        Square square9 = new Square('9');
        squares.add(square1);
        squares.add(square2);
        squares.add(square3);
        squares.add(square4);
        squares.add(square5);
        squares.add(square6);
        squares.add(square7);
        squares.add(square8);
        squares.add(square9);
        separation.add("----+---+----");
        separation.add("-------------");
    }

    public String printGameBoardSquares() {
        return  "\n" + separation.get(1) +
                "\n| " + squares.get(0).getMarker() + " | " + squares.get(1).getMarker() + " | " +  squares.get(2).getMarker() + " |" +
                "\n" + separation.get(0) +
                "\n| " + squares.get(3).getMarker() + " | " +  squares.get(4).getMarker() + " | " +  squares.get(5).getMarker() + " |"  +
                "\n" + separation.get(0) +
                "\n| " + squares.get(6).getMarker() + " | " + squares.get(7).getMarker() + " | " + squares.get(8).getMarker() + " |" +
                "\n" + separation.get(1);
    }

    public List<Square> getSquares() {
        return squares;
    }

    public boolean checkIfAllOccupied(){
        boolean result = true;
        for(Square square : squares){
            if (!square.isOccupied()) {
                result = false;
                break;
            }
        }
        return result;
    }

    public boolean checkIfWin(char marker){
        boolean isWin = false;
        if(squares.get(0).getMarker() == marker && squares.get(1).getMarker() == marker && squares.get(2).getMarker() == marker){
            System.out.println("Row 1");
            isWin = true;
        }
        if(squares.get(3).getMarker() == marker && squares.get(4).getMarker() == marker && squares.get(5).getMarker() == marker){
            System.out.println("Row 2");
            isWin = true;
        }
        if(squares.get(6).getMarker() == marker && squares.get(7).getMarker() == marker && squares.get(8).getMarker() == marker){
            System.out.println("Row 3");
            isWin = true;
        }
        if(squares.get(0).getMarker() == marker && squares.get(3).getMarker() == marker && squares.get(6).getMarker() == marker){
            System.out.println("Column 1");
            isWin = true;
        }
        if(squares.get(1).getMarker() == marker && squares.get(4).getMarker() == marker && squares.get(7).getMarker() == marker){
            System.out.println("Column 2");
            isWin = true;
        }
        if(squares.get(2).getMarker() == marker && squares.get(5).getMarker() == marker && squares.get(8).getMarker() == marker){
            System.out.println("Column 3");
            isWin = true;
        }
        return  isWin;
    }
}
