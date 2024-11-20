package player;

import gameboard.GameBoard;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class CpuPlayer extends Player {

    private boolean isFirst = false;

    public CpuPlayer(String name) {
        super(name, "CPU");
    }

    public void placeMarker(GameBoard gameboard, char marker) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < gameboard.getGrid().size(); i++) {
            if (!gameboard.getGrid().get(i).isOccupied()) {
                list.add(i);
            }
        }

        //////////////////////////////////////
        //        If CPU plays first        //
        //////////////////////////////////////
        // First play
        if (list.size() == 9) {
            setMarkerAndToggle(gameboard,0,marker);

        }
        // Second play
        if (list.size() == 7) {
            if(gameboard.getGrid().get(5).isOccupied()){
                setMarkerAndToggle(gameboard, 4, marker);
            } else if(!gameboard.getGrid().get(1).isOccupied() && !gameboard.getGrid().get(2).isOccupied()) {
                setMarkerAndToggle(gameboard, 1, marker);
            } else if (gameboard.getGrid().get(1).isOccupied() || gameboard.getGrid().get(2).isOccupied()) {
                setMarkerAndToggle(gameboard, 3, marker);
            } else if (gameboard.getGrid().get(3).isOccupied() || gameboard.getGrid().get(4).isOccupied() || gameboard.getGrid().get(6).isOccupied()) {
                setMarkerAndToggle(gameboard, 1, marker);
            } else if (gameboard.getGrid().get(5).isOccupied()) {
                setMarkerAndToggle(gameboard, 4, marker);
            } else if (gameboard.getGrid().get(7).isOccupied() || gameboard.getGrid().get(8).isOccupied()) {
                setMarkerAndToggle(gameboard, 2, marker);

            }
        }
        // Third play
        if (list.size() == 5) {
            if (gameboard.getGrid().get(1).getMarker() == marker && !gameboard.getGrid().get(2).isOccupied()) {
                    setMarkerAndToggle(gameboard, 2, marker);
            }else if (gameboard.getGrid().get(3).getMarker() == marker && !gameboard.getGrid().get(6).isOccupied()) {
                    setMarkerAndToggle(gameboard, 6, marker);
            }else if(gameboard.getGrid().get(2).getMarker() == 'X' && gameboard.getGrid().get(4).getMarker() == 'X'){
                setMarkerAndToggle(gameboard, 6, marker);
            }else if(gameboard.getGrid().get(2).getMarker() == 'X' && gameboard.getGrid().get(6).getMarker() == 'X') {
                setMarkerAndToggle(gameboard, 4, marker);
            } else if(gameboard.getGrid().get(5).getMarker() == 'X' && gameboard.getGrid().get(8).getMarker() == 'X'){
                setMarkerAndToggle(gameboard, 2, marker);
            }else if(gameboard.getGrid().get(2).getMarker() == 'X' && gameboard.getGrid().get(5).getMarker() == 'X'){
                setMarkerAndToggle(gameboard, 8, marker);
            }else if(gameboard.getGrid().get(2).getMarker() == 'X' && gameboard.getGrid().get(8).getMarker() == 'X'){
                setMarkerAndToggle(gameboard, 5, marker);
            } else if (gameboard.getGrid().get(1).getMarker() == marker) {
                if (!gameboard.getGrid().get(2).isOccupied()) {
                    setMarkerAndToggle(gameboard, 2, marker);
                } else {
                    if (!gameboard.getGrid().get(3).isOccupied()) {
                        setMarkerAndToggle(gameboard, 3, marker);
                    } else  if (!gameboard.getGrid().get(4).isOccupied()) {
                        setMarkerAndToggle(gameboard, 4, marker);
                    } else if (!gameboard.getGrid().get(7).isOccupied()) {
                        setMarkerAndToggle(gameboard, 7, marker);
                    } else if (!gameboard.getGrid().get(8).isOccupied()) {
                        setMarkerAndToggle(gameboard, 8, marker);
                    }
                }
            } else if (gameboard.getGrid().get(2).getMarker() == marker) {
                if (!gameboard.getGrid().get(4).isOccupied()) {
                    setMarkerAndToggle(gameboard, 4, marker);
                }
            } else if (gameboard.getGrid().get(3).getMarker() == marker) {
                if (!gameboard.getGrid().get(6).isOccupied()) {
                    setMarkerAndToggle(gameboard, 6, marker);

                } else if (!gameboard.getGrid().get(4).isOccupied()) {
                    setMarkerAndToggle(gameboard, 4, marker);

                } else if (!gameboard.getGrid().get(7).isOccupied()) {
                    setMarkerAndToggle(gameboard, 7, marker);
                }
            }
            }

        // Fourth play
        if (list.size() == 3) {
            if (gameboard.getGrid().get(2).getMarker() == 'X' && gameboard.getGrid().get(4).getMarker() == 'X' && !gameboard.getGrid().get(6).isOccupied()) {
                setMarkerAndToggle(gameboard, 6, marker);
            } else if (gameboard.getGrid().get(3).getMarker() == 'X' && gameboard.getGrid().get(4).getMarker() == 'X'){
                setMarkerAndToggle(gameboard, 5, marker);
            } else if (gameboard.getGrid().get(6).getMarker() == 'X' && gameboard.getGrid().get(8).getMarker() == 'X' && !gameboard.getGrid().get(7).isOccupied()){
                setMarkerAndToggle(gameboard, 7, marker);
            } else if (gameboard.getGrid().get(6).getMarker() == marker) {
                if (!gameboard.getGrid().get(3).isOccupied()) {
                    setMarkerAndToggle(gameboard, 3, marker);}
            } else if (gameboard.getGrid().get(3).getMarker() == marker) {
                if (gameboard.getGrid().get(4).getMarker() == marker) {
                    if (!gameboard.getGrid().get(8).isOccupied()) {
                        setMarkerAndToggle(gameboard, 8, marker);
                    } else if (!gameboard.getGrid().get(5).isOccupied()) {
                        setMarkerAndToggle(gameboard, 5, marker);
                    }
                } else {
                    System.out.println("FAIL");
                }
            } else if (gameboard.getGrid().get(1).getMarker() == marker) {
                if (!gameboard.getGrid().get(8).isOccupied()) {
                    setMarkerAndToggle(gameboard, 8, marker);
                } else if (!gameboard.getGrid().get(7).isOccupied()) {
                    setMarkerAndToggle(gameboard, 7, marker);
                } else if (!gameboard.getGrid().get(5).isOccupied()) {
                    setMarkerAndToggle(gameboard, 5, marker);
                }else {
                    System.out.println("FAIL");
                }
            } else {
                System.out.println("FAIL");
            }

        }
        // Fifth play
        if (list.size() == 1) {
            int j = 0;
            for (int i = 0; i < gameboard.getGrid().size(); i++) {
                if (!gameboard.getGrid().get(i).isOccupied()) {
                    j = gameboard.getGrid().indexOf(gameboard.getGrid().get(i));
                }
            }
            System.out.println(j);
            setMarkerAndToggle(gameboard, j, marker);
        }




        //////////////////////////////////////
        //        If CPU plays second        //
        //////////////////////////////////////
        // First play
        if (list.size() == 8) {
            if(!isOccupied(gameboard,4)){
                setMarkerAndToggle(gameboard, 4, marker);
            } else {
                setMarkerAndToggle(gameboard,0,marker);
            }
        }
        // Second play
        if (list.size() == 6) {
            if((
                isOccupiedBy(gameboard, 1, 'X') && isOccupiedBy(gameboard, 2,'X'))
            || (isOccupiedBy(gameboard, 3, 'X') && isOccupiedBy(gameboard, 6,'X'))
            || (isOccupiedBy(gameboard, 4, 'X') && isOccupiedBy(gameboard, 9,'X'))){
                setMarkerAndToggle(gameboard,0,marker);
            } else if((
                isOccupiedBy(gameboard, 0, 'X') && isOccupiedBy(gameboard, 2,'X'))
            || (isOccupiedBy(gameboard, 0, 'X') && isOccupiedBy(gameboard, 8,'X'))
            || (isOccupiedBy(gameboard, 2, 'X') && isOccupiedBy(gameboard, 6,'X'))
            || (isOccupiedBy(gameboard, 3, 'X') && isOccupiedBy(gameboard, 8,'X'))
            || (isOccupiedBy(gameboard, 4, 'X') && isOccupiedBy(gameboard, 7,'X'))
            || (isOccupiedBy(gameboard, 4, 'X') && isOccupiedBy(gameboard, 8,'X'))
            || (isOccupiedBy(gameboard, 5, 'X') && isOccupiedBy(gameboard, 6,'X'))){
                setMarkerAndToggle(gameboard,1,marker);
            }else if(
                isOccupiedBy(gameboard, 0, 'X') && isOccupiedBy(gameboard, 1,'X')
            || (isOccupiedBy(gameboard, 1, 'X') && isOccupiedBy(gameboard, 3,'X'))
            || (isOccupiedBy(gameboard, 0, 'X') && isOccupiedBy(gameboard, 8,'X'))
            || (isOccupiedBy(gameboard, 4, 'X') && isOccupiedBy(gameboard, 6,'X'))
            || (isOccupiedBy(gameboard, 4, 'X') && isOccupiedBy(gameboard, 8,'X'))){
                setMarkerAndToggle(gameboard,2,marker);
            }else if(
                isOccupiedBy(gameboard, 0, 'X') && isOccupiedBy(gameboard, 6,'X')
            || (isOccupiedBy(gameboard, 1, 'X') && isOccupiedBy(gameboard, 8,'X'))
            || (isOccupiedBy(gameboard, 2, 'X') && isOccupiedBy(gameboard, 7,'X'))
            || (isOccupiedBy(gameboard, 4, 'X') && isOccupiedBy(gameboard, 5,'X'))
            || (isOccupiedBy(gameboard, 4, 'X') && isOccupiedBy(gameboard, 8,'X'))){
                setMarkerAndToggle(gameboard,3,marker);
            }else if(
                isOccupiedBy(gameboard, 0, 'X') && isOccupiedBy(gameboard, 7,'X')
            || (isOccupiedBy(gameboard, 1, 'X') && isOccupiedBy(gameboard, 6,'X'))
            || (isOccupiedBy(gameboard, 2, 'X') && isOccupiedBy(gameboard, 8,'X'))
            || (isOccupiedBy(gameboard, 3, 'X') && isOccupiedBy(gameboard, 4,'X'))
            || (isOccupiedBy(gameboard, 4, 'X') && isOccupiedBy(gameboard, 8,'X'))){
                setMarkerAndToggle(gameboard,5,marker);
            }else if(
                isOccupiedBy(gameboard, 0, 'X') && isOccupiedBy(gameboard, 3,'X')
            || (isOccupiedBy(gameboard, 2, 'X') && isOccupiedBy(gameboard, 4,'X'))
            || (isOccupiedBy(gameboard, 7, 'X') && isOccupiedBy(gameboard, 8,'X'))){
                setMarkerAndToggle(gameboard,6,marker);
            }else if(
                isOccupiedBy(gameboard, 0, 'X') && isOccupiedBy(gameboard, 5,'X')
            || (isOccupiedBy(gameboard, 2, 'X') && isOccupiedBy(gameboard, 3,'X'))
            || (isOccupiedBy(gameboard, 1, 'X') && isOccupiedBy(gameboard, 4,'X'))
            || (isOccupiedBy(gameboard, 0, 'X') && isOccupiedBy(gameboard, 5,'X'))
            || (isOccupiedBy(gameboard, 6, 'X') && isOccupiedBy(gameboard, 8,'X'))){
                setMarkerAndToggle(gameboard,7,marker);
            }else {
                setMarkerAndToggle(gameboard,8,marker);
            }
        }
        // Third play
        if (list.size() == 4) {
            if (isOccupiedBy(gameboard, 0, marker) && isOccupiedBy(gameboard, 4, marker) && !isOccupied(gameboard, 8)) {
                setMarkerAndToggle(gameboard, 8, marker);
            } else if (isOccupiedBy(gameboard, 1, marker) && isOccupiedBy(gameboard, 4, marker) && !isOccupied(gameboard, 7)) {
                setMarkerAndToggle(gameboard, 7, marker);
            } else if (isOccupiedBy(gameboard, 2, marker) && isOccupiedBy(gameboard, 4, marker) && !isOccupied(gameboard, 6)) {
                setMarkerAndToggle(gameboard, 6, marker);
            } else if (isOccupiedBy(gameboard, 3, marker) && isOccupiedBy(gameboard, 4, marker) && !isOccupied(gameboard, 5)) {
                setMarkerAndToggle(gameboard, 5, marker);
            } else if (isOccupiedBy(gameboard, 5, marker) && isOccupiedBy(gameboard, 4, marker) && !isOccupied(gameboard, 3)) {
                setMarkerAndToggle(gameboard, 3, marker);
            } else if (isOccupiedBy(gameboard, 6, marker) && isOccupiedBy(gameboard, 4, marker) && !isOccupied(gameboard, 2)) {
                setMarkerAndToggle(gameboard, 2, marker);
            } else if (isOccupiedBy(gameboard, 7, marker) && isOccupiedBy(gameboard, 4, marker) && !isOccupied(gameboard, 1)) {
                setMarkerAndToggle(gameboard, 1, marker);
            } else if (isOccupiedBy(gameboard, 8, marker) && isOccupiedBy(gameboard, 4, marker) && !isOccupied(gameboard, 0)) {
                setMarkerAndToggle(gameboard, 0, marker);
            } else if (isOccupiedBy(gameboard, 0, 'X') && isOccupiedBy(gameboard, 2, 'X') && !isOccupied(gameboard, 1)) {
                setMarkerAndToggle(gameboard, 1, marker);
            } else if (isOccupiedBy(gameboard, 0, 'X') && isOccupiedBy(gameboard, 6, 'X') && !isOccupied(gameboard, 3)) {
                setMarkerAndToggle(gameboard, 3, marker);
            } else if (isOccupiedBy(gameboard, 6, 'X') && isOccupiedBy(gameboard, 8, 'X') && !isOccupied(gameboard, 7)) {
                setMarkerAndToggle(gameboard, 7, marker);
            } else if (isOccupiedBy(gameboard, 2, 'X') && isOccupiedBy(gameboard, 8, 'X') && !isOccupied(gameboard, 5)) {
                setMarkerAndToggle(gameboard, 5, marker);
            } else if (isOccupiedBy(gameboard, 7, 'X') && isOccupiedBy(gameboard, 8, 'X') && !isOccupied(gameboard, 6)) {
                setMarkerAndToggle(gameboard, 6, marker);
            } else if (isOccupiedBy(gameboard, 4, marker) && !isOccupied(gameboard, 5)) {
                setMarkerAndToggle(gameboard, 5, marker);
            } else if (isOccupiedBy(gameboard, 4, marker) && !isOccupied(gameboard, 3)) {
                setMarkerAndToggle(gameboard, 3, marker);
            }else if (!isOccupied(gameboard, 0)) {
                setMarkerAndToggle(gameboard, 0, marker);
            } else {
                System.out.println("FAIL THIRD");
            }
        }
        // Fourth play
        if (list.size() == 2) {

            if(isOccupiedBy(gameboard, 0, marker) && isOccupiedBy(gameboard, 4,marker) && !isOccupied(gameboard,8)){
                setMarkerAndToggle(gameboard,8,marker);
            } else if(isOccupiedBy(gameboard, 1, marker) && isOccupiedBy(gameboard, 4,marker) && !isOccupied(gameboard,7)){
                setMarkerAndToggle(gameboard,7,marker);
            } else if(isOccupiedBy(gameboard, 2, marker) && isOccupiedBy(gameboard, 4,marker) && !isOccupied(gameboard,6)){
                setMarkerAndToggle(gameboard,6,marker);
            } else if(isOccupiedBy(gameboard, 3, marker) && isOccupiedBy(gameboard, 4,marker) && !isOccupied(gameboard,5)){
                setMarkerAndToggle(gameboard,5,marker);
            } else if(isOccupiedBy(gameboard, 5, marker) && isOccupiedBy(gameboard, 4,marker) && !isOccupied(gameboard,3)){
                setMarkerAndToggle(gameboard,3,marker);
            } else if(isOccupiedBy(gameboard, 6, marker) && isOccupiedBy(gameboard, 4,marker) && !isOccupied(gameboard,2)){
                setMarkerAndToggle(gameboard,2,marker);
            } else if(isOccupiedBy(gameboard, 7, marker) && isOccupiedBy(gameboard, 4,marker) && !isOccupied(gameboard,1)){
                setMarkerAndToggle(gameboard,1,marker);
            } else if(isOccupiedBy(gameboard, 8, marker) && isOccupiedBy(gameboard, 4,marker) && !isOccupied(gameboard,0)){
                setMarkerAndToggle(gameboard,0,marker);
            } else if(isOccupiedBy(gameboard, 0, 'X') && isOccupiedBy(gameboard, 2,'X') && !isOccupied(gameboard,1)){
                setMarkerAndToggle(gameboard,1,marker);
            } else if(isOccupiedBy(gameboard, 0, 'X') && isOccupiedBy(gameboard, 6,'X') && !isOccupied(gameboard,3)){
                setMarkerAndToggle(gameboard,3,marker);
            } else if(isOccupiedBy(gameboard, 6, 'X') && isOccupiedBy(gameboard, 8,'X') && !isOccupied(gameboard,7)){
                setMarkerAndToggle(gameboard,7,marker);
            } else if(isOccupiedBy(gameboard, 2, 'X') && isOccupiedBy(gameboard, 8,'X') && !isOccupied(gameboard,5)){
                setMarkerAndToggle(gameboard,5,marker);
            } else if(isOccupiedBy(gameboard, 7, 'X') && isOccupiedBy(gameboard, 8,'X') && !isOccupied(gameboard,6)){
                setMarkerAndToggle(gameboard,6,marker);
            } else if(isOccupiedBy(gameboard, 0, 'X') && isOccupiedBy(gameboard, 3,'X') && !isOccupied(gameboard,6)){
                setMarkerAndToggle(gameboard,6,marker);
            } else if(!isOccupied(gameboard,0)) {
                setMarkerAndToggle(gameboard,0,marker);
            } else {
                List<Integer> remains = new ArrayList<>();
                for (int i = 0; i < gameboard.getGrid().size(); i++) {
                    if (!gameboard.getGrid().get(i).isOccupied()) {
                        remains.add(gameboard.getGrid().indexOf(gameboard.getGrid().get(i)));
                    }
                    Random rand = new Random();
                    System.out.println(remains.size());
                    setMarkerAndToggle(gameboard, rand.nextInt(remains.size()), marker);
                    System.out.println("FAIL FOURTH");
                }
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








