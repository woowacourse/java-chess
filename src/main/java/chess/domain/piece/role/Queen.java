package chess.domain.piece.role;

import chess.domain.position.Position;

public final class Queen implements Role {

    private static final int SCORE = 9;
    private static final String RULE = "퀸은 상하좌우 대각선으로 이동할 수 있습니다.";

    @Override
    public String getSymbol() {
        return "Q";
    }

    @Override
    public void checkMovable(Position source, Position target) {
        if (!(source.isStraight(target) || source.isDiagonal(target))) {
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
