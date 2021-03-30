package chess.domain.piece;

import chess.domain.grid.Column;
import chess.domain.grid.Lines;
import chess.domain.grid.Row;
import chess.domain.position.Direction;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Piece {
    private final Color color;
    private Position position;

    public Piece(final Color color, final char x, final char y) {
        this(color, Column.column(x), Row.row(y));
    }

    public Piece(final Color color, final char x, final int y) {
        this(color, Column.column(x), Row.row(y));
    }

    public Piece(final Color color, final Column column, final int y) {
        this(color, column, Row.row(y));
    }

    public Piece(final Color color, final Position position) {
        this(color, position.column(), position.row());
    }

    public Piece(final Color color, final Column x, final Row y) {
        this.position = new Position(x, y);
        this.color = color;
    }

    public final Position position() {
        return position;
    }

    public final void moveTo(final Position position) {
        this.position = position;
    }

    public final boolean isEmpty() {
        return this instanceof Empty;
    }

    public Color color() {
        return color;
    }

    public int rowDifference(final Piece other) {
        return this.position.rowDifference(other.position);
    }

    public int columnDifference(final Piece other) {
        return this.position.columnDifference(other.position);
    }

    public final List<Position> route(final Direction direction, final int stepRange, Lines lines) {
        List<Position> positions = new ArrayList<>();
        Position sourcePosition = position();

        for (int i = 1; i <= stepRange; i++) {
            if (!sourcePosition.isValidNext(direction.getXDegree() * i, direction.getYDegree() * i)) {
                break;
            }
            Position movedPosition = sourcePosition.next(direction.getXDegree() * i, direction.getYDegree() * i);
            positions.add(movedPosition);
            if (!lines.isEmpty(movedPosition)) {
                break;
            }
        }
        return positions;
    }

    public final void validateRoute(final Piece targetPiece, final Lines lines) {
        validateSourcePieceEmpty();
        validateTargetPiece(targetPiece);
        validateSteps(targetPiece, lines);
    }

    private void validateSourcePieceEmpty() {
        if (isEmpty()) {
            throw new IllegalArgumentException("빈 공간을 옮길 수 없습니다.");
        }
    }

    private void validateTargetPiece(final Piece targetPiece) {
        if (!targetPiece.isEmpty() && isSameColor(targetPiece)) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
    }

    void validateSteps(final Piece targetPiece, final Lines lines) {
        List<Position> movablePositions = new ArrayList<>();
        for (Direction direction : directions()) {
            movablePositions.addAll(route(direction, stepRange(), lines));
        }
        targetPiece.validateTargetInMovablePositions(movablePositions);
    }

    public final void validateTargetInMovablePositions(final List<Position> movablePositions) {
        if (!movablePositions.contains(this.position())) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
    }

    private boolean isSameColor(final Piece other) {
        return this.color == other.color;
    }

    public abstract List<Direction> directions();

    public abstract int stepRange();

    public abstract char name();

    public abstract double score();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return Objects.equals(position, piece.position) && color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, color);
    }
}