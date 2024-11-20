import gameboard.GameBoard;
import player.CpuPlayer;
import player.HumanPlayer;
import player.Player;
import utilities.InputHandler;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private static List<Player> players = new ArrayList<>();
    private static Player currentPlayer;
    private static Player otherPlayer;
    private static boolean mainMenu = true;
//   private static boolean game = true;
    char markerToPlace;
    public Game(){
        while(mainMenu){
            players.clear();
            mainMenu();
            boolean game = true;
            while(game){
                GameBoard gameboard = new GameBoard();
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
                        printStandings();
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
//        currentPlayer = players.get(0);
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
            ((CpuPlayer) currentPlayer).placeMarker(gameboard, currentPlayer.getMarker(), otherPlayer.getMarker());
        }
    }


    //      Check the state of the game, if someone has won or the board is full
    public boolean checkState(GameBoard gameboard){
        boolean running = true;
        if(gameboard.checkIfWin(currentPlayer.getMarker())){
            System.out.println(currentPlayer.getName() + ", wins!");
            currentPlayer.increaseWins();
            otherPlayer.increaseLosses();
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
            players.add(new CpuPlayer("CPU", '¤'));
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
            otherPlayer = player2;
        }
        else if(random == 1){
            currentPlayer = player2;
            otherPlayer = player1;
        }
    }

    // Switches the active player. Used at the end of each round.
    public void switchPlayer(){
        if(currentPlayer == players.get(0)){
            currentPlayer = players.get(1);
            otherPlayer = players.get(0);
        }
        else if(currentPlayer == players.get(1)){
            currentPlayer = players.get(0);
            otherPlayer = players.get(1);
        }
    }


    //////////////////////////////
    //      "Scoreboard"        //
    //////////////////////////////
    private static void printStandings() {
        System.out.println(
                   players.get(0).getName()  + " vs. " + players.get(1).getName() +
            "\n" + players.get(0).getWins() + " WINS " + players.get(1).getWins() +
            "\n" + players.get(0).getLosses() + " LOSSES " + players.get(1).getLosses() +
            "\n" + players.get(0).getDraws() + " DRAWS " + players.get(1).getDraws());
    }

    //////////////////////////////
    //          Menus           //
    //////////////////////////////

    // Print out the main menu
    public void mainMenu(){
        System.out.println("Welcome to Tic-Tac-Toe!");
        System.out.println("1 - Human vs CPU");
        System.out.println("2 - Human vs Human");
        System.out.println("3 - CPU vs CPU");
        System.out.println("0 - Quit");
        int input = InputHandler.getIntInRange(0,3);
        switch(input){
            case 1:
                setupPlayers(1);
                break;
            case 2:
                setupPlayers(2);
                break;
            case 3:
                players.add(new CpuPlayer("CPU 1", 'O'));
                players.add(new CpuPlayer("CPU 2", 'X'));
                break;
// TODO           case 0:
//                mainMenu = false;
//                game = false;
//                break;
            default:
                System.out.println("Invalid input. Try again.");
        }
    }

    // End of round menu
    public boolean playAgain(){
        System.out.println("Do you want to play again? (Y/N)");
        return InputHandler.getBoolean();
    }

}
