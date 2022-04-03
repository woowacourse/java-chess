package chess.domain.piece.role;

import chess.domain.position.Position;

public final class Bishop implements Role {

    private static final double SCORE = 3;
    private static final String RULE = "비숍은 대각선 이동만 가능합니다.";

    @Override
    public String getSymbol() {
        return "B";
    }

    @Override
    public void checkMovable(Position source, Position target) {
        if (!source.isDiagonal(target)) {
            throw new IllegalArgumentException(RULE);
        }
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
