package chess.domain.piece.role;

import chess.domain.position.Position;

public final class King implements Role {

    private static final int MOVEMENT_LIMIT = 1;
    private static final double SCORE = 0;
    private static final String RULE = "킹은 방향 관계없이 한 칸만 이동할 수 있습니다.";

    @Override
    public String getSymbol() {
        return "K";
    }

    @Override
    public void checkMovable(Position source, Position target) {
        if (!isAdjacent(source, target)) {
            throw new IllegalArgumentException(RULE);
        }
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    public double score() {
        return SCORE;
    }

    private boolean isAdjacent(Position source, Position target) {
        return source.columnGap(target) <= MOVEMENT_LIMIT && source.rowGap(target) <= MOVEMENT_LIMIT;
    }
}
