package domain.piece.objects;

import domain.piece.Color;
import domain.piece.Direction;
import domain.piece.Name;
import domain.piece.Position;
import domain.score.Score;

import java.util.Map;
import java.util.Objects;

public abstract class Piece {
    private final Name name;
    private final Score score;
    private final Color color;

    public abstract boolean canMove(Map<Position, Piece> board, Position start, Position end);

    public Piece(String name, Score score, boolean isBlack) {
        this.name = Name.of(name, isBlack);
        this.score = score;
        this.color = Color.of(isBlack);
    }

    public Score getScore() {
        return score;
    }

    public String getName() {
        return name.getName();
    }

    public boolean isSameColor(boolean value) {
        return color.equals(Color.of(value));
    }

    public boolean isSameColor(Piece piece) {
        return color.equals(piece.color);
    }

    protected boolean isDiagonal(Position start, Position end) {
        int[] diff = Position.makeDiff(start, end);
        int rowDiff = Math.abs(diff[0]);
        int colDiff = Math.abs(diff[1]);
        return (rowDiff != 0 && colDiff != 0) && rowDiff == colDiff;
    }

    public boolean isLinear(Position start, Position end) {
        int[] diff = Position.makeDiff(start, end);
        int rowDiff = Math.abs(diff[0]);
        int colDiff = Math.abs(diff[1]);
        return (rowDiff == 0) || (colDiff == 0);
    }

    protected boolean isEmptyPosition(Map<Position, Piece> board, Position nextPosition) {
        return !board.containsKey(nextPosition);
    }

    protected Direction getLinearDirection(Position start, Position end) {
        int[] diff = Position.makeDiff(start, end);
        Direction direction = Direction.findLinearDirection(diff[0], diff[1]);
        return direction;
    }

    protected Direction getDiagonalDirection(Position start, Position end) {
        int[] diff = Position.makeDiff(start, end);
        Direction direction = Direction.findDiagonalDirection(diff[0], diff[1]);
        return direction;
    }

    public boolean isKingDead() {
        return this instanceof King;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return color == piece.color && Objects.equals(name, piece.name) && Objects.equals(score, piece.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, score, color);
    }
}
