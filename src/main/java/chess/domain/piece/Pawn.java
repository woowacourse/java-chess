package chess.domain.piece;

import chess.domain.position.Position;

import java.util.List;

public class Pawn extends Division {
    private static final String PAWN_DISPLAYNAME = "p";
    private static final int PAWN_SCORE = 1;
    private static final int SINGLE_MOVEMENT = 1;
    private static final int DOUBLE_MOVEMENTS = 2;
    public static final String PAWN_MOVE_ERROR = "폰이 이동할 수 없는 위치입니다";

    public Pawn(final Color color, final Position position) {
        super(color, PAWN_DISPLAYNAME, position);
    }

    @Override
    public void moveToEmpty(final Position to, final Pieces pieces) {
        if (canMoveDouble(to)) {
            validateNoneBetween(to, pieces);
            position = to;
            return;
        }
        if (canMoveSingle(to)) {
            position = to;
            return;
        }
        throw new IllegalArgumentException(PAWN_MOVE_ERROR);
    }

    private boolean canMoveDouble(final Position to) {
        return (getPawnMovementSize(to) == DOUBLE_MOVEMENTS) && position.hasRow(initPawnRow());
    }

    private int getPawnMovementSize(final Position to) {
        if (position.diffColumn(to) != 0) {
            throw new IllegalArgumentException();
        }
        return position.diffRow(to) / color.moveUnit();
    }

    private void validateNoneBetween(final Position to, final Pieces pieces) {
        final List<Position> positions = position.getBetween(to);
        if (positions.stream()
                     .anyMatch(pieces::hasPieceOf)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean canMoveSingle(final Position to) {
        return getPawnMovementSize(to) == SINGLE_MOVEMENT;
    }

    @Override
    public void moveForKill(final Position to, final Pieces pieces) {
        if (canKill(to)) {
            position = to;
            return;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public double score() {
        return PAWN_SCORE;
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    private boolean canKill(final Position to) {
        return Math.abs(position.diffColumn(to)) == 1 && position.diffRow(to) == color.moveUnit();
    }
}
