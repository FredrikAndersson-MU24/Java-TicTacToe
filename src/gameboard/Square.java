package gameboard;

//////////////////////////////////////////////////////////
//  Instances of this class represent one single        //
//  square of the grid that makes up the game board.    //
//////////////////////////////////////////////////////////

public class Square {
    private boolean occupied;
    private char marker;

    public Square(char marker){
        this.marker = marker;
        this.occupied = false;
    }

    public char getMarker() {
        return marker;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void toggleOccupied() {
        this.occupied = !occupied;
    }

    public void setMarker(char marker) {
        this.marker = marker;
    }
}
