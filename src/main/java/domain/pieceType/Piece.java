package domain.pieceType;

import domain.Color;
import domain.Square;
import java.util.List;

public abstract class Piece {

    private final Color color;

    public Piece(final Color color) {
        this.color = color;
    }

    public abstract List<Square> calculatePath(Square source, Square target);

    public abstract PieceType getPieceType();

    public Color getColor() {
        return color;
    }
}
