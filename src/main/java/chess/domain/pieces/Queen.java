package chess.domain.pieces;

import chess.domain.position.Position;

public class Queen implements Type {

    @Override
    public String getSymbol() {
        return "Q";
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        return source.isStraight(target) || source.isDiagonal(target);
    }

    @Override
    public boolean isPawn() {
        return false;
    }
}
