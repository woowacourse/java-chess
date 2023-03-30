package chess.strategy;

import chess.domain.board.Position;

public class KingStrategy implements MoveStrategy{

    private static final int KING_MOVE_DISTANCE = 1;
    @Override
    public boolean isMovable(Position source, Position target) {
        return source.getDistanceTo(target) == KING_MOVE_DISTANCE;
    }
}
