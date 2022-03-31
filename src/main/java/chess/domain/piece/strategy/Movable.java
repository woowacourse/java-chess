package chess.domain.piece.strategy;

import chess.domain.board.Board;
import chess.domain.position.Position;

public interface Movable {

    boolean isPawnMoving(final Board board, final Position from, final Position to);
}
