package chess.domain;

public abstract class Piece {

    private Team team;
    private final String name;

    public Piece(Team team, String name) {
        this.team = team;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isBlackTeam() {
        return team == Team.BLACK;
    }
}
