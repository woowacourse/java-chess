package chess.domain.piece.strategy;

import chess.domain.position.Position;

public class EmptyStrategy implements MoveStrategy{

    @Override
    public boolean canMove(Position from, Position to) {
        return false;
    }
}
