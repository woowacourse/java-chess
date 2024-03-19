package chess.domain;

public abstract class Piece {
    private final Position position;
    private final Team team;

    public Piece(Position position, Team team) {
        this.position = position;
        this.team = team;
    }
}
