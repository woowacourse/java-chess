package chess.domain.piece.role;

import chess.domain.position.Position;

public final class Rook implements Role {

    private static final int SCORE = 5;
    private static final String RULE = "룩은 수직, 수평 이동만 가능합니다.";

    @Override
    public String getSymbol() {
        return "R";
    }

    @Override
    public void checkMovable(Position source, Position target) {
        if (!(source.isStraight(target))) {
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
