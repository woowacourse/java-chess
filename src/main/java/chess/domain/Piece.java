package chess.domain;

public abstract class Piece {
    private final Team team;

    Piece(final Team team) {
        this.team = team;
    }

    public boolean isDifferentTeam(final Piece piece) {
        return this.team != piece.team;
    }

    public String getTeam() {
        return team.toString();
    }
}
