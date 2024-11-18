package player;

public class Player {
    private String name;
    private int score;
    private int wins;
    private int losses;
    private String playerType;

    public Player(String name, String playerType) {
        this.name = name;
        this.score = 0;
        this.wins = 0;
        this.losses = 0;
        this.playerType = playerType;
    }


    public void increaseWins() {
        this.wins = this.wins+1;
    }

    public void increaseLosses() {
        this.losses = this.losses+1;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "\nName: " + name +
                "\nPlayer type: " + playerType +
                "\nScore: " + score +
                "\nWins: " + wins +
                "\nLosses: " + losses;
    }
}