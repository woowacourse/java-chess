package chess.domain.piece.strategy;

import chess.domain.board.Position;
import chess.domain.exceptions.OverDistanceException;

public class KingMoveStrategy extends BasicMoveStrategy {

    private static final int KING_DISTANCE_LIMIT = 1;

    @Override
    void checkValidMove(Position source, Position target) {
        checkValidDistance(source, target);
    }

    private void checkValidDistance(Position source, Position target) {
        if (Math.abs(source.computeHorizontalDistance(target)) > KING_DISTANCE_LIMIT ||
            Math.abs(source.computeVerticalDistance(target)) > KING_DISTANCE_LIMIT) {
            throw new OverDistanceException();
        }
    }
}
