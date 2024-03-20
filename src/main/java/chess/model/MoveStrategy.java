package chess.model;

public interface MoveStrategy {
    boolean canMove(ChessPosition source, ChessPosition target);
}
