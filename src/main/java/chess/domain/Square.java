package chess.domain;

import java.util.Objects;

public class Square {
    private final Piece piece;
    private final Position position;

    public Square(Piece piece, Position position) {
        this.piece = piece;
        this.position = position;
    }

    public Team getTeam() {
        return piece.getTeam();
    }

    public Position getPosition() {
        return position;
    }

    public Role getRole() {
        return piece.getRole();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Square)) {
            return false;
        }
        Square square = (Square) o;
        return Objects.equals(piece, square.piece) && Objects.equals(position, square.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(piece, position);
    }
}
