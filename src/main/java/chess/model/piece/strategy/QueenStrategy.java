package chess.model.piece.strategy;

import chess.model.position.Movement;

public class QueenStrategy implements PieceStrategy {
    @Override
    public boolean canMove(Movement movement) {
        return movement.isDiagonal() || movement.isSameFileOrRank();
    }
}
