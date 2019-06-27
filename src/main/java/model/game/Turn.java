package model.game;

public class Turn {
    private int count = 1;
    private Color team = Color.WHITE;

    public Turn() {}

    public Turn(int turnCount) {
        this.count = turnCount;
        resetTeam();
    }

    private Color resetTeam() {
        this.team = (this.count % 2 == 0) ? Color.WHITE : Color.BLACK;
        return this.team;
    }

    public Turn endTurn() {
        this.team = this.team.toggle();
        this.count++;
        return this;
    }

    public Color team() {
        return this.team;
    }

    public int count() {
        return this.count;
    }
}