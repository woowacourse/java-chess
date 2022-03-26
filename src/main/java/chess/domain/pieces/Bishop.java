package chess.domain.pieces;

import chess.domain.position.Position;

public class Bishop implements Type {

    @Override
    public boolean isMovable(Position source, Position target) {
        return source.isDiagonal(target);
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
        return 3;
    }

    @Override
    public String symbol() {
        return "B";
    }
}
