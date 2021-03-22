package domain.piece;

import domain.Color;
import domain.Direction;
import domain.Score;

import java.util.Map;
import java.util.Objects;

public abstract class Piece {
    private final Name name;
    private final Score score;
    private final Color color;

    public abstract boolean canMove2(Map<Position, Piece> board, Position start, Position end);

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
        int rowDiff = Math.abs(start.getRow() - end.getRow());
        int colDiff = Math.abs(start.getColumn() - end.getColumn());
        return (rowDiff != 0 && colDiff != 0) && rowDiff == colDiff;
    }

    public boolean isLinear(Position start, Position end) {
        int rowDiff = Math.abs(start.getRow() - end.getRow());
        int colDiff = Math.abs(start.getColumn() - end.getColumn());
        return (rowDiff == 0) || (colDiff == 0);
    }

    protected boolean isEmptyPosition(Map<Position, Piece> board, Position nextPosition) {
        return !board.containsKey(nextPosition);
    }

    protected Direction getLinearDirection(Position start, Position end) {
        int rowDiff = end.getRow() - start.getRow();
        int colDiff = end.getColumn() - start.getColumn();
        Direction direction = Direction.findLinearDirection(rowDiff, colDiff);
        return direction;
    }

    protected Direction getDiagonalDirection(Position start, Position end) {
        int rowDiff = end.getRow() - start.getRow();
        int colDiff = end.getColumn() - start.getColumn();
        Direction direction = Direction.findDiagonalDirection(rowDiff, colDiff);
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
