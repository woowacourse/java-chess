package chess.piece;

public abstract class ChessPiece {

    private final Team team;
    private final Position position;

    public ChessPiece(final Team team, final Position position) {
        this.team = team;
        this.position = position;
    }
}
