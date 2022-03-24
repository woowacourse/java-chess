package chess.domain.pieces;

import chess.domain.position.Position;
import chess.domain.position.Row;

public final class Pawn implements Type {

    public static final int DEFAULT_MOVEMENT_LIMIT = 1;
    public static final int VERTICAL_MOVEMENT_LIMIT_ON_START = 2;
    public static final int HORIZONTAL_MOVEMENT_LIMIT = 0;

    @Override
    public String getSymbol() {
        return "P";
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        int rowGap = source.rowGap(target);
        int columnGap = source.columnGap(target);
        return isVerticalOneOrTwoSteps(source, rowGap, columnGap) || isDiagonalOneStep(rowGap, columnGap);
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

    @Override
    public boolean isPawn() {
        return true;
    }
}
