package domain.piece;

import domain.Score;

import java.util.Map;
import java.util.Objects;

public abstract class Piece {
    private final String name;
    private final Score score;
    private final boolean isBlack;

    public abstract boolean canMove2(Map<Position, Piece> board, Position start, Position end);

    public Piece(String name, Score score, boolean isBlack) {
        this.name = name;
        this.score = score;
        this.isBlack = isBlack;
    }

    public boolean isBlack() {
        return isBlack;
    }

    public String getName() {
        return name;
    }

    public Score getScore() {
        return score;
    }

    public boolean isKing() {
        return false;
    }

    public boolean isSameColor(boolean color) {
        return this.isBlack == color;
    }

    public boolean isSameColor(Piece piece) {
        return this.isBlack == piece.isBlack;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return isBlack == piece.isBlack && Objects.equals(name, piece.name) && Objects.equals(score, piece.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, score, isBlack);
    }
}
