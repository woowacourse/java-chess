package chess.domain.piece;

import java.util.Objects;

public class FullPiece implements Piece {

    private final Color color;
    private final Type type;

    public FullPiece(final Color color, final Type type) {
        this.color = color;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FullPiece piece = (FullPiece) o;
        return color == piece.color && type == piece.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, type);
    }

    @Override
    public String getName() {
        if (color.equals(Color.WHITE)) {
            return type.getType().toLowerCase();
        }
        return type.getType();
    }
}
