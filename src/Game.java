import gameboard.GameBoard;
import player.CpuPlayer;
import player.HumanPlayer;
import player.Player;
import utilities.InputHandler;

public class Game {

    private static Player player1;
    private static Player player2;
    private static Player currentPlayer;
    private static Player otherPlayer;
    private static boolean mainMenu = true;
    private static boolean difficulty = false;
    private static char markerToPlace;

    public Game(){
        while(mainMenu){
            run();
        }
    }

    // Clear the player list and print out the main menu. The rest of the game is launched from the main menu.
    public void run(){
        mainMenu();
        }

    //This handles each round. After a round has finished a prompt appears to decide wether to keep playing or not.
    // If no, return to main menu.
    public void round(){
        boolean game = true;
        while(game) {
            GameBoard gameboard = new GameBoard();
            boolean round = true;
            gameboard.generateGameBoard();
            System.out.println(gameboard.printGameBoard());
            randomizeStartingPlayer();
            while (round) {
                setPlayerMarker(currentPlayer);
                System.out.println(currentPlayer.getName() + ", it is your turn.");
                placeMarker(gameboard);
                System.out.println(gameboard.printGameBoard());
                round = checkState(gameboard);
                switchPlayer();
                if (!round) {
                    printStandings();
                    game = playAgain();
                    difficulty = (game ? difficulty : false); // reset difficulty //TODO Build a better method for handling difficulty in CPUPlayer
                }
            }
        }
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
            if(difficulty){
                ((CpuPlayer) currentPlayer).easyMode(gameboard, currentPlayer.getMarker());
            } else {
                ((CpuPlayer) currentPlayer).hardMode(gameboard, currentPlayer.getMarker(), otherPlayer.getMarker());
            }
        }
    }

    // Check the state of the game, if someone has won or the board is full
    public boolean checkState(GameBoard gameboard){
        boolean running = true;
        if(gameboard.checkIfWin(currentPlayer.getMarker())){
            System.out.println(currentPlayer.getName() + ", wins!");
            currentPlayer.increaseWins();
            otherPlayer.increaseLosses();
            running = false;
        }else if(gameboard.checkIfAllOccupied()){
            System.out.println("No squares left. It's a draw!");
            player1.increaseDraws();
            player2.increaseDraws();
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
            player1 = new HumanPlayer(enterPlayerName("Player 1. "), markerSelection(1));
            player2 = new CpuPlayer("CPU", 'X');
        }
        if(numPlayers == 2){
            player1 = new HumanPlayer(enterPlayerName("Player 1. "), markerSelection(1));
            player2 = new HumanPlayer(enterPlayerName("Player 2. "), markerSelection(2));
        }
    }

    // Let the human player enter their name
    public String enterPlayerName(String string){
        System.out.println(string + "Enter your name: ");
        return InputHandler.getString();
    }

    // Let the player choose their marker
    //TODO add functionality to choose a marker
    public void setPlayerMarker(Player player){
        if(player.equals(player1)){
            markerToPlace = player1.getMarker();
        }
        else if(player.equals(player2)){
            markerToPlace = player2.getMarker();
        }
    }
    // A coin toss to see who starts the game
    public void randomizeStartingPlayer(){
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
        if(currentPlayer == player1){
            currentPlayer = player2;
            otherPlayer = player1;
        }
        else if(currentPlayer == player2){
            currentPlayer = player1;
            otherPlayer = player2;
        }
    }

    //////////////////////////////
    //      "Scoreboard"        //
    //////////////////////////////
    private static void printStandings() {
        System.out.println(
             "-------------------------------------" +
             "\n\t" +  player1.getName()  +  "\t\t\t\t\t" +player2.getName() +
             "\n\t\t\t\t  vs.\t\t" +
            "\n\t\t" + player1.getWins() + "\t\t WINS\t\t" + player2.getWins() +
            "\n\t\t" + player1.getLosses() + "\t\tLOSSES\t\t" + player2.getLosses() +
            "\n\t\t" + player1.getDraws() + "\t\t DRAW  \t\t" + player2.getDraws() +
             "\n-------------------------------------");
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
        int choice = InputHandler.getIntInRange(0,3);
        switch(choice){
            case 1:
                setupPlayers(1);
                difficulty = chooseDifficulty();
                round();
                break;
            case 2:
                setupPlayers(2);
                round();
                break;
            case 3:
                player1 = new CpuPlayer("CPU 1", 'O');
                player2 = new CpuPlayer("CPU 2", 'X');
                round();
                break;
            case 0:
                mainMenu = false;
                break;
        }
    }
    // End of round menu
    public boolean playAgain(){
        System.out.println("Do you want to play again? (Y/N)");
        return InputHandler.getBoolean();
    }

    // Difficulty level
    public boolean chooseDifficulty(){
        System.out.println("Please select difficulty level:");
        System.out.println("1 - Easy");
        System.out.println("2 - Hard");
        int i = InputHandler.getIntInRange(1,2);
        boolean bool = true;
        switch(i){
            case 1:
                bool = true;
                break;
            case 2:
                bool = false;
                break;
        }
        return bool;
    }

    // Marker selection. Lets the player choose their marker. Takes an int as argument to decide which if statement is
    // used. As i have set it up player 1 gets to choose from the first if statement,
    // player 2 from the second. CPU1 and CPU2 has hardcoded markers.
    public char markerSelection(int i){
        char marker = 0;
        int choice;
        System.out.println("Select your marker:");
        if(i == 1){
            System.out.println("1 - O");
            System.out.println("2 - @");
            System.out.println("3 - C");
            choice = InputHandler.getIntInRange(1,3);
            // Apparently this is a way to write a switch statement aswell (IntelliJ suggested it).
            // When it's a simple statement like this, I think it looks a lot cleaner.
            marker = switch (choice) {
                case 1 -> 'O';
                case 2 -> '@';
                case 3 -> 'C';
                default -> marker;
            };
            }
        if(i == 2) {
            System.out.println("1 - X");
            System.out.println("2 - &");
            System.out.println("3 - %");
            choice = InputHandler.getIntInRange(1,3);
            marker = switch (choice) {
                case 1 -> 'X';
                case 2 -> '&';
                case 3 -> '%';
                default -> marker;
            };
            }
        return marker;
    }
}
