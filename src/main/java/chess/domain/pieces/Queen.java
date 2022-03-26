package chess.domain.pieces;

import chess.domain.position.Position;

public class Queen implements Type {

    @Override
    public boolean isMovable(Position source, Position target) {
        return source.isStraight(target) || source.isDiagonal(target);
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public double score() {
        return 9;
    }

    @Override
    public String symbol() {
        return "Q";
    }
}
