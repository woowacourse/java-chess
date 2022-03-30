package chess.domain.move;

import chess.domain.board.Board;
import chess.domain.board.Position;

public interface MoveStrategy {

    boolean isMovable(final Board board, final Position source, final Position target);
}
