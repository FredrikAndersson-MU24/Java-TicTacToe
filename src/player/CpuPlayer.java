package player;

import gameboard.GameBoard;
import gameboard.Square;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class CpuPlayer extends Player {

    private boolean isFirst = false;

    public CpuPlayer(String name, char marker) {
        super(name, "CPU", marker);
    }



    ///////////////////////////////////////////////////////////////////
    //  Okay, here I attempted to make a method that tries to win,   //
    //  instead of just randomly placing markers. It's a mess :D     //
    //  But as far as I've managed to test it it seems to work.      //
    ///////////////////////////////////////////////////////////////////




    public void placeMarker(GameBoard gameboard, char thisMarker, char otherMarker) {
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
                setMarkerAndToggle(gameboard, 1, thisMarker);
            } else if (gameboard.getGrid().get(1).isOccupied() || gameboard.getGrid().get(2).isOccupied()) {
                setMarkerAndToggle(gameboard, 3, thisMarker);
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
            }else if(gameboard.getGrid().get(2).getMarker() == otherMarker && gameboard.getGrid().get(4).getMarker() == otherMarker){
                setMarkerAndToggle(gameboard, 6, thisMarker);
            }else if(gameboard.getGrid().get(2).getMarker() == otherMarker && gameboard.getGrid().get(6).getMarker() == otherMarker) {
                setMarkerAndToggle(gameboard, 4, thisMarker);
            } else if(gameboard.getGrid().get(5).getMarker() == otherMarker && gameboard.getGrid().get(8).getMarker() == otherMarker){
                setMarkerAndToggle(gameboard, 2, thisMarker);
            }else if(gameboard.getGrid().get(2).getMarker() == otherMarker && gameboard.getGrid().get(5).getMarker() == otherMarker){
                setMarkerAndToggle(gameboard, 8, thisMarker);
            }else if(gameboard.getGrid().get(2).getMarker() == otherMarker && gameboard.getGrid().get(8).getMarker() == otherMarker){
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
            if (gameboard.getGrid().get(2).getMarker() == otherMarker && gameboard.getGrid().get(4).getMarker() == otherMarker && !gameboard.getGrid().get(6).isOccupied()) {
                setMarkerAndToggle(gameboard, 6, thisMarker);
            } else if (gameboard.getGrid().get(3).getMarker() == otherMarker && gameboard.getGrid().get(4).getMarker() == otherMarker){
                setMarkerAndToggle(gameboard, 5, thisMarker);
            } else if (gameboard.getGrid().get(6).getMarker() == otherMarker && gameboard.getGrid().get(8).getMarker() == otherMarker && !gameboard.getGrid().get(7).isOccupied()){
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
                } else {
                    System.out.println("FAIL");
                }
            } else if (gameboard.getGrid().get(1).getMarker() == thisMarker) {
                if (!gameboard.getGrid().get(8).isOccupied()) {
                    setMarkerAndToggle(gameboard, 8, thisMarker);
                } else if (!gameboard.getGrid().get(7).isOccupied()) {
                    setMarkerAndToggle(gameboard, 7, thisMarker);
                } else if (!gameboard.getGrid().get(5).isOccupied()) {
                    setMarkerAndToggle(gameboard, 5, thisMarker);
                }else {
                    System.out.println("FAIL");
                }
            } else {
                System.out.println("FAIL");
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
            if((
                isOccupiedBy(gameboard, 1, otherMarker) && isOccupiedBy(gameboard, 2,otherMarker))
            || (isOccupiedBy(gameboard, 3, otherMarker) && isOccupiedBy(gameboard, 6,otherMarker))
            || (isOccupiedBy(gameboard, 4, otherMarker) && isOccupiedBy(gameboard, 8,otherMarker) && !isOccupied(gameboard, 0))){
                setMarkerAndToggle(gameboard,0,thisMarker);
            } else if((
                isOccupiedBy(gameboard, 0, otherMarker) && isOccupiedBy(gameboard, 2,otherMarker))
            || (isOccupiedBy(gameboard, 0, otherMarker) && isOccupiedBy(gameboard, 8,otherMarker))
            || (isOccupiedBy(gameboard, 2, otherMarker) && isOccupiedBy(gameboard, 6,otherMarker))
            || (isOccupiedBy(gameboard, 3, otherMarker) && isOccupiedBy(gameboard, 8,otherMarker))
            || (isOccupiedBy(gameboard, 4, otherMarker) && isOccupiedBy(gameboard, 7,otherMarker))
            || (isOccupiedBy(gameboard, 4, otherMarker) && isOccupiedBy(gameboard, 8,otherMarker))
            || (isOccupiedBy(gameboard, 5, otherMarker) && isOccupiedBy(gameboard, 6,otherMarker))){
                setMarkerAndToggle(gameboard,1,thisMarker);
            }else if(
                isOccupiedBy(gameboard, 0, otherMarker) && isOccupiedBy(gameboard, 1,otherMarker)
            || (isOccupiedBy(gameboard, 1, otherMarker) && isOccupiedBy(gameboard, 3,otherMarker))
            || (isOccupiedBy(gameboard, 0, otherMarker) && isOccupiedBy(gameboard, 8,otherMarker))
            || (isOccupiedBy(gameboard, 4, otherMarker) && isOccupiedBy(gameboard, 6,otherMarker))
            || (isOccupiedBy(gameboard, 4, otherMarker) && isOccupiedBy(gameboard, 8,otherMarker))){
                setMarkerAndToggle(gameboard,2,thisMarker);
            }else if(
                isOccupiedBy(gameboard, 0, otherMarker) && isOccupiedBy(gameboard, 6,otherMarker)
            || (isOccupiedBy(gameboard, 1, otherMarker) && isOccupiedBy(gameboard, 8,otherMarker))
            || (isOccupiedBy(gameboard, 2, otherMarker) && isOccupiedBy(gameboard, 7,otherMarker))
            || (isOccupiedBy(gameboard, 4, otherMarker) && isOccupiedBy(gameboard, 5,otherMarker))
            || (isOccupiedBy(gameboard, 4, otherMarker) && isOccupiedBy(gameboard, 8,otherMarker))){
                setMarkerAndToggle(gameboard,3,thisMarker);
            }else if(
                isOccupiedBy(gameboard, 0, otherMarker) && isOccupiedBy(gameboard, 7,otherMarker)
            || (isOccupiedBy(gameboard, 1, otherMarker) && isOccupiedBy(gameboard, 6,otherMarker))
            || (isOccupiedBy(gameboard, 2, otherMarker) && isOccupiedBy(gameboard, 8,otherMarker))
            || (isOccupiedBy(gameboard, 3, otherMarker) && isOccupiedBy(gameboard, 4,otherMarker))
            || (isOccupiedBy(gameboard, 4, otherMarker) && isOccupiedBy(gameboard, 8,otherMarker))){
                setMarkerAndToggle(gameboard,5,thisMarker);
            }else if(
                isOccupiedBy(gameboard, 0, otherMarker) && isOccupiedBy(gameboard, 3,otherMarker)
            || (isOccupiedBy(gameboard, 2, otherMarker) && isOccupiedBy(gameboard, 4,otherMarker))
            || (isOccupiedBy(gameboard, 7, otherMarker) && isOccupiedBy(gameboard, 8,otherMarker))){
                setMarkerAndToggle(gameboard,6,thisMarker);
            }else if(
                isOccupiedBy(gameboard, 0, otherMarker) && isOccupiedBy(gameboard, 5,otherMarker)
            || (isOccupiedBy(gameboard, 2, otherMarker) && isOccupiedBy(gameboard, 3,otherMarker))
            || (isOccupiedBy(gameboard, 1, otherMarker) && isOccupiedBy(gameboard, 4,otherMarker))
            || (isOccupiedBy(gameboard, 0, otherMarker) && isOccupiedBy(gameboard, 5,otherMarker))
            || (isOccupiedBy(gameboard, 6, otherMarker) && isOccupiedBy(gameboard, 8,otherMarker))){
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
            if (isOccupiedBy(gameboard, 0, thisMarker) && isOccupiedBy(gameboard, 4, thisMarker) && !isOccupied(gameboard, 8)) {
                setMarkerAndToggle(gameboard, 8, thisMarker);
            } else if (isOccupiedBy(gameboard, 1, thisMarker) && isOccupiedBy(gameboard, 4, thisMarker) && !isOccupied(gameboard, 7)) {
                setMarkerAndToggle(gameboard, 7, thisMarker);
            } else if (isOccupiedBy(gameboard, 2, thisMarker) && isOccupiedBy(gameboard, 4, thisMarker) && !isOccupied(gameboard, 6)) {
                setMarkerAndToggle(gameboard, 6, thisMarker);
            } else if (isOccupiedBy(gameboard, 3, thisMarker) && isOccupiedBy(gameboard, 4, thisMarker) && !isOccupied(gameboard, 5)) {
                setMarkerAndToggle(gameboard, 5, thisMarker);
            } else if (isOccupiedBy(gameboard, 5, thisMarker) && isOccupiedBy(gameboard, 4, thisMarker) && !isOccupied(gameboard, 3)) {
                setMarkerAndToggle(gameboard, 3, thisMarker);
            } else if (isOccupiedBy(gameboard, 6, thisMarker) && isOccupiedBy(gameboard, 4, thisMarker) && !isOccupied(gameboard, 2)) {
                setMarkerAndToggle(gameboard, 2, thisMarker);
            } else if (isOccupiedBy(gameboard, 7, thisMarker) && isOccupiedBy(gameboard, 4, thisMarker) && !isOccupied(gameboard, 1)) {
                setMarkerAndToggle(gameboard, 1, thisMarker);
            } else if (isOccupiedBy(gameboard, 8, thisMarker) && isOccupiedBy(gameboard, 4, thisMarker) && !isOccupied(gameboard, 0)) {
                setMarkerAndToggle(gameboard, 0, thisMarker);
            } else if (isOccupiedBy(gameboard, 5, otherMarker) && isOccupiedBy(gameboard, 4, otherMarker) && !isOccupied(gameboard, 3)) {
                setMarkerAndToggle(gameboard, 3, thisMarker);
            } else if (isOccupiedBy(gameboard, 6, otherMarker) && isOccupiedBy(gameboard, 4, otherMarker) && !isOccupied(gameboard, 2)) {
                setMarkerAndToggle(gameboard, 2, thisMarker);
            } else if (isOccupiedBy(gameboard, 7, otherMarker) && isOccupiedBy(gameboard, 4, otherMarker) && !isOccupied(gameboard, 1)) {
                setMarkerAndToggle(gameboard, 1, thisMarker);
            } else if (isOccupiedBy(gameboard, 8, otherMarker) && isOccupiedBy(gameboard, 4, otherMarker) && !isOccupied(gameboard, 0)) {
                setMarkerAndToggle(gameboard, 0, thisMarker);
            }else if (isOccupiedBy(gameboard, 0, otherMarker) && isOccupiedBy(gameboard, 2, otherMarker) && !isOccupied(gameboard, 1)) {
                setMarkerAndToggle(gameboard, 1, thisMarker);
            } else if (isOccupiedBy(gameboard, 0, otherMarker) && isOccupiedBy(gameboard, 6, otherMarker) && !isOccupied(gameboard, 3)) {
                setMarkerAndToggle(gameboard, 3, thisMarker);
            } else if (isOccupiedBy(gameboard, 6, otherMarker) && isOccupiedBy(gameboard, 8, otherMarker) && !isOccupied(gameboard, 7)) {
                setMarkerAndToggle(gameboard, 7, thisMarker);
            } else if (isOccupiedBy(gameboard, 2, otherMarker) && isOccupiedBy(gameboard, 8, otherMarker) && !isOccupied(gameboard, 5)) {
                setMarkerAndToggle(gameboard, 5, thisMarker);
            } else if (isOccupiedBy(gameboard, 7, otherMarker) && isOccupiedBy(gameboard, 8, otherMarker) && !isOccupied(gameboard, 6)) {
                setMarkerAndToggle(gameboard, 6, thisMarker);
            } else if (isOccupiedBy(gameboard, 6, otherMarker) && isOccupiedBy(gameboard, 7, otherMarker) && !isOccupied(gameboard, 8)) {
                setMarkerAndToggle(gameboard, 8, thisMarker);
            } else if (isOccupiedBy(gameboard, 4, thisMarker) && !isOccupied(gameboard, 5)) {
                setMarkerAndToggle(gameboard, 5, thisMarker);
            } else if (isOccupiedBy(gameboard, 4, thisMarker) && !isOccupied(gameboard, 3)) {
                setMarkerAndToggle(gameboard, 3, thisMarker);
            }else if (!isOccupied(gameboard, 0)) {
                setMarkerAndToggle(gameboard, 0, thisMarker);
            } else {
                System.out.println("FAIL THIRD");
            }
        }
        // Fourth turn
        if (list.size() == 2) {

            if(isOccupiedBy(gameboard, 0, thisMarker) && isOccupiedBy(gameboard, 4,thisMarker) && !isOccupied(gameboard,8)){
                setMarkerAndToggle(gameboard,8,thisMarker);
            } else if(isOccupiedBy(gameboard, 1, thisMarker) && isOccupiedBy(gameboard, 4,thisMarker) && !isOccupied(gameboard,7)){
                setMarkerAndToggle(gameboard,7,thisMarker);
            } else if(isOccupiedBy(gameboard, 2, thisMarker) && isOccupiedBy(gameboard, 4,thisMarker) && !isOccupied(gameboard,6)){
                setMarkerAndToggle(gameboard,6,thisMarker);
            } else if(isOccupiedBy(gameboard, 3, thisMarker) && isOccupiedBy(gameboard, 4,thisMarker) && !isOccupied(gameboard,5)){
                setMarkerAndToggle(gameboard,5,thisMarker);
            } else if(isOccupiedBy(gameboard, 5, thisMarker) && isOccupiedBy(gameboard, 4,thisMarker) && !isOccupied(gameboard,3)){
                setMarkerAndToggle(gameboard,3,thisMarker);
            } else if(isOccupiedBy(gameboard, 6, thisMarker) && isOccupiedBy(gameboard, 4,thisMarker) && !isOccupied(gameboard,2)){
                setMarkerAndToggle(gameboard,2,thisMarker);
            } else if(isOccupiedBy(gameboard, 7, thisMarker) && isOccupiedBy(gameboard, 4,thisMarker) && !isOccupied(gameboard,1)){
                setMarkerAndToggle(gameboard,1,thisMarker);
            } else if(isOccupiedBy(gameboard, 8, thisMarker) && isOccupiedBy(gameboard, 4,thisMarker) && !isOccupied(gameboard,0)){
                setMarkerAndToggle(gameboard,0,thisMarker);
            } else if(isOccupiedBy(gameboard, 0, otherMarker) && isOccupiedBy(gameboard, 2,otherMarker) && !isOccupied(gameboard,1)){
                setMarkerAndToggle(gameboard,1,thisMarker);
            } else if(isOccupiedBy(gameboard, 0, otherMarker) && isOccupiedBy(gameboard, 6,otherMarker) && !isOccupied(gameboard,3)){
                setMarkerAndToggle(gameboard,3,thisMarker);
            } else if(isOccupiedBy(gameboard, 6, otherMarker) && isOccupiedBy(gameboard, 8,otherMarker) && !isOccupied(gameboard,7)){
                setMarkerAndToggle(gameboard,7,thisMarker);
            } else if(isOccupiedBy(gameboard, 2, otherMarker) && isOccupiedBy(gameboard, 8,otherMarker) && !isOccupied(gameboard,5)){
                setMarkerAndToggle(gameboard,5,thisMarker);
            } else if(isOccupiedBy(gameboard, 7, otherMarker) && isOccupiedBy(gameboard, 8,otherMarker) && !isOccupied(gameboard,6)){
                setMarkerAndToggle(gameboard,6,thisMarker);
            } else if(isOccupiedBy(gameboard, 0, otherMarker) && isOccupiedBy(gameboard, 3,otherMarker) && !isOccupied(gameboard,6)){
                setMarkerAndToggle(gameboard,6,thisMarker);
            } else if(!isOccupied(gameboard,0)) {
                setMarkerAndToggle(gameboard,0,thisMarker);
            } else if (isOccupiedBy(gameboard, 7, otherMarker) && isOccupiedBy(gameboard, 4, otherMarker) && !isOccupied(gameboard, 1)) {
            setMarkerAndToggle(gameboard, 1, thisMarker);
            } else if (isOccupiedBy(gameboard, 4, otherMarker) && isOccupiedBy(gameboard, 4, otherMarker) && !isOccupied(gameboard, 3)) {
                setMarkerAndToggle(gameboard, 3, thisMarker);
            } else if (isOccupiedBy(gameboard, 3, otherMarker) && isOccupiedBy(gameboard, 4, otherMarker) && !isOccupied(gameboard, 5)) {
                setMarkerAndToggle(gameboard, 5, thisMarker);
            } else if (isOccupiedBy(gameboard, 1, otherMarker) && isOccupiedBy(gameboard, 4, otherMarker) && !isOccupied(gameboard, 7)) {
                setMarkerAndToggle(gameboard, 7, thisMarker);
            } else if (isOccupiedBy(gameboard, 5, otherMarker) && isOccupiedBy(gameboard, 8, otherMarker) && !isOccupied(gameboard, 2)) {
                setMarkerAndToggle(gameboard, 2, thisMarker);
            } else {
                List<Integer> remains = new ArrayList<>();
                for (int i = 0; i < gameboard.getGrid().size(); i++) {
                    if (!gameboard.getGrid().get(i).isOccupied()) {
                        remains.add(gameboard.getGrid().indexOf(gameboard.getGrid().get(i)));
                    }
                }
                Random rand = new Random();
                remains.forEach(i-> System.out.println(i));
                setMarkerAndToggle(gameboard, list.get(rand.nextInt(remains.size())), thisMarker);
                System.out.println("FAIL FOURTH");
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


    }








