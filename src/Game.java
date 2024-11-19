import GameBoard.GameBoard;
import player.HumanPlayer;
import player.Player;
import utilities.InputHandler;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private static List<Player> players = new ArrayList<>();
    private static Player currentPlayer;
    char markerToPlace;
    public Game(){

        initPlayers();
        while(true){
            GameBoard gameboard = new GameBoard();
            System.out.println(players.get(0).getName() + " vs. " + players.get(1).getName());
            boolean running = true;
            initGame(gameboard);
            while(running){
                setPlayerMarker(currentPlayer);
                System.out.println(currentPlayer.getName() + ", it is your turn.");
                placeMarker(gameboard);
                System.out.println(gameboard.printGameBoardSquares());
                running = checkState(gameboard);
                switchPlayer();
            }
        }

    }


    public void initGame(GameBoard gameboard){
        gameboard.generateGameBoardSquares();
        System.out.println(gameboard.printGameBoardSquares());
        randomizePlayer(players.get(0), players.get(1));
    }

    public void initPlayers(){
        players.add(new HumanPlayer("Fredrik"));
        players.add(new HumanPlayer("Alice"));
    }

    public void randomizePlayer(Player player1, Player player2){
        int random = InputHandler.getRandomIntInRange(0,1);
        if(random == 0){
            currentPlayer = player1;
        }
        else if(random == 1){
            currentPlayer = player2;
        }
    }

    public void switchPlayer(){
        if(currentPlayer == players.get(0)){
            currentPlayer = players.get(1);
        }
        else if(currentPlayer == players.get(1)){
            currentPlayer = players.get(0);
        }
    }

    public void inputMarkerPlacement(){
        System.out.println("Where do you want to place your marker?");
        int input = InputHandler.getIntInRange(1,9);
        System.out.println(input);
    }

    public void setPlayerMarker(Player player){
        if(player.equals(players.get(0))){
            markerToPlace = 'X';
        }
        else if(player.equals(players.get(1))){
            markerToPlace = '¤';
        }
    }

    public void placeMarker(GameBoard gameboard){
        int placement;
        while(true){
            placement = InputHandler.getMarkerPlacementAsInt();
            if(!isOccupied(gameboard, placement)){
                break;
            }
            System.out.println("Square already occupied");
        }
            gameboard.getSquares().get(placement-1).setMarker(markerToPlace);
            gameboard.getSquares().get(placement-1).setOccupied(true);
            System.out.println(gameboard.getSquares().get(placement-1).getMarker());
            System.out.println(gameboard.printGameBoardSquares());
            System.out.println(gameboard.getSquares().get(placement-1).isOccupied());

    }

    public boolean isOccupied(GameBoard gameboard, int placement){
        return gameboard.getSquares().get(placement-1).isOccupied();
    }

    public boolean checkSquaresLeft(GameBoard gameboard){
        return gameboard.checkIfAllOccupied();
    }

    public boolean checkState(GameBoard gameboard){
        boolean running = true;
        if(checkSquaresLeft(gameboard)){
            System.out.println("No squares left. It's a draw!");
            players.forEach(player -> player.increaseDraws());
            running = false;
        }
        if(gameboard.checkIfWin(markerToPlace)){
            System.out.println(currentPlayer.getName() + ", wins!");
            currentPlayer.increaseWins();
            running = false;
        }
        return running;
    }


}
