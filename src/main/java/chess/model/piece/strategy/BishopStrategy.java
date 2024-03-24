package chess.model.piece.strategy;

import chess.model.position.Movement;

public class BishopStrategy implements PieceStrategy {
    @Override
    public boolean canMove(Movement movement) {
        return movement.isDiagonal();
    }
}
