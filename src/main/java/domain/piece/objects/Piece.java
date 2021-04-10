package domain.piece.objects;

import domain.piece.Color;
import domain.piece.Name;
import domain.piece.position.Direction;
import domain.piece.position.Position;
import domain.score.Score;

import java.util.Objects;

public abstract class Piece {
    private final Name name;
    private final Score score;
    private final Color color;

    public abstract boolean existPath();

    public abstract void checkMovable(Position start, Position end, boolean turn);

    public Piece(String name, Score score, boolean isBlack) {
        this.name = Name.of(name, isBlack);
        this.score = score;
        this.color = Color.of(isBlack);
    }

    public Score getScore() {
        return score;
    }

    public String name() {
        return name.getName();
    }

    public boolean isSameColor(boolean value) {
        return color.equals(Color.of(value));
    }

    public boolean isSameColor(Piece piece) {
        return color.equals(piece.color);
    }

    public boolean isKing() {
        return false;
    }

    public boolean isEmpty() {
        return false;
    }

    public boolean isPawn() {
        return false;
    }

    protected boolean isDiagonal(Position start, Position end) {
        int rowDiff = Math.abs(Position.makeRowDiff(start, end));
        int colDiff = Math.abs(Position.makeColumnDiff(start, end));
        return (rowDiff != 0 && colDiff != 0) && rowDiff == colDiff;
    }

    protected boolean isLinear(Position start, Position end) {
        int rowDiff = Math.abs(Position.makeRowDiff(start, end));
        int colDiff = Math.abs(Position.makeColumnDiff(start, end));
        return (rowDiff == 0) || (colDiff == 0);
    }

    protected Direction getLinearDirection(Position start, Position end) {
        int rowDiff = Position.makeRowDiff(start, end);
        int colDiff = Position.makeColumnDiff(start, end);
        Direction direction = Direction.findLinearDirection(rowDiff, colDiff);
        return direction;
    }

    protected Direction getDiagonalDirection(Position start, Position end) {
        int rowDiff = Position.makeRowDiff(start, end);
        int colDiff = Position.makeColumnDiff(start, end);
        Direction direction = Direction.findDiagonalDirection(rowDiff, colDiff);
        return direction;
    }

    public Direction direction(Position start, Position end) {
        throw new RuntimeException(name.getName() + "은 direction이 구현되지 않았습니다. ");
    }

    @Override
    public String toString() {
        return name.getName();
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
