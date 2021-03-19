package chess.domain.piece.strategy;

import chess.domain.position.Position;

public class BishopStrategy implements MoveStrategy {

    @Override
    public boolean canGoFrom(Position from, Position to) {
        return from.isDiagonal(to);
    }
}
