package chess.model.piece.strategy;

import chess.model.position.Movement;

public class KingStrategy implements PieceStrategy {
    private static final int MAX_MOVE_DISTANCE = 1;

    @Override
    public boolean canMove(Movement movement) {
        int fileDistance = movement.getFileDistance();
        int rankDistance = movement.getRankDistance();
        return fileDistance <= MAX_MOVE_DISTANCE && rankDistance <= MAX_MOVE_DISTANCE;
    }
}
