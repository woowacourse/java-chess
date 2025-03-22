package chess.domain.piece;

import java.util.List;
import java.util.Objects;

public abstract class Piece {

    protected final Color color;
    protected Position position;

    protected Piece(final Color color, final Position position) {
        this.color = color;
        this.position = position;
    }

    public abstract List<Position> canMove(Position position);

    public boolean samePosition(final Position position) {
        return this.position.equals(position);
    }


    public Color getColor() {
        return color;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Piece piece = (Piece) o;
        return color == piece.color && Objects.equals(position, piece.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, position);
    }

    public void move(final Position wantMovePosition) {
        this.position = wantMovePosition;
    }

    public boolean isOtherTeam(final Piece findPiece) {
        return this.color != findPiece.color;
    }

    public boolean isSameTeam(Color color) {
        return this.color == color;
    }
}
