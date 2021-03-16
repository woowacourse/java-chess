package domain.chess.piece;

import java.util.Objects;

public abstract class Piece {
    private Position position;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return Double.compare(piece.score, score) == 0 && isBlack == piece.isBlack && Objects.equals(position, piece.position) && Objects.equals(name, piece.name);
    }

    public boolean isBlack(){
        return isBlack;
    }

    public String getName() {
        return name;
    }

    public void movePosition(Position position) {
        this.position = position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, name, score, isBlack);
    }
}
