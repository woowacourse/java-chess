package chess.piece;

public abstract class ChessPiece {

    private final Team team;

    public ChessPiece(final Team team) {
        this.team = team;
    }
}
