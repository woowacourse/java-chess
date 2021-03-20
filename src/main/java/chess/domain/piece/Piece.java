package chess.domain.piece;

import chess.domain.grid.Lines;
import chess.domain.position.Direction;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {
    private Position position;
    private final boolean isBlack;

    public Piece(final boolean isBlack, final char x, final char y) {
        this.position = new Position(x, y);
        this.isBlack = isBlack;
    }

    public boolean isBlack() {
        return isBlack;
    }

    public Position position() {
        return position;
    }

    public void moveTo(final Position position) {
        this.position = position;
    }

    public void validateRoute(Piece targetPiece, Lines lines) {
        validateSourcePieceEmpty();
        validatePositionInGrid(targetPiece);
        validateTargetPiece(targetPiece);
        validateSteps(targetPiece, lines);
    }

    private void validateSourcePieceEmpty() {
        if (isEmpty()) {
            throw new IllegalArgumentException("빈 공간을 옮길 수 없습니다.");
        }
    }

    public boolean isEmpty() {
        return this instanceof Empty;
    }

    private void validatePositionInGrid(final Piece targetPiece) {
        Position source = this.position();
        Position target = targetPiece.position();
        if (!source.isInGridRange() || !target.isInGridRange()) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
    }

    private void validateTargetPiece(final Piece targetPiece) {
        if (!targetPiece.isEmpty() && isSameColor(targetPiece)) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
    }

    private boolean isSameColor(final Piece other) {
        return this.isBlack == other.isBlack;
    }

    void validateSteps(Piece targetPiece, Lines lines) {
        List<Position> movablePositions = new ArrayList<>();
        for (Direction direction : directions()) {
            movablePositions.addAll(route(direction, stepRange(), lines));
        }
        targetPiece.validateTargetInMovablePositions(movablePositions);
    }

    public void validateTargetInMovablePositions(final List<Position> movablePositions) {
        if (!movablePositions.contains(this.position())) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
    }

    public List<Position> route(final Direction direction, final int stepRange, Lines lines) {
        List<Position> positions = new ArrayList<>();
        Position sourcePosition = position();

        for (int i = 1; i <= stepRange; i++) {
            Position movedPosition = sourcePosition.next(direction.getXDegree() * i, direction.getYDegree() * i);
            if (!movedPosition.isInGridRange()) {
                break;
            }
            positions.add(movedPosition);
            if (!lines.isEmpty(movedPosition)) {
                break;
            }
        }
        return positions;
    }


    public abstract List<Direction> directions();

    public abstract int stepRange();

    public abstract char name();

    public abstract double score();
}