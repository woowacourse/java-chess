package chess.domain.piece.strategy;

import chess.domain.square.Square;

public class EmptyStrategy implements MoveStrategy {

    @Override
    public boolean canMove(Square source, Square target) {
        return false;
    }
}
