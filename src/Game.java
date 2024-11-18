import player.HumanPlayer;
import player.Player;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private static List<Player> players = new ArrayList<>();

    public Game(){

        initPlayers();
        System.out.println(players);



    }

    public void initPlayers(){
        players.add(new HumanPlayer("Fredrik"));
        players.add(new HumanPlayer("Alice"));
    }


}
