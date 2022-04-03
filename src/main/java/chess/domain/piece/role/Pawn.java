package chess.domain.piece.role;

import chess.domain.position.Position;
import chess.domain.position.Row;

public final class Pawn implements Role {

    private static final int DEFAULT_MOVEMENT_LIMIT = 1;
    private static final int VERTICAL_MOVEMENT_LIMIT_ON_START = 2;
    private static final int HORIZONTAL_MOVEMENT_LIMIT = 0;
    private static final int SCORE = 1;
    private static final String RULE = "폰은 기본 앞으로 한 칸 움직입니다.\n시작할 때는 두 칸 까지, 상대를 잡을 때는 대각 한 칸도 이동할 수 있습니다.";

    @Override
    public String getSymbol() {
        return "P";
    }

    @Override
    public void checkMovable(Position source, Position target) {
        int rowGap = source.rowGap(target);
        int columnGap = source.columnGap(target);
        if (!(isVerticalOneOrTwoSteps(source, rowGap, columnGap) || isDiagonalOneStep(rowGap, columnGap))) {
            throw new IllegalArgumentException(RULE);
        }
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public double score() {
        return SCORE;
    }

    private boolean isVerticalOneOrTwoSteps(Position source, int rowGap, int columnGap) {
        return isVerticalMove(columnGap) && (isOneStep(rowGap) || isTwoStepOnStart(source, rowGap));
    }

    private boolean isVerticalMove(int columnGap) {
        return columnGap == HORIZONTAL_MOVEMENT_LIMIT;
    }

    private boolean isTwoStepOnStart(Position source, int rowGap) {
        return rowGap == VERTICAL_MOVEMENT_LIMIT_ON_START && checkPawnOnStartPoint(source);
    }

    private boolean isOneStep(int rowGap) {
        return rowGap == DEFAULT_MOVEMENT_LIMIT;
    }

    private boolean isDiagonalOneStep(int rowGap, int columnGap) {
        return rowGap == DEFAULT_MOVEMENT_LIMIT && columnGap == DEFAULT_MOVEMENT_LIMIT;
    }

    private boolean checkPawnOnStartPoint(Position source) {
        return source.isSameRow(Row.TWO) || source.isSameRow(Row.SEVEN);
    }
}
