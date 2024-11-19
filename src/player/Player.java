package player;

public class Player {
    private String name;
    private int wins;
    private int losses;
    private int draws;
    private String playerType;

    public Player(String name, String playerType) {
        this.name = name;
        this.wins = 0;
        this.losses = 0;
        this.draws = 0;
        this.playerType = playerType;
    }


    public void increaseWins() {
        this.wins = this.wins+1;
    }

    public void increaseLosses() {
        this.losses = this.losses+1;
    }

    public void increaseDraws(){
        this.draws = this.draws+1;
    }

    public String getName() {
        return name;
    }

    public int getWins() {
        return wins;
    }

    @Override
    public String toString() {
        return "\nName: " + name +
                "\nPlayer type: " + playerType +
                "\nWins: " + wins +
                "\nLosses: " + losses +
                "\nDraws: " + draws;
    }
}
