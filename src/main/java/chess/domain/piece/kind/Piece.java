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

    public Piece(String name, Color color) {
        this.name = new Name(name, color);
        this.color = color;
    }

    public abstract void validateMovableRoute(Point source, Point target, Piece targetPiece);

    public final boolean isSameTeam(Color color) {
        return color.isSameAs(this.color);
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

    public final void validateCorrectTurn(Color currentColor) {
        if (isIncorrectTurn(currentColor)) {
            throw new IllegalArgumentException("기물의 색이 일치하지 않아 움직일 수 없는 기물입니다.");
        }
    }

    private boolean isIncorrectTurn(Color currentColor) {
        return !currentColor.isSameAs(this.color);
    }

    protected final void validateTargetPieceColor(Piece targetPiece) {
        if (targetPiece.isSameTeam(this.color)) {
            throw new IllegalArgumentException("도착지에 아군이 존재합니다.");
        }
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
                color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, color);
    }
}
