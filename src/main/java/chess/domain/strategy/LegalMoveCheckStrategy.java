package chess.domain.strategy;

import chess.domain.board.Board;
import chess.domain.position.Square;

public interface LegalMoveCheckStrategy {

    boolean check(Square source, Square destination, Board board);
}
