package chess.domain.pieces;

import chess.domain.position.Position;
import chess.domain.position.Row;

public final class Pawn implements Type {

    private static final int DEFAULT_MOVEMENT_LIMIT = 1;
    private static final int VERTICAL_MOVEMENT_LIMIT_ON_START = 2;
    private static final int HORIZONTAL_MOVEMENT_LIMIT = 0;

    @Override
    public boolean isMovable(final Position source, final Position target) {
        final int rowGap = source.rowGap(target);
        final int columnGap = source.columnGap(target);
        return isVerticalOneOrTwoSteps(source, rowGap, columnGap) || isDiagonalOneStep(rowGap, columnGap);
    }

    private boolean isVerticalOneOrTwoSteps(final Position source, final int rowGap, final int columnGap) {
        return isVerticalMove(columnGap) && (isOneStep(rowGap) || isTwoStepOnStart(source, rowGap));
    }

    private boolean isVerticalMove(final int columnGap) {
        return columnGap == HORIZONTAL_MOVEMENT_LIMIT;
    }

    private boolean isTwoStepOnStart(final Position source, final int rowGap) {
        return rowGap == VERTICAL_MOVEMENT_LIMIT_ON_START && checkPawnOnStartPoint(source);
    }

    private boolean isOneStep(final int rowGap) {
        return rowGap == DEFAULT_MOVEMENT_LIMIT;
    }

    private boolean isDiagonalOneStep(final int rowGap, final int columnGap) {
        return rowGap == DEFAULT_MOVEMENT_LIMIT && columnGap == DEFAULT_MOVEMENT_LIMIT;
    }

    private boolean checkPawnOnStartPoint(final Position source) {
        return source.isSameRow(Row.TWO) || source.isSameRow(Row.SEVEN);
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
        return Symbol.PAWN.score();
    }

    @Override
    public Symbol symbol() {
        return Symbol.PAWN;
    }
}
