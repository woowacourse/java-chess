package chess.domain.piece.kind;

import java.util.Objects;

import chess.domain.board.Point;
import chess.domain.piece.Color;
import chess.domain.piece.Direction;
import chess.domain.piece.Name;

public abstract class Piece {
    public static final String IMPOSSIBLE_ROUTE_ERROR_MESSAGE = "기물이 이동할 수 없는 경로입니다.";
    protected static final int MOVE_STRAIGHT_ONE_SQUARE = 1;
    protected static final int MOVE_DIAGONAL_ONE_SQUARE = 2;

    protected final Name name;
    protected final Color color;
    protected String image;

    public Piece(String name, Color color) {
        this.name = new Name(name, color);
        this.color = color;
        this.image = "";
    }

    public abstract double score();

    public final boolean isSameTeam(Color color) {
        return color.isSameAs(this.color);
    }

    public final boolean isIncorrectTurn(Color color) {
        return !color.isSameAs(this.color);
    }

    public final void validateRoute(Point source, Point target, Piece targetPiece) {
        checkCorrectDistance(source, target, targetPiece);
        checkCorrectDirection(Direction.findDirection(source, target));
    }

    public void checkCorrectDistance(Point source, Point target, Piece targetPiece) {}

    public void checkCorrectDirection(Direction direction) {}

    public boolean isKnight() {
        return false;
    }

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

    public String getImage() {
        return image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Piece piece = (Piece)o;
        return Objects.equals(name, piece.name) && color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, color);
    }
}
