package chess;

public abstract class Piece {

    private final Team team;

    public Piece(Team team) {
        this.team = team;
    }

    public final boolean isBlackTeam() {
        return team.isBlack();
    }
}
