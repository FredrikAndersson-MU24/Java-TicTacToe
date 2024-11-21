package player;

import gameboard.GameBoard;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CpuPlayer extends Player {

    public CpuPlayer(String name, char marker) {
        super(name, "CPU", marker);
    }
    public void easyMode(GameBoard gameboard, char thisMarker) {
        randomPlacement(gameboard, thisMarker);
    }

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


    //////////////////////////////////////////////////////////////////////
    //  Okay, here is my attempt at making a method for the CPUPlayer   //
    //  that tries to win, rather than just randomly placing markers.   //
    //  It's a mess :D                                                  //
    //  But as far as I've managed to test it it seems to work.         //
    //  Strategy from the tic-tac-toe wikipedia page.                   //
    //////////////////////////////////////////////////////////////////////
    public void hardMode(GameBoard gameboard, char thisMarker, char otherMarker) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < gameboard.getGrid().size(); i++) {
            if (!gameboard.getGrid().get(i).isOccupied()) {
                list.add(i);
            }
        }
        //////////////////////////////////////
        //        If CPU plays first        //
        //////////////////////////////////////
        // First turn
        if (list.size() == 9) {
            setMarkerAndToggle(gameboard,0,thisMarker);
        }
        // Second turn
        if (list.size() == 7) {
            if(gameboard.getGrid().get(5).isOccupied()){
                setMarkerAndToggle(gameboard, 4, thisMarker);
            } else if(!gameboard.getGrid().get(1).isOccupied() && !gameboard.getGrid().get(2).isOccupied()) {
                setMarkerAndToggle(gameboard, 2, thisMarker);//
            } else if (gameboard.getGrid().get(1).isOccupied() || gameboard.getGrid().get(2).isOccupied()) {
                setMarkerAndToggle(gameboard, 6, thisMarker);//
            } else if (gameboard.getGrid().get(3).isOccupied() || gameboard.getGrid().get(4).isOccupied() || gameboard.getGrid().get(6).isOccupied()) {
                setMarkerAndToggle(gameboard, 1, thisMarker);
            } else if (gameboard.getGrid().get(5).isOccupied()) {
                setMarkerAndToggle(gameboard, 4, thisMarker);
            } else if (gameboard.getGrid().get(7).isOccupied() || gameboard.getGrid().get(8).isOccupied()) {
                setMarkerAndToggle(gameboard, 2, thisMarker);
            }
        }
        // Third turn
        if (list.size() == 5) {
            if (gameboard.getGrid().get(1).getMarker() == thisMarker && !gameboard.getGrid().get(2).isOccupied()) {
                    setMarkerAndToggle(gameboard, 2, thisMarker);
            }else if (gameboard.getGrid().get(3).getMarker() == thisMarker && !gameboard.getGrid().get(6).isOccupied()) {
                    setMarkerAndToggle(gameboard, 6, thisMarker);
            }else if(twoOccupiedAnd(gameboard, 2, 4, otherMarker)){
                setMarkerAndToggle(gameboard, 6, thisMarker);
            }else if(twoOccupiedAnd(gameboard, 2, 6, otherMarker)){
                setMarkerAndToggle(gameboard, 4, thisMarker);
            } else if(twoOccupiedAnd(gameboard, 5, 8, otherMarker)){
                setMarkerAndToggle(gameboard, 2, thisMarker);
            }else if(twoOccupiedAnd(gameboard, 2, 5, otherMarker)){
                setMarkerAndToggle(gameboard, 8, thisMarker);
            }else if(twoOccupiedAnd(gameboard, 2, 8, otherMarker)){
                setMarkerAndToggle(gameboard, 5, thisMarker);
            } else if (gameboard.getGrid().get(1).getMarker() == thisMarker) {
                if (!gameboard.getGrid().get(2).isOccupied()) {
                    setMarkerAndToggle(gameboard, 2, thisMarker);
                } else {
                    if (!gameboard.getGrid().get(3).isOccupied()) {
                        setMarkerAndToggle(gameboard, 3, thisMarker);
                    } else  if (!gameboard.getGrid().get(4).isOccupied()) {
                        setMarkerAndToggle(gameboard, 4, thisMarker);
                    } else if (!gameboard.getGrid().get(7).isOccupied()) {
                        setMarkerAndToggle(gameboard, 7, thisMarker);
                    } else if (!gameboard.getGrid().get(8).isOccupied()) {
                        setMarkerAndToggle(gameboard, 8, thisMarker);
                    }
                }
            } else if (gameboard.getGrid().get(2).getMarker() == thisMarker) {
                if (!gameboard.getGrid().get(4).isOccupied()) {
                    setMarkerAndToggle(gameboard, 4, thisMarker);
                }
            } else if (gameboard.getGrid().get(3).getMarker() == thisMarker) {
                if (!gameboard.getGrid().get(6).isOccupied()) {
                    setMarkerAndToggle(gameboard, 6, thisMarker);
                } else if (!gameboard.getGrid().get(4).isOccupied()) {
                    setMarkerAndToggle(gameboard, 4, thisMarker);
                } else if (!gameboard.getGrid().get(7).isOccupied()) {
                    setMarkerAndToggle(gameboard, 7, thisMarker);
                }
            }
        }
        // Fourth turn
        if (list.size() == 3) {
            if (twoOccupiedOneNotAnd(gameboard, 0, 4, 8, thisMarker)) {
                setMarkerAndToggle(gameboard, 8, thisMarker);
            } else if (twoOccupiedOneNotAnd(gameboard, 1, 4, 7, thisMarker)) {
                setMarkerAndToggle(gameboard, 7, thisMarker);
            } else if (twoOccupiedOneNotAnd(gameboard, 2, 4, 6, thisMarker)) {
                setMarkerAndToggle(gameboard, 6, thisMarker);
            } else if (twoOccupiedOneNotAnd(gameboard, 3, 4, 5, thisMarker)) {
                setMarkerAndToggle(gameboard, 5, thisMarker);
            } else if (twoOccupiedOneNotAnd(gameboard, 5, 4, 3, thisMarker)) {
                setMarkerAndToggle(gameboard, 3, thisMarker);
            } else if (twoOccupiedOneNotAnd(gameboard, 6, 4, 2, thisMarker)) {
                setMarkerAndToggle(gameboard, 2, thisMarker);
            } else if (twoOccupiedOneNotAnd(gameboard, 7, 4, 1, thisMarker)) {
                setMarkerAndToggle(gameboard, 1, thisMarker);
            } else if (twoOccupiedOneNotAnd(gameboard, 8, 4, 0, thisMarker)) {
                setMarkerAndToggle(gameboard, 0, thisMarker);
            } else if (twoOccupiedOneNotAnd(gameboard, 5, 4, 3, otherMarker)) {
                setMarkerAndToggle(gameboard, 3, thisMarker);
            } else if (twoOccupiedOneNotAnd(gameboard, 6, 4, 2, otherMarker)) {
                setMarkerAndToggle(gameboard, 2, thisMarker);
            } else if (twoOccupiedOneNotAnd(gameboard, 7, 4, 1, otherMarker)) {
                setMarkerAndToggle(gameboard, 1, thisMarker);
            } else if (twoOccupiedOneNotAnd(gameboard, 8, 4, 0, otherMarker)) {
                setMarkerAndToggle(gameboard, 0, thisMarker);
            }else if (twoOccupiedOneNotAnd(gameboard, 3, 4, 5, otherMarker)){
                setMarkerAndToggle(gameboard, 5, thisMarker);
            } else if (twoOccupiedOneNotAnd(gameboard, 6, 8, 7, otherMarker)){
                setMarkerAndToggle(gameboard, 7, thisMarker);
            } else if (gameboard.getGrid().get(6).getMarker() == thisMarker) {
                if (!gameboard.getGrid().get(3).isOccupied()) {
                    setMarkerAndToggle(gameboard, 3, thisMarker);}
            } else if (gameboard.getGrid().get(3).getMarker() == thisMarker) {
                if (gameboard.getGrid().get(4).getMarker() == thisMarker) {
                    if (!gameboard.getGrid().get(8).isOccupied()) {
                        setMarkerAndToggle(gameboard, 8, thisMarker);
                    } else if (!gameboard.getGrid().get(5).isOccupied()) {
                        setMarkerAndToggle(gameboard, 5, thisMarker);
                    }
                }
            } else if (gameboard.getGrid().get(1).getMarker() == thisMarker) {
                if (!gameboard.getGrid().get(8).isOccupied()) {
                    setMarkerAndToggle(gameboard, 8, thisMarker);
                } else if (!gameboard.getGrid().get(7).isOccupied()) {
                    setMarkerAndToggle(gameboard, 7, thisMarker);
                } else if (!gameboard.getGrid().get(5).isOccupied()) {
                    setMarkerAndToggle(gameboard, 5, thisMarker);
                }
            }
        }
        // Fifth turn
        if (list.size() == 1) {
            int j = 0;
            for (int i = 0; i < gameboard.getGrid().size(); i++) {
                if (!gameboard.getGrid().get(i).isOccupied()) {
                    j = gameboard.getGrid().indexOf(gameboard.getGrid().get(i));
                }
            }
            System.out.println(j);
            setMarkerAndToggle(gameboard, j, thisMarker);
        }

        //////////////////////////////////////
        //        If CPU plays second        //
        //////////////////////////////////////
        // First turn
        if (list.size() == 8) {
            if(!isOccupied(gameboard,4)){
                setMarkerAndToggle(gameboard, 4, thisMarker);
            } else {
                setMarkerAndToggle(gameboard,0,thisMarker);
            }
        }
        // Second turn
        if (list.size() == 6) {
            if(twoOccupiedAnd(gameboard, 1, 2, otherMarker)
            || twoOccupiedAnd(gameboard, 3, 6, otherMarker)
            || twoOccupiedOneNotAnd(gameboard, 4, 8, 0, otherMarker)){
                setMarkerAndToggle(gameboard,0,thisMarker);
            } else if(
               twoOccupiedAnd(gameboard, 0, 2, otherMarker)
            || twoOccupiedAnd(gameboard, 0, 8, otherMarker)
            || twoOccupiedAnd(gameboard, 2, 6, otherMarker)
            || twoOccupiedAnd(gameboard, 3, 8, otherMarker)
            || twoOccupiedAnd(gameboard, 4, 7, otherMarker)
            || twoOccupiedAnd(gameboard, 4, 8, otherMarker)
            || twoOccupiedAnd(gameboard, 5, 6, otherMarker)){
                setMarkerAndToggle(gameboard,1,thisMarker);
            }else if(
               twoOccupiedAnd(gameboard, 0, 1, otherMarker)
            || twoOccupiedAnd(gameboard, 1, 3, otherMarker)
            || twoOccupiedAnd(gameboard, 0, 8, otherMarker)
            || twoOccupiedAnd(gameboard, 4, 6, otherMarker)
            || twoOccupiedAnd(gameboard, 4, 8, otherMarker)){
                setMarkerAndToggle(gameboard,2,thisMarker);
            }else if(
               twoOccupiedAnd(gameboard, 0, 6, otherMarker)
            || twoOccupiedAnd(gameboard, 1, 8, otherMarker)
            || twoOccupiedAnd(gameboard, 2, 7, otherMarker)
            || twoOccupiedAnd(gameboard, 4, 5, otherMarker)
            || twoOccupiedAnd(gameboard, 4, 8, otherMarker)){
                setMarkerAndToggle(gameboard,3,thisMarker);
            }else if(
                    twoOccupiedAnd(gameboard, 0, 7, otherMarker)
            || twoOccupiedAnd(gameboard, 1, 6, otherMarker)
            || twoOccupiedAnd(gameboard, 2, 8, otherMarker)
            || twoOccupiedAnd(gameboard, 3, 4, otherMarker)
            || twoOccupiedAnd(gameboard, 4, 8, otherMarker)){
                setMarkerAndToggle(gameboard,5,thisMarker);
            }else if(
                    twoOccupiedAnd(gameboard, 0, 3, otherMarker)
            || twoOccupiedAnd(gameboard, 2, 4, otherMarker)
            || twoOccupiedAnd(gameboard, 7, 8, otherMarker)){
                setMarkerAndToggle(gameboard,6,thisMarker);
            }else if(
                    twoOccupiedAnd(gameboard, 0, 5, otherMarker)
            || twoOccupiedAnd(gameboard, 2, 3, otherMarker)
            || twoOccupiedAnd(gameboard, 1, 4, otherMarker)
            || twoOccupiedAnd(gameboard, 0, 5, otherMarker)
            || twoOccupiedAnd(gameboard, 6, 8, otherMarker)){
                setMarkerAndToggle(gameboard,7,thisMarker);
            } else if(isOccupiedBy(gameboard, 0, thisMarker) && !isOccupiedBy(gameboard, 1,otherMarker)){
                setMarkerAndToggle(gameboard,1,thisMarker);
            } else if(isOccupiedBy(gameboard, 0, thisMarker) && !isOccupiedBy(gameboard, 3,otherMarker)){
                setMarkerAndToggle(gameboard,3,thisMarker);
            } else {
                setMarkerAndToggle(gameboard,8,thisMarker);
            }
        }
        // Third turn
        if (list.size() == 4) {
            if (twoOccupiedOneNotAnd(gameboard,0,4,8,thisMarker)) {
                setMarkerAndToggle(gameboard, 8, thisMarker);
            } else if (twoOccupiedOneNotAnd(gameboard,1,4,7,thisMarker)) {
                setMarkerAndToggle(gameboard, 7, thisMarker);
            } else if (twoOccupiedOneNotAnd(gameboard,2,4,6,thisMarker)) {
                setMarkerAndToggle(gameboard, 6, thisMarker);
            } else if (twoOccupiedOneNotAnd(gameboard,3,4,5,thisMarker)) {
                setMarkerAndToggle(gameboard, 5, thisMarker);
            } else if (twoOccupiedOneNotAnd(gameboard,5,4,3,thisMarker)) {
                setMarkerAndToggle(gameboard, 3, thisMarker);
            } else if (twoOccupiedOneNotAnd(gameboard,6,4,2,thisMarker)) {
                setMarkerAndToggle(gameboard, 2, thisMarker);
            } else if (twoOccupiedOneNotAnd(gameboard,7,4,1,thisMarker)) {
                setMarkerAndToggle(gameboard, 1, thisMarker);
            } else if (twoOccupiedOneNotAnd(gameboard,8,4,0,thisMarker)) {
                setMarkerAndToggle(gameboard, 0, thisMarker);
            } else if (twoOccupiedOneNotAnd(gameboard,5,4,3,otherMarker)) {
                setMarkerAndToggle(gameboard, 3, thisMarker);
            } else if (twoOccupiedOneNotAnd(gameboard,6,4,2,otherMarker)) {
                setMarkerAndToggle(gameboard, 2, thisMarker);
            } else if (twoOccupiedOneNotAnd(gameboard,7,4,1,otherMarker)) {
                setMarkerAndToggle(gameboard, 1, thisMarker);
            } else if (twoOccupiedOneNotAnd(gameboard,8,4,0,otherMarker)) {
                setMarkerAndToggle(gameboard, 0, thisMarker);
            }else if (twoOccupiedOneNotAnd(gameboard,0,2,1,otherMarker)) {
                setMarkerAndToggle(gameboard, 1, thisMarker);
            } else if (twoOccupiedOneNotAnd(gameboard,0,6,3,otherMarker)) {
                setMarkerAndToggle(gameboard, 3, thisMarker);
            } else if (twoOccupiedOneNotAnd(gameboard,6,8,7,otherMarker)) {
                setMarkerAndToggle(gameboard, 7, thisMarker);
            } else if (twoOccupiedOneNotAnd(gameboard,2,8,5,otherMarker)) {
                setMarkerAndToggle(gameboard, 5, thisMarker);
            } else if (twoOccupiedOneNotAnd(gameboard,7,8,6,otherMarker)) {
                setMarkerAndToggle(gameboard, 6, thisMarker);
            } else if (twoOccupiedOneNotAnd(gameboard,6,7,8,otherMarker)) {
                setMarkerAndToggle(gameboard, 8, thisMarker);
            } else if (twoOccupiedOneNotAnd(gameboard,0,1,2,otherMarker)) {
                setMarkerAndToggle(gameboard, 2, thisMarker);
            } else if (twoOccupiedOneNotAnd(gameboard,0,3,6,otherMarker)) {
                setMarkerAndToggle(gameboard, 6, thisMarker);
            } else if (isOccupiedBy(gameboard, 4, thisMarker) && !isOccupied(gameboard, 5)) {
                setMarkerAndToggle(gameboard, 5, thisMarker);
            } else if (isOccupiedBy(gameboard, 4, thisMarker) && !isOccupied(gameboard, 3)) {
                setMarkerAndToggle(gameboard, 3, thisMarker);
            } else if (isOccupiedBy(gameboard, 4, thisMarker) && !isOccupied(gameboard, 1)) {
                setMarkerAndToggle(gameboard, 1, thisMarker);
            } else if (isOccupiedBy(gameboard, 4, thisMarker) && !isOccupied(gameboard, 2)) {
                setMarkerAndToggle(gameboard, 2, thisMarker);
            } else if (isOccupiedBy(gameboard, 0, thisMarker) && !isOccupied(gameboard, 2)) {
                setMarkerAndToggle(gameboard, 2, thisMarker);
            } else if (!isOccupied(gameboard, 0)) {
                setMarkerAndToggle(gameboard, 0, thisMarker);
            } else {
                System.out.println("FAIL THIRD"); // Print if there is a scenario that has not been foreseen
            }
        }
        // Fourth turn
        if (list.size() == 2) {
            if(twoOccupiedOneNotAnd(gameboard, 0, 4, 8, thisMarker)){
                setMarkerAndToggle(gameboard,8,thisMarker);
            } else if(twoOccupiedOneNotAnd(gameboard, 1, 4, 7, thisMarker)){
                setMarkerAndToggle(gameboard,7,thisMarker);
            } else if(twoOccupiedOneNotAnd(gameboard, 2, 4, 6, thisMarker)){
                setMarkerAndToggle(gameboard,6,thisMarker);
            } else if(twoOccupiedOneNotAnd(gameboard, 3, 4, 5, thisMarker)){
                setMarkerAndToggle(gameboard,5,thisMarker);
            } else if(twoOccupiedOneNotAnd(gameboard, 5, 4, 3, thisMarker)){
                setMarkerAndToggle(gameboard,3,thisMarker);
            } else if(twoOccupiedOneNotAnd(gameboard, 6, 4, 2, thisMarker)){
                setMarkerAndToggle(gameboard,2,thisMarker);
            } else if(twoOccupiedOneNotAnd(gameboard, 7, 4, 1, thisMarker)){
                setMarkerAndToggle(gameboard,1,thisMarker);
            } else if(twoOccupiedOneNotAnd(gameboard, 8, 4, 0, thisMarker)){
                setMarkerAndToggle(gameboard,0,thisMarker);
            } else if(twoOccupiedOneNotAnd(gameboard, 0, 2, 1, otherMarker)){
                setMarkerAndToggle(gameboard,1,thisMarker);
            } else if(twoOccupiedOneNotAnd(gameboard, 0, 6, 3, otherMarker)){
                setMarkerAndToggle(gameboard,3,thisMarker);
            } else if(twoOccupiedOneNotAnd(gameboard, 6, 8, 7, otherMarker)){
                setMarkerAndToggle(gameboard,7,thisMarker);
            } else if(twoOccupiedOneNotAnd(gameboard, 2, 8, 5, otherMarker)){
                setMarkerAndToggle(gameboard,5,thisMarker);
            } else if(twoOccupiedOneNotAnd(gameboard, 7, 8, 6, otherMarker)){
                setMarkerAndToggle(gameboard,6,thisMarker);
            } else if(twoOccupiedOneNotAnd(gameboard, 0, 3, 6, otherMarker)){
                setMarkerAndToggle(gameboard,6,thisMarker);
            } else if(!isOccupied(gameboard,0)) {
                setMarkerAndToggle(gameboard,0,thisMarker);
            } else if (twoOccupiedOneNotAnd(gameboard, 7, 4, 1, otherMarker)) {
                setMarkerAndToggle(gameboard, 1, thisMarker);
            } else if (twoOccupiedOneNotAnd(gameboard, 5, 4, 3, otherMarker)) {
                setMarkerAndToggle(gameboard, 3, thisMarker);
            } else if (twoOccupiedOneNotAnd(gameboard, 3, 4, 5, otherMarker)) {
                setMarkerAndToggle(gameboard, 5, thisMarker);
            } else if (twoOccupiedOneNotAnd(gameboard, 1, 4, 7, otherMarker)) {
                setMarkerAndToggle(gameboard, 7, thisMarker);
            } else if (twoOccupiedOneNotAnd(gameboard, 8, 5, 2, otherMarker)) {
                setMarkerAndToggle(gameboard, 2, thisMarker);
            } else if (twoOccupiedOneNotAnd(gameboard, 6, 7, 8, otherMarker)) {
                setMarkerAndToggle(gameboard, 8, thisMarker);
            }else {
                randomPlacement(gameboard, thisMarker);
                }
            }
    }

    public void setMarkerAndToggle(GameBoard gameboard, int num, char marker){
        gameboard.getGrid().get(num).setMarker(marker);
        gameboard.getGrid().get(num).toggleOccupied();
    }

    public boolean isOccupiedBy(GameBoard gameboard, int num, char marker){
        return gameboard.getGrid().get(num).getMarker() == marker;
    }

    public boolean isOccupied(GameBoard gameboard, int num){
        return gameboard.getGrid().get(num).isOccupied();
    }

    public boolean twoOccupiedAnd(GameBoard gameboard, int first, int second, char marker){
        return isOccupiedBy(gameboard, first, marker) && isOccupiedBy(gameboard, second, marker);
    }

    public boolean twoOccupiedOneNotAnd(GameBoard gameboard, int first, int second, int third, char marker){
        return isOccupiedBy(gameboard, first, marker) && isOccupiedBy(gameboard, second, marker) && !isOccupied(gameboard, third);
    }
}
// TODO 23558 chars, 375 line breaks
// TODO 20231 chars