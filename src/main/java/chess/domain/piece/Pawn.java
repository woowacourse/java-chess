package chess.domain.piece;

import chess.domain.position.Position;

import java.util.List;

public class Pawn extends Division {
    private static final String PAWN_DISPLAYNAME = "p";
    private static final int PAWN_SCORE = 1;
    private static final int SINGLE_MOVEMENT = 1;
    private static final int DOUBLE_MOVEMENTS = 2;

    public Pawn(Color color, Position position) {
        super(color, PAWN_DISPLAYNAME, position);
    }

    @Override
    public void moveToEmpty(Position to, Pieces pieces) {
        if (canMoveDouble(to)) {
            validateNoneBetween(to, pieces);
            position = to;
            return;
        }
        if (canMoveSingle(to)) {
            position = to;
            return;
        }
        throw new IllegalArgumentException();
    }

    private boolean canMoveDouble(Position to) {
        return (getPawnMovementSize(to) == DOUBLE_MOVEMENTS) && position.hasRow(initPawnRow());
    }

    private int getPawnMovementSize(Position to) {
        if (position.diffColumn(to) != 0) {
            throw new IllegalArgumentException();
        }
        return position.diffRow(to) / color.moveUnit();
    }

    private void validateNoneBetween(Position to, Pieces pieces) {
        List<Position> positions = position.getBetween(to);
        if (positions.stream()
                     .anyMatch(pieces::hasPieceOf)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean canMoveSingle(Position to) {
        return getPawnMovementSize(to) == SINGLE_MOVEMENT;
    }

    @Override
    public void moveForKill(Position to, Pieces pieces) {
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

    private boolean canKill(Position to) {
        return Math.abs(position.diffColumn(to)) == 1 && position.diffRow(to) == color.moveUnit();
    }
}
