package chess.domain.piece;

import chess.domain.piece.state.Pieces;
import chess.domain.position.Position;

public interface CanNotMoveStrategy {
    boolean canNotMove(Position from, Position to, Pieces pieces);
}
