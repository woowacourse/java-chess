package chess.domain.piece;

import chess.domain.position.File;
import chess.domain.position.Position;
import java.util.List;
import java.util.Objects;

public abstract class Piece {

    protected final Position position;
    protected final Side side;

    public Piece(final Position position, Side side) {
        this.position = position;
        this.side = side;
    }

    public abstract boolean isMovable(Position targetPosition);

    public boolean isSamePosition(Position position) {
        return this.position.equals(position);
    }

    public abstract Piece move(final Position positionToMove);

    public abstract boolean isPawn();

    public abstract boolean isKing();

    public abstract List<Position> getPaths(Position targetPosition);

    public boolean isSameSide(Side side) {
        return this.side == side;
    }

    public boolean isSameFile(File file) {
        return position.isSameFile(file);
    }

    public int getFile() {
        return position.getFile();
    }

    public int getRank() {
        return position.getRank();
    }

    public Side getSide() {
        return side;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return Objects.equals(position, piece.position) && side == piece.side;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, side);
    }

    @Override
    public String toString() {
        return "Piece{" +
                "position=" + position +
                ", side=" + side +
                '}';
    }
}
