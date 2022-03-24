package chess.domain.move;

import chess.domain.board.Board;
import chess.domain.board.Position;

public class KnightMoveStrategy implements MoveStrategy {

    @Override
    public boolean isMovable(final Board board, final Position source, final Position target) {
        return true;
    }
}
