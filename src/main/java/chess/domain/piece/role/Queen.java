package chess.domain.piece.role;

import chess.domain.position.Position;

public final class Queen implements Role {

    private static final int SCORE = 9;

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

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public double score() {
        return SCORE;
    }
}
