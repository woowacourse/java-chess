package domain.chess.piece;

import java.util.Objects;

public abstract class Piece {
    protected Position position;
    private final String name;
    private final double score;
    private final boolean isBlack;

    protected Piece(String name, double score, Position position, boolean color) {
        this.name = name;
        this.score = score;
        this.position = position;
        this.isBlack = color;
    }

    public Position getPosition() {
        return this.position;
    }

    public boolean isBlack() {
        return isBlack;
    }

    public String getName() {
        return name;
    }

    public boolean isOurTeam(Piece[][] board, Position endPosition){
        return board[endPosition.getRow()][endPosition.getColumn()].isBlack == isBlack;
    }

    public boolean positionIsEmpty(Piece piece) {
        return piece == null;
    }

    public abstract boolean canMove(Piece[][] board, Position end);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return Double.compare(piece.score, score) == 0 && isBlack == piece.isBlack && Objects.equals(position, piece.position) && Objects.equals(name, piece.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, name, score, isBlack);
    }

    public abstract Piece movePosition(Position end);
}
