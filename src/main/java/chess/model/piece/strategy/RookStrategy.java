package chess.model.piece.strategy;

import chess.model.position.Movement;

public class RookStrategy implements PieceStrategy {
    @Override
    public boolean canMove(Movement movement) {
        return movement.isSameFileOrRank();
    }
}
