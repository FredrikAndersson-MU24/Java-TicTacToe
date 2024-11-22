package gameboard;

//////////////////////////////////////////////////////////
//  Instances of this class represent one single        //
//  square of the grid that makes up the game board.    //
//////////////////////////////////////////////////////////
// (In retrospect, the Square class might have been a longer than necessary route to take. It might have sufficed
// having a list of chars, and checking if they were the default value or one of the markers. Oh well, at least I
// got the chance to practice working with objects. )     

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
