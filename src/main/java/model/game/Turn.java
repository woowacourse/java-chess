package model.game;

public class Turn {
    private int count = 1;
    private Player team = Player.WHITE;

    public Turn() {}

    public Turn(int turnCount) {
        this.count = turnCount;
        resetTeam();
    }

    private Player resetTeam() {
        this.team = (this.count % 2 == 0) ? Player.WHITE : Player.BLACK;
        return this.team;
    }

    public Turn endTurn() {
        this.team = this.team.toggle();
        this.count++;
        return this;
    }

    public Player team() {
        return this.team;
    }

    public int count() {
        return this.count;
    }
}