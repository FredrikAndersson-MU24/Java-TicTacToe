import gameboard.GameBoard;
import player.CpuPlayer;
import player.HumanPlayer;
import player.Player;
import player.PlayerService;
import utilities.InputHandler;

public class Game {

    private static Player player1;
    private static Player player2;
    private static Player currentPlayer;
    private static Player otherPlayer;
    private static boolean mainMenu = true;

    public Game(){
        while(mainMenu){
            mainMenu();
        }
    }

    //This handles each round. After a round has finished a prompt appears to decide whether to keep playing or not.
    // If no, return to main menu. (Maybe this should have been placed in GameBoard, or a GameBoardService? Since
    // it mostly invoke gameboard methods.)
    public void round(){
        boolean game = true;
        while(game) {
            GameBoard gameboard = new GameBoard();
            boolean round = true;
            gameboard.generateGameBoard();
            gameboard.printGameBoard();
            randomizeStartingPlayer();
            while (round) {
                PlayerService.placeMarker(gameboard, currentPlayer, otherPlayer);
                gameboard.printGameBoard();
                round = checkState(gameboard);
                switchPlayer();
                if (!round) {
                    printStandings();
                    game = playAgain();
                }
            }
        }
    }

    ////////////////////////////////////////////////
    //          Menus and active choices          //
    ////////////////////////////////////////////////

    // Print out the main menu
    public void mainMenu(){
        System.out.println("Welcome to Tic-Tac-Toe!");
        System.out.println("1 - Human vs CPU");
        System.out.println("2 - Human vs Human");
        System.out.println("3 - CPU vs CPU");
        System.out.println("4 - Rules");
        System.out.println("0 - Quit");
        int choice = InputHandler.getIntInRange(0,4);
        switch(choice){
            case 1:
                player1 = new HumanPlayer(PlayerService.inputName("Player 1. "), PlayerService.markerSelection(1));
                player2 = new CpuPlayer("CPU", 'X', PlayerService.chooseDifficulty());
                round();
                break;
            case 2:
                player1 = new HumanPlayer(PlayerService.inputName("Player 1. "), PlayerService.markerSelection(1));
                player2 = new HumanPlayer(PlayerService.inputName("Player 2. "), PlayerService.markerSelection(2));
                round();
                break;
            // This was intended for testing purposes only, to work out kinks in the hardMode method, but I kept it to show the
            // difference between the "difficulty" levels if you put Easy vs Hard for example.
            case 3:
                player1 = new CpuPlayer("CPU 1", 'O', PlayerService.chooseDifficulty());
                player2 = new CpuPlayer("CPU 2", 'X', PlayerService.chooseDifficulty());
                round();
                break;
            case 4:
                rules();
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

    //////////////////////////////////
    //          "Scoreboard"        //
    // Prints at the end of a game  //
    // Had a hard time to align each//
    // "column". Settled for this.  //
    //////////////////////////////////
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

    /////////////////////////////////
    //          Misc.              //
    /////////////////////////////////

    // A "coin toss" to see who starts the game
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

    // Check the state of the game, if someone has won or the board is full it returns false
    public boolean checkState(GameBoard gameboard){
        boolean running = true;
        if(gameboard.checkIfWin(currentPlayer.getMarker())){
            System.out.println(currentPlayer.getName() + " wins!");
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


    // Rules
    public void rules(){
        System.out.println(
                "Rules\n" +
                "Thank you for playing Tic-Tac-Toe!\n" +
                "In Tic-Tac-Toe two players face off against each other. " +
                "\nThe aim of the game is to get three of your markers in a row,\n" +
                "and at the same time make sure your opponent does not get three in a row.\n" +
                "\nThe game board is made up of a 3x3 grid of squares. Each square can be \n" +
                "populated by one marker. Once a marker has been placed, it can not be changed.\n" +
                "At the start of a round a coin toss determines which player starts placing \n" +
                "their marker. The players then take turns placing markers until one of them\n" +
                "gets three markers in a row or there are no more empty squares on the board. \n" +
                "Once there are three identical markers in a row on a horizontal, vertical or \n" +
                "diagonal line, that player wins and the round is finished. \n" +
                "If neither player gets three in a row before the board is filled, the game \n" +
                "ends in a draw.\n" +
                "You can challenge a friend or play against the computer. If you decide to \n" +
                "try to beat the computer, you can pick between two skill levels to face.\n" +
                "\nPlacing your marker is done by entering a number from 1 to 9. As shown in \n" +
                "the schematic below:" +
                "\n-------------" +
                "\n| 1 | 2 | 3 |" +
                "\n----+---+----" +
                "\n| 4 | 5 | 6 |" +
                "\n----+---+----" +
                "\n| 7 | 8 | 9 |" +
                "\n-------------" +
                "\n\n0 - Return to main menu");
        InputHandler.getIntInRange(0,0);
    }

}