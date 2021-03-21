package chess.domain.piece;

import chess.domain.position.Position;

import java.util.List;

public class Pawn extends Division {
    public Pawn(Color color, Position position) {
        super(color, "p", position);
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
        return (getPawnMovementSize(to) == 2) && position.hasRow(initPawnRow());
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
        return getPawnMovementSize(to) == 1;
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
        return 1;
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    private boolean canKill(Position to) {
        return Math.abs(position.diffColumn(to)) == 1 && position.diffRow(to) == color.moveUnit();
    }
}
