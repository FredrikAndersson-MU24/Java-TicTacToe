import GameBoard.GameBoard;
import player.CpuPlayer;
import player.HumanPlayer;
import player.Player;
import utilities.InputHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {

    private static List<Player> players = new ArrayList<>();
    private static Player currentPlayer;
    char markerToPlace;
    public Game(){
        boolean menuLevel = true;
        while(menuLevel){
            mainMenu();
            boolean game = true;
            while(game){
                GameBoard gameboard = new GameBoard();
                printStandings();
                boolean round = true;
                initGame(gameboard);
                while(round){
                    setPlayerMarker(currentPlayer);
                    System.out.println(currentPlayer.getName() + ", it is your turn.");
                    placeMarker(gameboard);
                    System.out.println(gameboard.printGameBoard());
                    round = checkState(gameboard);
                    switchPlayer();
                    if(!round){
                        game = playAgain();
                    }
                }
            }
        }
    }


    // Initiates the most basic parts to get the game upp and running.
    public void initGame(GameBoard gameboard){
        gameboard.generateGameBoard();
        System.out.println(gameboard.printGameBoard());
        randomizePlayer(players.get(0), players.get(1));
    }


    // Handle the player placing their marker. Check if the square is occupied and place the marker if it is not.
    public void placeMarker(GameBoard gameboard){

        if (currentPlayer instanceof HumanPlayer){
            int placement;
            while(true){
                placement = InputHandler.getMarkerPlacementAsInt();
                if(!gameboard.getGrid().get(placement-1).isOccupied()){
                    break;
                }
                System.out.println("Square already occupied");
            }
            gameboard.getGrid().get(placement-1).setMarker(markerToPlace);
            gameboard.getGrid().get(placement-1).toggleOccupied();
        }
        if(currentPlayer instanceof CpuPlayer){

            List<Integer> list = new ArrayList<>();
            Random rand = new Random();
            for(int i = 0; i < gameboard.getGrid().size(); i++){
                if (!gameboard.getGrid().get(i).isOccupied()){
                    list.add(i);
                }
            }
            int placement = list.get(rand.nextInt(list.size()));
            gameboard.getGrid().get(placement).setMarker(markerToPlace);
            gameboard.getGrid().get(placement).toggleOccupied();
            list.clear();

        }


    }


    //      Check the state of the game, if someone has won or the board is full
    public boolean checkState(GameBoard gameboard){
        boolean running = true;
        if(gameboard.checkIfWin(markerToPlace)){
            System.out.println(currentPlayer.getName() + ", wins!");
            currentPlayer.increaseWins();
            running = false;
        }else if(gameboard.checkIfAllOccupied()){
            System.out.println("No squares left. It's a draw!");
            players.forEach(player -> player.increaseDraws());
            running = false;
        }
        return running;
    }

    //////////////////////////////////////
    //         Player related           //
    //////////////////////////////////////

    // Determines if there are one or two human players
    public void setupPlayers(int numPlayers){
        if(numPlayers == 1){
            enterPlayerName("Player 1. ");
            players.add(new CpuPlayer("CPU"));
        }
        if(numPlayers == 2){
            enterPlayerName("Player 1. ");
            enterPlayerName("Player 2. ");
        }
    }


    // Let the player enter their name
    public void enterPlayerName(String string){
        System.out.println(string + "Enter your name: ");
        players.add(new HumanPlayer(InputHandler.getString()));
    }

    // Let the player choose their marker
    //TODO add functionality to choose a marker
    public void setPlayerMarker(Player player){
        if(player.equals(players.get(0))){
            markerToPlace = 'X';
        }
        else if(player.equals(players.get(1))){
            markerToPlace = '¤';
        }
    }

    // A coin toss to see who starts the game
    public void randomizePlayer(Player player1, Player player2){
        int random = InputHandler.getRandomIntInRange(0,1);
        if(random == 0){
            currentPlayer = player1;
        }
        else if(random == 1){
            currentPlayer = player2;
        }
    }

    // Switches the active player. Used at the end of each round.
    public void switchPlayer(){
        if(currentPlayer == players.get(0)){
            currentPlayer = players.get(1);
        }
        else if(currentPlayer == players.get(1)){
            currentPlayer = players.get(0);
        }
    }


    //////////////////////////////
    //      "Scoreboard"        //
    //////////////////////////////
    private static void printStandings() {
        System.out.println(players.get(0).getName() + " " + players.get(0).getWins() + " vs. "
                + players.get(1).getName() + " " + players.get(1).getWins());
    }

    //////////////////////////////
    //          Menus           //
    //////////////////////////////

    // Print out the main menu
    public void mainMenu(){
        System.out.println("Welcome to Tic-Tac-Toe!");
        System.out.println("1 - Human vs CPU");
        System.out.println("2 - Human vs Human");
        int input = InputHandler.getIntInRange(1,2);
        switch(input){
            case 1:
                setupPlayers(1);
                break;
            case 2:
                setupPlayers(2);
                break;
            default:
                System.out.println("Invalid input. Try again.");
        }
    }

    // End of round menu
    public boolean playAgain(){
        System.out.println("Do you want to play again? (Y/N)");
        return InputHandler.getBoolean();
    }

// TODO Create a better player setup, that works if there are more than two players playing since last start of the application


}
