package chess.domain;

public interface Piece {
    boolean isValidMove(final Position origin, final Position target);
}
