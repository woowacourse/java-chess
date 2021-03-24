package chess.domain.piece.kind;

import chess.domain.Point;
import chess.domain.piece.Color;
import chess.domain.piece.Direction;
import chess.domain.piece.Name;

import java.util.Objects;

public abstract class Piece {
    protected static final int MOVE_STRAIGHT_ONE_SQUARE = 1;
    protected static final int MOVE_DIAGONAL_ONE_SQUARE = 2;

    protected final Name name;
    protected final Color color;
    protected Point point;

    public Piece(String name, Color color, Point point) {
        this.name = new Name(name, color);
        this.color = color;
        this.point = point;
    }

    public abstract void validateMovable(Direction direction, Piece targetPiece);

    public abstract Point moveOneStep(Point target, Direction direction);

    public final boolean isSameTeam(Color color) {
        return color.isSameAs(this.color);
    }

    public final boolean isIncorrectTurn(Color color) {
        return !color.isSameAs(this.color);
    }

    public final void movePoint(Point target) {
        this.point = target;
    }

    public abstract double score();

    public boolean isEmptyPiece() {
        return false;
    }

    public boolean isKing() {
        return false;
    }

    public boolean isPawn() {
        return false;
    }

    public final String getName() {
        return name.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return Objects.equals(name, piece.name) &&
                color == piece.color &&
                Objects.equals(point, piece.point);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, color, point);
    }
}
