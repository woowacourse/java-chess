package domain.piece;

import domain.Score;

import java.util.Objects;

public abstract class Piece {
    protected Position position;
    private final String name;
    private final Score score;
    private final boolean isBlack;

    protected Piece(String name, Score score, Position position, boolean color) {
        this.name = name;
        this.score = score;
        this.position = position;
        this.isBlack = color;
    }

    public abstract boolean canMove(Piece[][] board, Position end);

    public abstract Piece movePosition(Position end);

    public Position getPosition() {
        return this.position;
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

    public boolean isOurTeam(Piece[][] board, Position endPosition) {
        return board[endPosition.getRow()][endPosition.getColumn()].isBlack == isBlack;
    }

    public boolean isKing() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return isBlack == piece.isBlack && Objects.equals(position, piece.position) && Objects.equals(name, piece.name) && Objects.equals(score, piece.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, name, score, isBlack);
    }
}
