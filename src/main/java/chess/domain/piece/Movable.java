package chess.domain.piece;

@FunctionalInterface
public interface Movable {
    Move move = new Move();

    boolean canMove(final Position source, final Position target);
}
