package model.game;

public class Turn {
    private int count = 1;
    private Color team = Color.WHITE;

    public Turn() {}

    public Turn(int turnCount) {
        this.count = turnCount;
        this.team = Color.turnCountToTeamColor(turnCount);
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