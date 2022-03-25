package chess.domain.piece;

import chess.domain.Position;
import java.util.Objects;

public abstract class Piece {

    private final State state;

    protected Position position;

    public Piece(State state, Position position) {
        this.state = state;
        this.position = position;
    }

    public abstract Position move(Position currentPosition, Position destinationPosition);

    public abstract boolean exist(Position checkingPosition);

    public Position capture(Position currentPosition, Position destinationPosition) {
        return move(currentPosition, destinationPosition);
    }

    public boolean isPawn() {
        return false;
    }

    public boolean isSameFile(char file) {
        return position.isSameFile(file);
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
        return Objects.equals(position, piece.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }
}
