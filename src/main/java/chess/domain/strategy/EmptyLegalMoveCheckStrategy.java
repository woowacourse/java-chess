package chess.domain.strategy;

import chess.domain.board.Board;
import chess.domain.position.Square;

public class EmptyLegalMoveCheckStrategy implements LegalMoveCheckStrategy {

    @Override
    public boolean check(Square source, Square destination, Board board) {
        return false;
    }
}
