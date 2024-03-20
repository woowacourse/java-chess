package chess.model;

public interface MoveStrategy {
    boolean canMove(ChessPosition target);
}
