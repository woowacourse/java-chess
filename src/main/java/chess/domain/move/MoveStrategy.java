package chess.domain.move;

import chess.domain.board.Board;
import chess.domain.board.Position;

public abstract class MoveStrategy {

    public abstract boolean isMovable(final Board board, final Position source, final Position target);
}
