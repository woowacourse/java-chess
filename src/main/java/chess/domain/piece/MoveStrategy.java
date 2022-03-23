package chess.domain.piece;

import chess.domain.board.Position;

public interface MoveStrategy {
    boolean canMove(Position src, Position dest);
}
