package chess.domain.piece;

import chess.domain.piece.state.PiecesState;
import chess.domain.position.Position;

public interface CanNotMoveStrategy {
    boolean canNotMove(Position from, Position to, PiecesState piecesState);
}
