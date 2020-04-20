package chess.domain.piece.policy.move;

import chess.domain.piece.PiecesState;
import chess.domain.piece.position.Position;

public interface CanNotMoveStrategy {
    boolean canNotMove(Position from, Position to, PiecesState piecesState);
}
