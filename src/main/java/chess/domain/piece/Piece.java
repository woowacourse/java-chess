package chess.domain.piece;

import chess.domain.position.Position;

import java.util.List;
import java.util.Objects;

public abstract class Piece {
    protected final char representation;
    protected final Position position;

    public Piece(char representation, Position position) {
        this.representation = representation;
        this.position = position;
    }

    public char getRepresentation() {
        return representation;
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return String.valueOf(representation);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Piece piece = (Piece) o;
        return representation == piece.representation &&
                Objects.equals(position, piece.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(representation, position);
    }

    public abstract List<Position> getPossiblePositions();
}
