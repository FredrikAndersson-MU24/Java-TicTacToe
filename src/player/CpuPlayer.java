package player;

import gameboard.GameBoard;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CpuPlayer extends Player {

    // This is set to false when a noob CPU is instanced and true if its a pro player.
    // Determines which method of placing marker is used.
    private final boolean proLevel;

    // Constructor for the CPUPlayer
    public CpuPlayer(String name, char marker, boolean proLevel) {
        super(name, marker);
        this.proLevel = proLevel;
    }

    // This method is invoked on every CPU players turn, it checks which "skill" level the CPU player should use
    public void placeMarker(GameBoard gameboard, Player otherPlayer){
        if(proLevel){
            proLevelV2(gameboard, this.getMarker(), otherPlayer.getMarker());
        } else {
            noobLevel(gameboard, this.getMarker());
        }
    }

    // The easier CPU opponent alternative. Places markers randomly. Invoked from placeMarker().
    public void noobLevel(GameBoard gameboard, char thisMarker) {
        randomPlacement(gameboard, thisMarker);
    }

    //////////////////////////////////////////////////////////////
    //         Second attempt at a winning algorithm            //
    //     This saved 16000+ chars and 200+ lines of code :D    //
    //      It also seems more stable and less prone to fail.   //
    // I saved the old mess (proLevel) below, just to keep as a //
    //                      comparison.                         //
    //////////////////////////////////////////////////////////////
    public void proLevelV2(GameBoard gameboard, char thisMarker, char otherMarker) {
        // Create
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < gameboard.getGrid().size(); i++) {
            if (!gameboard.getGrid().get(i).isOccupied()) {
                list.add(i);
            }
        }
        // If cpu starts, always start in the top left
        if (list.size() == 9) {
            setMarkerAndToggle(gameboard,0,thisMarker);
        // When the cpu starts second, start with middle centre, alternatively top left if middle center is taken
        }else if (list.size() == 8) {
            if(notOccupied(gameboard, 4)){
                setMarkerAndToggle(gameboard, 4, thisMarker);
            } else {
                setMarkerAndToggle(gameboard,0,thisMarker);
            }
        }else {
            orderToCheck(gameboard, thisMarker, otherMarker);
        }
    }

    // This method determines in which order the CPUplayer checks for a move.
    // If a move is performed in a mathod, it returns false and stops the rest of the if statements.
    // (built this for proLevelV2)
    public void orderToCheck(GameBoard gameboard, char thisMarker, char otherMarker){
        boolean play;
        // First it checks if there is a winning move available.
        play = checkVertically(gameboard, thisMarker, thisMarker);
        if (play) {
            play = checkHorizontally(gameboard, thisMarker, thisMarker);
        }
        if (play){
            play = checkDiagonally(gameboard, thisMarker, thisMarker);
        }
        // Second it checks if there is a blocking move that needs to be done.
        if (play){
            play = blockCorners(gameboard, thisMarker, otherMarker);
        }
        if (play){
            play = checkDiagonally(gameboard, thisMarker, otherMarker);
        }
        if (play) {
            play = checkVertically(gameboard, thisMarker, otherMarker);
        }
        if (play) {
            play = checkHorizontally(gameboard, thisMarker, otherMarker);
        }
        // Third it places a marker to open up for the next move.
        if (play){
            play = placeCorners(gameboard,thisMarker);
        }
        if (play) {
            play = placeHorizontally(gameboard, thisMarker, thisMarker);
        }
        if (play) {
            play = placeVertically(gameboard, thisMarker, thisMarker);
        }
        if (play) {
            randomPlacement(gameboard, thisMarker);
        }
    }

    // Checks if there are two of the same in a horizontal row and places own marker to win or block other player,
    // depending on given parameters for markerToPlace and markerToLookFor. (built this for proLevelV2)
    private boolean checkHorizontally(GameBoard gameboard, char markerToPlace, char markerToLookFor) {
        boolean result = true;
        for (int i = 0; i <= 2; i ++) {
            if (twoOccupiedOneNotAnd(gameboard, i, i + 3, i + 6, markerToLookFor)) {
                setMarkerAndToggle(gameboard, i + 6, markerToPlace);
                result = false;
                break;
            } else if (twoOccupiedOneNotAnd(gameboard, i + 3, i + 6, i, markerToLookFor)) {
                setMarkerAndToggle(gameboard, i, markerToPlace);
                result = false;
                break;
            } else if (twoOccupiedOneNotAnd(gameboard, i + 6, i, i + 3, markerToLookFor)) {
                setMarkerAndToggle(gameboard, i + 3, markerToPlace);
                result = false;
                break;
            }
        }
        return result;
    }

    // Checks if there are two of the same in a column and places own marker to win or block other marker, depending on
    // given parameters for markerToPlace and markerToLookFor. (built this for proLevelV2)
    private boolean checkVertically(GameBoard gameboard, char markerToPlace, char markerToLookFor) {
        boolean result = true;
        for (int i = 0; i <= 6; i += 3) {
            if (twoOccupiedOneNotAnd(gameboard, i, i + 1, i + 2, markerToLookFor)) {
                setMarkerAndToggle(gameboard, i + 2, markerToPlace);
                result = false;
                break;
            } else if (twoOccupiedOneNotAnd(gameboard, i + 1, i + 2, i, markerToLookFor)) {
                setMarkerAndToggle(gameboard, i, markerToPlace);
                result = false;
                break;
            } else if (twoOccupiedOneNotAnd(gameboard, i + 2, i, i + 1, markerToLookFor)) {
                setMarkerAndToggle(gameboard, i + 1, markerToPlace);
                result = false;
                break;
            }
        }
        return result;
    }

    // Used to check if CPU can win by placing a marker to complete a diagonal line
    // or block the other player from getting a diagonal win. (built this for proLevelV2)
    private boolean checkDiagonally(GameBoard gameboard, char markerToPlace, char markerToLookFor) {
        boolean result = true;
        if (twoOccupiedOneNotAnd(gameboard, 0, 4, 8, markerToLookFor)) {
            setMarkerAndToggle(gameboard, 8, markerToPlace);
            result = false;
        } else if (twoOccupiedOneNotAnd(gameboard, 2, 4, 6, markerToLookFor)) {
            setMarkerAndToggle(gameboard, 6, markerToPlace);
            result = false;
        } else if (twoOccupiedOneNotAnd(gameboard, 6, 4, 2, markerToLookFor)) {
            setMarkerAndToggle(gameboard, 2, markerToPlace);
            result = false;
        } else if (twoOccupiedOneNotAnd(gameboard, 8, 4, 0, markerToLookFor)) {
            setMarkerAndToggle(gameboard, 0, markerToPlace);
            result = false;
        }
        return result;
    }

    // Used if there is no row with two of the same markers already placed. (built this for proLevelV2)
    public boolean placeHorizontally(GameBoard gameboard, char markerToPlace, char markerToLookFor) {
        boolean result = true;
        for (int i = 0; i <= 6; i += 3) {
            if (isOccupiedBy(gameboard, i, markerToLookFor) && twoNotOccupied(gameboard, i + 1, i + 2)) {
                setMarkerAndToggle(gameboard, i + 2, markerToPlace);
                result = false;
            } else if (isOccupiedBy(gameboard, i + 1, markerToLookFor) && twoNotOccupied(gameboard, i, i + 2)) {
                setMarkerAndToggle(gameboard, i, markerToPlace);
                result = false;
            } else if (isOccupiedBy(gameboard, i + 2, markerToLookFor) && twoNotOccupied(gameboard, i, i + 1)) {
                setMarkerAndToggle(gameboard, i, markerToPlace);
                result = false;
            }
        }
        return result;
    }

    // Used if there is no column with two of the same markers already placed. (built this for proLevelV2)
    public boolean placeVertically(GameBoard gameboard, char markerToPlace, char markerToLookFor) {
        boolean result = true;
        for (int i = 0; i <= 2; i++) {
            if (isOccupiedBy(gameboard, i, markerToLookFor) && twoNotOccupied(gameboard, i + 3, i + 6)) {
                setMarkerAndToggle(gameboard, i + 6, markerToPlace);
                result = false;
            } else if (isOccupiedBy(gameboard, i + 3, markerToLookFor) && twoNotOccupied(gameboard, i, i + 6)) {
                setMarkerAndToggle(gameboard, i, markerToPlace);
                result = false;
            } else if (isOccupiedBy(gameboard, i + 6, markerToLookFor) && twoNotOccupied(gameboard, i, i + 3)) {
                setMarkerAndToggle(gameboard, i, markerToPlace);
                result  = false;
            }
        }
        return result;
    }

    // Used to block corners that open up two possibilities for the opponent, if they dont start in a corner.
    public boolean blockCorners(GameBoard gameboard, char markerToPlace, char markerToLookFor) {
        boolean result = true;
        if (isOccupiedBy(gameboard, 4, markerToPlace)) {
            if (twoOccupiedAnd(gameboard, 1, 5, markerToLookFor) &&
                threeNotOccupied(gameboard,0, 2, 8)) {
                setMarkerAndToggle(gameboard, 2, markerToPlace);
                result = false;
            } else if
            (twoOccupiedAnd(gameboard, 5, 7, markerToLookFor) &&
                threeNotOccupied(gameboard, 2, 8, 6)) {
                setMarkerAndToggle(gameboard, 8, markerToPlace);
                result = false;
            } else if
            (twoOccupiedAnd(gameboard, 7, 3, markerToLookFor) &&
            threeNotOccupied(gameboard, 8, 6, 0)) {
                setMarkerAndToggle(gameboard, 6, markerToPlace);
                result = false;
            } else if
            (twoOccupiedAnd(gameboard, 3, 1, markerToLookFor) &&
            threeNotOccupied(gameboard, 6, 0, 2)){
                setMarkerAndToggle(gameboard, 0, markerToPlace);
                result = false;
            }
        }
        return result;
    }

    // Used to place marker in a corner when the diagonal line is still not blocked
    public boolean placeCorners(GameBoard gameboard, char markerToPlace) {
        boolean result = true;
        if (isOccupiedBy(gameboard, 4, markerToPlace)) {
            if (    twoNotOccupied(gameboard, 0, 8)) {
                setMarkerAndToggle(gameboard, 0, markerToPlace);
                result = false;
            } else if
            (twoNotOccupied(gameboard,2,6)) {
                setMarkerAndToggle(gameboard, 2, markerToPlace);
                result = false;
            }
        }
        return result;
    }


    // Sets the players marker in the sqaure and toggles its occupied status.(used in both proLevel methods)
    public void setMarkerAndToggle(GameBoard gameboard, int num, char marker){
        gameboard.getGrid().get(num).setMarker(marker);
        gameboard.getGrid().get(num).toggleOccupied();
    }

    // Checks whether the specified marker is in the square(used in both proLevel methods). I made it a method
    // because i thgought the code looked cleaner and more readable than writing the whole .getMarker out every time.
    public boolean isOccupiedBy(GameBoard gameboard, int num, char marker){
        return gameboard.getGrid().get(num).getMarker() == marker;
    }

    // Checks if a square is occupied by getting its isOccupied state.(used in both proLevel methods)
    public boolean notOccupied(GameBoard gameboard, int num){
        return !gameboard.getGrid().get(num).isOccupied();
    }

    // Checks if two are occupied
    public boolean twoNotOccupied(GameBoard gameboard, int first, int second){
        return notOccupied(gameboard, first) && notOccupied(gameboard, second);
    }
    // Checks if three are occupied
    public boolean threeNotOccupied(GameBoard gameboard, int first, int second, int third){
        return notOccupied(gameboard, first) && notOccupied(gameboard, second) && notOccupied(gameboard, third);
    }

    // Checks two squares if they are both occupied by the specified marker and if the "third in a row"
    // square is unoccupied. Used both for placing a players third to win or prevent the other player of
    // placing their third.(used in both proLevel methods)
    public boolean twoOccupiedOneNotAnd(GameBoard gameboard, int first, int second, int third, char marker){
        return  isOccupiedBy(gameboard, first, marker) &&
                isOccupiedBy(gameboard, second, marker) &&
                notOccupied(gameboard, third);
    }

    // This is used to randomly place marker depending on which squares are left unoccupied.
    // Used in noobLevel() and as a last resort in the fourth turn if CPU starts second, as draw should be the only
    // possibility (used in both proLevel methods)
    public void randomPlacement(GameBoard gameboard, char thisMarker) {
        List<Integer> remains = new ArrayList<>();
        for (int i = 0; i < gameboard.getGrid().size(); i++) {
            if (!gameboard.getGrid().get(i).isOccupied()) {
                remains.add(gameboard.getGrid().indexOf(gameboard.getGrid().get(i)));
            }
        }
        Random rand = new Random();
        setMarkerAndToggle(gameboard, remains.get(rand.nextInt(remains.size())), thisMarker);
    }

    // Checks two squares if they are both occupied by the specified marker (used in both proLevel methods)
    public boolean twoOccupiedAnd(GameBoard gameboard, int first, int second, char marker){
        return  isOccupiedBy(gameboard, first, marker) &&
                isOccupiedBy(gameboard, second, marker);
    }
    //////////////////////////////////////////////////////////////////////////////
    //  Okay, here is my first attempt at making a method for the CPUPlayer     //
    //  that tries to win, rather than just randomly placing markers.           //
    //  It's a mess :D                                                          //
    //  But as far as I've managed to test it it seems to work.                 //
    //  "Strategy" from the tic-tac-toe wikipedia page.                         //
    //  This could be cleaned up significantly with smarter methods.            //
    //  Invoked from placeMarker().                                             //
    //////////////////////////////////////////////////////////////////////////////
// public void proLevelV1(GameBoard gameboard, char thisMarker, char otherMarker) {
//        List<Integer> list = new ArrayList<>();
//        for (int i = 0; i < gameboard.getGrid().size(); i++) {
//            if (!gameboard.getGrid().get(i).isOccupied()) {
//                list.add(i);
//            }
//        }
//        //////////////////////////////////////
//        //        If CPU plays first        //
//        //////////////////////////////////////
//        // First turn
//        if (list.size() == 9) {
//            setMarkerAndToggle(gameboard,0,thisMarker);
//        }
//        // Second turn
//        if (list.size() == 7) {
//            if(gameboard.getGrid().get(5).isOccupied()){
//                setMarkerAndToggle(gameboard, 4, thisMarker);
//            } else if(!gameboard.getGrid().get(1).isOccupied() && !gameboard.getGrid().get(2).isOccupied()) {
//                setMarkerAndToggle(gameboard, 2, thisMarker);
//            } else if (gameboard.getGrid().get(1).isOccupied() || gameboard.getGrid().get(2).isOccupied()) {
//                setMarkerAndToggle(gameboard, 6, thisMarker);
//            } else if (gameboard.getGrid().get(3).isOccupied() || gameboard.getGrid().get(4).isOccupied() || gameboard.getGrid().get(6).isOccupied()) {
//                setMarkerAndToggle(gameboard, 1, thisMarker);
//            } else if (gameboard.getGrid().get(5).isOccupied()) {
//                setMarkerAndToggle(gameboard, 4, thisMarker);
//            } else if (gameboard.getGrid().get(7).isOccupied() || gameboard.getGrid().get(8).isOccupied()) {
//                setMarkerAndToggle(gameboard, 2, thisMarker);
//            }
//        }
//        // Third turn
//        if (list.size() == 5) {
//            if (gameboard.getGrid().get(1).getMarker() == thisMarker && !gameboard.getGrid().get(2).isOccupied()) {
//                    setMarkerAndToggle(gameboard, 2, thisMarker);
//            }else if (gameboard.getGrid().get(2).getMarker() == thisMarker && !gameboard.getGrid().get(1).isOccupied()) {
//                setMarkerAndToggle(gameboard, 1, thisMarker);
//            }else if (gameboard.getGrid().get(3).getMarker() == thisMarker && !gameboard.getGrid().get(6).isOccupied()) {
//                setMarkerAndToggle(gameboard, 6, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard, 0, 4, 8, thisMarker)) {
//                setMarkerAndToggle(gameboard, 8, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard, 1, 4, 7, thisMarker)) {
//                setMarkerAndToggle(gameboard, 7, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard, 2, 4, 6, thisMarker)) {
//                setMarkerAndToggle(gameboard, 6, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard, 3, 4, 5, thisMarker)) {
//                setMarkerAndToggle(gameboard, 5, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard, 5, 4, 3, thisMarker)) {
//                setMarkerAndToggle(gameboard, 3, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard, 6, 4, 2, thisMarker)) {
//                setMarkerAndToggle(gameboard, 2, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard, 7, 4, 1, thisMarker)) {
//                setMarkerAndToggle(gameboard, 1, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard, 8, 4, 0, thisMarker)) {
//                setMarkerAndToggle(gameboard, 0, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard, 0, 6, 3, thisMarker)) {
//                setMarkerAndToggle(gameboard, 3, thisMarker);
//            }  else if (twoOccupiedOneNotAnd(gameboard, 0, 2, 1, thisMarker)) {
//                setMarkerAndToggle(gameboard, 1, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard, 5, 4, 3, otherMarker)) {
//                setMarkerAndToggle(gameboard, 3, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard, 6, 4, 2, otherMarker)) {
//                setMarkerAndToggle(gameboard, 2, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard, 1, 4, 7, otherMarker)) {
//                setMarkerAndToggle(gameboard, 7, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard, 7, 4, 1, otherMarker)) {
//                setMarkerAndToggle(gameboard, 1, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard, 8, 4, 0, otherMarker)) {
//                setMarkerAndToggle(gameboard, 0, thisMarker);
//            }else if (twoOccupiedOneNotAnd(gameboard, 3, 4, 5, otherMarker)){
//                setMarkerAndToggle(gameboard, 5, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard, 6, 8, 7, otherMarker)){
//                setMarkerAndToggle(gameboard, 7, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard, 1, 2, 0, otherMarker)){
//                setMarkerAndToggle(gameboard, 0, thisMarker);
//            }else if(twoOccupiedAnd(gameboard, 2, 4, otherMarker)){
//                setMarkerAndToggle(gameboard, 6, thisMarker);
//            }else if(twoOccupiedAnd(gameboard, 2, 6, otherMarker)){
//                setMarkerAndToggle(gameboard, 4, thisMarker);
//            } else if(twoOccupiedAnd(gameboard, 5, 8, otherMarker)){
//                setMarkerAndToggle(gameboard, 2, thisMarker);
//            }else if(twoOccupiedAnd(gameboard, 2, 5, otherMarker)){
//                setMarkerAndToggle(gameboard, 8, thisMarker);
//            }else if(twoOccupiedAnd(gameboard, 2, 8, otherMarker)){
//                setMarkerAndToggle(gameboard, 5, thisMarker);
//            } else if (gameboard.getGrid().get(1).getMarker() == thisMarker) {
//                if (!gameboard.getGrid().get(2).isOccupied()) {
//                    setMarkerAndToggle(gameboard, 2, thisMarker);
//                } else {
//                    if (!gameboard.getGrid().get(3).isOccupied()) {
//                        setMarkerAndToggle(gameboard, 3, thisMarker);
//                    } else  if (!gameboard.getGrid().get(4).isOccupied()) {
//                        setMarkerAndToggle(gameboard, 4, thisMarker);
//                    } else if (!gameboard.getGrid().get(7).isOccupied()) {
//                        setMarkerAndToggle(gameboard, 7, thisMarker);
//                    } else if (!gameboard.getGrid().get(8).isOccupied()) {
//                        setMarkerAndToggle(gameboard, 8, thisMarker);
//                    }
//                }
//            } else if (gameboard.getGrid().get(2).getMarker() == thisMarker) {
//                if (!gameboard.getGrid().get(4).isOccupied()) {
//                    setMarkerAndToggle(gameboard, 4, thisMarker);
//                }
//            } else if (gameboard.getGrid().get(3).getMarker() == thisMarker) {
//                if (!gameboard.getGrid().get(6).isOccupied()) {
//                    setMarkerAndToggle(gameboard, 6, thisMarker);
//                } else if (!gameboard.getGrid().get(4).isOccupied()) {
//                    setMarkerAndToggle(gameboard, 4, thisMarker);
//                } else if (!gameboard.getGrid().get(7).isOccupied()) {
//                    setMarkerAndToggle(gameboard, 7, thisMarker);
//                }
//            } else if (notOccupied(gameboard, 4)){
//                setMarkerAndToggle(gameboard, 4, thisMarker);
//            }
//        }
//        // Fourth turn
//        if (list.size() == 3) {
//            if (twoOccupiedOneNotAnd(gameboard, 0, 4, 8, thisMarker)) {
//                setMarkerAndToggle(gameboard, 8, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard, 1, 4, 7, thisMarker)) {
//                setMarkerAndToggle(gameboard, 7, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard, 2, 4, 6, thisMarker)) {
//                setMarkerAndToggle(gameboard, 6, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard, 3, 4, 5, thisMarker)) {
//                setMarkerAndToggle(gameboard, 5, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard, 5, 4, 3, thisMarker)) {
//                setMarkerAndToggle(gameboard, 3, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard, 6, 4, 2, thisMarker)) {
//                setMarkerAndToggle(gameboard, 2, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard, 7, 4, 1, thisMarker)) {
//                setMarkerAndToggle(gameboard, 1, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard, 8, 4, 0, thisMarker)) {
//                setMarkerAndToggle(gameboard, 0, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard, 5, 4, 3, otherMarker)) {
//                setMarkerAndToggle(gameboard, 3, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard, 6, 4, 2, otherMarker)) {
//                setMarkerAndToggle(gameboard, 2, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard, 7, 4, 1, otherMarker)) {
//                setMarkerAndToggle(gameboard, 1, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard, 8, 4, 0, otherMarker)) {
//                setMarkerAndToggle(gameboard, 0, thisMarker);
//            }else if (twoOccupiedOneNotAnd(gameboard, 3, 4, 5, otherMarker)){
//                setMarkerAndToggle(gameboard, 5, thisMarker);
//            }else if (twoOccupiedOneNotAnd(gameboard, 2, 8, 5, otherMarker)){
//                setMarkerAndToggle(gameboard, 5, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard, 6, 8, 7, otherMarker)){
//                setMarkerAndToggle(gameboard, 7, thisMarker);
//            } else if (gameboard.getGrid().get(6).getMarker() == thisMarker) {
//                if (!gameboard.getGrid().get(3).isOccupied()) {
//                    setMarkerAndToggle(gameboard, 3, thisMarker);}
//            } else if (gameboard.getGrid().get(3).getMarker() == thisMarker) {
//                if (gameboard.getGrid().get(4).getMarker() == thisMarker) {
//                    if (!gameboard.getGrid().get(8).isOccupied()) {
//                        setMarkerAndToggle(gameboard, 8, thisMarker);
//                    } else if (!gameboard.getGrid().get(5).isOccupied()) {
//                        setMarkerAndToggle(gameboard, 5, thisMarker);
//                    }
//                }
//            } else if (gameboard.getGrid().get(1).getMarker() == thisMarker) {
//                if (!gameboard.getGrid().get(8).isOccupied()) {
//                    setMarkerAndToggle(gameboard, 8, thisMarker);
//                } else if (!gameboard.getGrid().get(7).isOccupied()) {
//                    setMarkerAndToggle(gameboard, 7, thisMarker);
//                } else if (!gameboard.getGrid().get(5).isOccupied()) {
//                    setMarkerAndToggle(gameboard, 5, thisMarker);
//                }
//            }
//        }
//        // Fifth turn
//        // Checks which square is the last one, and places the marker there
//        if (list.size() == 1) {
//            int j = 0;
//            for (int i = 0; i < gameboard.getGrid().size(); i++) {
//                if (!gameboard.getGrid().get(i).isOccupied()) {
//                    j = gameboard.getGrid().indexOf(gameboard.getGrid().get(i));
//                }
//            }
//            setMarkerAndToggle(gameboard, j, thisMarker);
//        }
//
//        //////////////////////////////////////
//        //        If CPU plays second        //
//        //////////////////////////////////////
//        // First turn
//        if (list.size() == 8) {
//            if(notOccupied(gameboard, 4)){
//                setMarkerAndToggle(gameboard, 4, thisMarker);
//            } else {
//                setMarkerAndToggle(gameboard,0,thisMarker);
//            }
//        }
//        // Second turn
//        if (list.size() == 6) {
//            if (twoOccupiedOneNotAnd(gameboard,5,4,3,otherMarker)) {
//                setMarkerAndToggle(gameboard, 3, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard,2,4,6,otherMarker)) {
//                setMarkerAndToggle(gameboard, 6, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard,1,2,0,otherMarker)) {
//                setMarkerAndToggle(gameboard, 0, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard,6,4,2,otherMarker)) {
//                setMarkerAndToggle(gameboard, 2, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard,7,4,1,otherMarker)) {
//                setMarkerAndToggle(gameboard, 1, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard,8,4,0,otherMarker)) {
//                setMarkerAndToggle(gameboard, 0, thisMarker);
//            }else if (twoOccupiedOneNotAnd(gameboard,0,2,1,otherMarker)) {
//                setMarkerAndToggle(gameboard, 1, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard,0,6,3,otherMarker)) {
//                setMarkerAndToggle(gameboard, 3, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard,6,8,7,otherMarker)) {
//                setMarkerAndToggle(gameboard, 7, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard,2,8,5,otherMarker)) {
//                setMarkerAndToggle(gameboard, 5, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard,7,8,6,otherMarker)) {
//                setMarkerAndToggle(gameboard, 6, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard,6,7,8,otherMarker)) {
//                setMarkerAndToggle(gameboard, 8, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard,0,1,2,otherMarker)) {
//                setMarkerAndToggle(gameboard, 2, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard,0,3,6,otherMarker)) {
//                setMarkerAndToggle(gameboard, 6, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard,1,4,7,otherMarker)) {
//                setMarkerAndToggle(gameboard, 7, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard,0,1,2,otherMarker)) {
//                setMarkerAndToggle(gameboard, 2, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard,2,4,6,otherMarker)) {
//                setMarkerAndToggle(gameboard, 6, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard,3,4,5,otherMarker)) {
//                setMarkerAndToggle(gameboard, 5, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard,5,4,3,otherMarker)) {
//                setMarkerAndToggle(gameboard, 3, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard,6,4,2,otherMarker)) {
//                setMarkerAndToggle(gameboard, 2, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard,7,4,1,otherMarker)) {
//                setMarkerAndToggle(gameboard, 1, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard,8,4,0,otherMarker)) {
//                setMarkerAndToggle(gameboard, 0, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard,0,3,6,otherMarker)) {
//                setMarkerAndToggle(gameboard, 6, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard,2,5,8,otherMarker)) {
//                setMarkerAndToggle(gameboard, 8, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard,6,3,0,otherMarker)) {
//                setMarkerAndToggle(gameboard, 0, thisMarker);
//            }
//
//
//            else if (isOccupiedBy(gameboard, 0, thisMarker) &&
//                    notOccupied(gameboard, 1) &&
//                    notOccupied(gameboard, 2)) {
//                setMarkerAndToggle(gameboard, 2, thisMarker);
//            } else if (isOccupiedBy(gameboard, 0, thisMarker) &&
//                    notOccupied(gameboard, 3) &&
//                    notOccupied(gameboard, 6)) {
//                setMarkerAndToggle(gameboard, 6, thisMarker);
//            }
//
//            else if (!notOccupied(gameboard,0) && notOccupied(gameboard,7)) {
//                setMarkerAndToggle(gameboard, 7, thisMarker);
//            }  else if (!notOccupied(gameboard,1) && notOccupied(gameboard,6)) {
//                setMarkerAndToggle(gameboard, 6, thisMarker);
//            }  else if (!notOccupied(gameboard,2) && notOccupied(gameboard,5)) {
//                setMarkerAndToggle(gameboard, 5, thisMarker);
//            }  else if (!notOccupied(gameboard,5) && notOccupied(gameboard,2)) {
//                setMarkerAndToggle(gameboard, 2, thisMarker);
//            }  else if (!notOccupied(gameboard,8) && notOccupied(gameboard,1)) {
//                setMarkerAndToggle(gameboard, 1, thisMarker);
//            }  else if (!notOccupied(gameboard,7) && notOccupied(gameboard,2)) {
//                setMarkerAndToggle(gameboard, 2, thisMarker);
//            }  else if (!notOccupied(gameboard,6) && notOccupied(gameboard,3)) {
//                setMarkerAndToggle(gameboard, 3, thisMarker);
//            }  else if (!notOccupied(gameboard,3) && notOccupied(gameboard,4)) {
//                setMarkerAndToggle(gameboard, 4, thisMarker);
//
//
//
//            } else if (isOccupiedBy(gameboard, 4, thisMarker) && notOccupied(gameboard, 5)) {
//                setMarkerAndToggle(gameboard, 5, thisMarker);
//            } else if (isOccupiedBy(gameboard, 4, thisMarker) && notOccupied(gameboard, 3)) {
//                setMarkerAndToggle(gameboard, 3, thisMarker);
//            } else if (isOccupiedBy(gameboard, 4, thisMarker) && notOccupied(gameboard, 1)) {
//                setMarkerAndToggle(gameboard, 1, thisMarker);
//            } else if (isOccupiedBy(gameboard, 4, thisMarker) && notOccupied(gameboard, 2)) {
//                setMarkerAndToggle(gameboard, 2, thisMarker);
//            } else if (isOccupiedBy(gameboard, 0, thisMarker) && notOccupied(gameboard, 2)) {
//                setMarkerAndToggle(gameboard, 2, thisMarker);
//            } else if (notOccupied(gameboard, 0)) {
//                setMarkerAndToggle(gameboard, 0, thisMarker);
//            } else {
//                System.out.println("FAIL SECOND"); // Print if there is a scenario that has not been foreseen
//            }
//        }
//        // Third turn
//        if (list.size() == 4) {
//            if (twoOccupiedOneNotAnd(gameboard,0,4,8,thisMarker)) {
//                setMarkerAndToggle(gameboard, 8, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard,1,4,7,thisMarker)) {
//                setMarkerAndToggle(gameboard, 7, thisMarker);
//            }  else if (twoOccupiedOneNotAnd(gameboard,0,2,1,thisMarker)) {
//                setMarkerAndToggle(gameboard, 1, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard,0,1,2,thisMarker)) {
//                setMarkerAndToggle(gameboard, 2, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard,2,4,6,thisMarker)) {
//                setMarkerAndToggle(gameboard, 6, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard,3,4,5,thisMarker)) {
//                setMarkerAndToggle(gameboard, 5, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard,5,4,3,thisMarker)) {
//                setMarkerAndToggle(gameboard, 3, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard,6,4,2,thisMarker)) {
//                setMarkerAndToggle(gameboard, 2, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard,7,4,1,thisMarker)) {
//                setMarkerAndToggle(gameboard, 1, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard,8,4,0,thisMarker)) {
//                setMarkerAndToggle(gameboard, 0, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard,0,3,6,thisMarker)) {
//                setMarkerAndToggle(gameboard, 6, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard,5,4,3,otherMarker)) {
//                setMarkerAndToggle(gameboard, 3, thisMarker);
//            }  else if (twoOccupiedOneNotAnd(gameboard,3,4,5,otherMarker)) {
//                setMarkerAndToggle(gameboard, 3, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard,2,4,6,otherMarker)) {
//                setMarkerAndToggle(gameboard, 6, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard,1,4,7,otherMarker)) {
//                setMarkerAndToggle(gameboard, 7, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard,6,4,2,otherMarker)) {
//                setMarkerAndToggle(gameboard, 2, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard,7,4,1,otherMarker)) {
//                setMarkerAndToggle(gameboard, 1, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard,8,4,0,otherMarker)) {
//                setMarkerAndToggle(gameboard, 0, thisMarker);
//            }else if (twoOccupiedOneNotAnd(gameboard,0,2,1,otherMarker)) {
//                setMarkerAndToggle(gameboard, 1, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard,0,6,3,otherMarker)) {
//                setMarkerAndToggle(gameboard, 3, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard,6,8,7,otherMarker)) {
//                setMarkerAndToggle(gameboard, 7, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard,2,8,5,otherMarker)) {
//                setMarkerAndToggle(gameboard, 5, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard,7,8,6,otherMarker)) {
//                setMarkerAndToggle(gameboard, 6, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard,6,7,8,otherMarker)) {
//                setMarkerAndToggle(gameboard, 8, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard,0,1,2,otherMarker)) {
//                setMarkerAndToggle(gameboard, 2, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard,0,3,6,otherMarker)) {
//                setMarkerAndToggle(gameboard, 6, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard,1,2,0,otherMarker)) {
//                setMarkerAndToggle(gameboard, 0, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard,2,5,8,otherMarker)) {
//                setMarkerAndToggle(gameboard, 8, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard,6,3,0,otherMarker)) {
//                setMarkerAndToggle(gameboard, 0, thisMarker);
//            } else if (isOccupiedBy(gameboard, 4, thisMarker) && notOccupied(gameboard, 5)) {
//                setMarkerAndToggle(gameboard, 5, thisMarker);
//            } else if (isOccupiedBy(gameboard, 4, thisMarker) && notOccupied(gameboard, 3)) {
//                setMarkerAndToggle(gameboard, 3, thisMarker);
//            } else if (isOccupiedBy(gameboard, 4, thisMarker) && notOccupied(gameboard, 1)) {
//                setMarkerAndToggle(gameboard, 1, thisMarker);
//            } else if (isOccupiedBy(gameboard, 4, thisMarker) && notOccupied(gameboard, 2)) {
//                setMarkerAndToggle(gameboard, 2, thisMarker);
//            } else if (isOccupiedBy(gameboard, 0, thisMarker) && notOccupied(gameboard, 2)) {
//                setMarkerAndToggle(gameboard, 2, thisMarker);
//            } else if (notOccupied(gameboard, 0)) {
//                setMarkerAndToggle(gameboard, 0, thisMarker);
//            } else {
//                System.out.println("FAIL THIRD"); // Print if there is a scenario that has not been foreseen
//            }
//        }
//        // Fourth turn
//        if (list.size() == 2) {
//            if(twoOccupiedOneNotAnd(gameboard, 0, 4, 8, thisMarker)){
//                setMarkerAndToggle(gameboard,8,thisMarker);
//            } else if(twoOccupiedOneNotAnd(gameboard, 1, 4, 7, thisMarker)){
//                setMarkerAndToggle(gameboard,7,thisMarker);
//            }  else if(twoOccupiedOneNotAnd(gameboard, 0, 6, 3, thisMarker)){
//                setMarkerAndToggle(gameboard,3,thisMarker);
//            } else if(twoOccupiedOneNotAnd(gameboard, 2, 4, 6, thisMarker)){
//                setMarkerAndToggle(gameboard,6,thisMarker);
//            } else if(twoOccupiedOneNotAnd(gameboard, 3, 4, 5, thisMarker)){
//                setMarkerAndToggle(gameboard,5,thisMarker);
//            } else if(twoOccupiedOneNotAnd(gameboard, 5, 4, 3, thisMarker)){
//                setMarkerAndToggle(gameboard,3,thisMarker);
//            } else if(twoOccupiedOneNotAnd(gameboard, 6, 4, 2, thisMarker)){
//                setMarkerAndToggle(gameboard,2,thisMarker);
//            } else if(twoOccupiedOneNotAnd(gameboard, 7, 4, 1, thisMarker)){
//                setMarkerAndToggle(gameboard,1,thisMarker);
//            } else if(twoOccupiedOneNotAnd(gameboard, 8, 4, 0, thisMarker)){
//                setMarkerAndToggle(gameboard,0,thisMarker);
//            } else if(twoOccupiedOneNotAnd(gameboard, 0, 2, 1, otherMarker)){
//                setMarkerAndToggle(gameboard,1,thisMarker);
//            } else if(twoOccupiedOneNotAnd(gameboard, 0, 6, 3, otherMarker)){
//                setMarkerAndToggle(gameboard,3,thisMarker);
//            } else if(twoOccupiedOneNotAnd(gameboard, 6, 8, 7, otherMarker)){
//                setMarkerAndToggle(gameboard,7,thisMarker);
//            } else if(twoOccupiedOneNotAnd(gameboard, 2, 8, 5, otherMarker)){
//                setMarkerAndToggle(gameboard,5,thisMarker);
//            } else if(twoOccupiedOneNotAnd(gameboard, 7, 8, 6, otherMarker)){
//                setMarkerAndToggle(gameboard,6,thisMarker);
//            } else if(twoOccupiedOneNotAnd(gameboard, 0, 3, 6, otherMarker)){
//                setMarkerAndToggle(gameboard,6,thisMarker);
//            } else if(notOccupied(gameboard, 0)) {
//                setMarkerAndToggle(gameboard,0,thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard, 7, 4, 1, otherMarker)) {
//                setMarkerAndToggle(gameboard, 1, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard, 5, 4, 3, otherMarker)) {
//                setMarkerAndToggle(gameboard, 3, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard, 3, 4, 5, otherMarker)) {
//                setMarkerAndToggle(gameboard, 5, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard, 1, 4, 7, otherMarker)) {
//                setMarkerAndToggle(gameboard, 7, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard, 8, 5, 2, otherMarker)) {
//                setMarkerAndToggle(gameboard, 2, thisMarker);
//            } else if (twoOccupiedOneNotAnd(gameboard, 6, 7, 8, otherMarker)) {
//                setMarkerAndToggle(gameboard, 8, thisMarker);
//            }else {
//                randomPlacement(gameboard, thisMarker);
//                }
//            }
//    }

}