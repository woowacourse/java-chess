package chess.domain.piece.moveStrategy;

import chess.domain.board.Board;
import chess.domain.board.Position;

public interface CanMoveStrategy {
    boolean canMove(Position target, Position destination, Board board);
}
