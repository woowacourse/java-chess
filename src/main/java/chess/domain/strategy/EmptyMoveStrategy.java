package chess.domain.strategy;

import chess.domain.board.Board;
import chess.domain.position.Square;

public class EmptyMoveStrategy implements  MoveStrategy {

    @Override
    public boolean check(Square source, Square destination, Board board) {
        return false;
    }
}
