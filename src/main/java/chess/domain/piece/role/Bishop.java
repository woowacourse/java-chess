package chess.domain.piece.role;

import chess.domain.position.Position;

public class Bishop implements Role {

    private static final double SCORE = 3;

    @Override
    public String getSymbol() {
        return "B";
    }

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
        return SCORE;
    }
}
