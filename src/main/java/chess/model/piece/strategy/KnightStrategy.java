package chess.model.piece.strategy;

import chess.model.position.Movement;

public class KnightStrategy implements PieceStrategy {
    private static final int LONG_MOVE_DISTANCE = 2;
    private static final int SHORT_MOVE_DISTANCE = 1;

    @Override
    public boolean canMove(Movement movement) {
        int fileDistance = movement.getFileDistance();
        int rankDistance = movement.getRankDistance();
        return (fileDistance == LONG_MOVE_DISTANCE && rankDistance == SHORT_MOVE_DISTANCE)
                || (fileDistance == SHORT_MOVE_DISTANCE && rankDistance == LONG_MOVE_DISTANCE);
    }
}
