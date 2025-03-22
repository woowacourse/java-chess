package chess.piece;

import chess.Color;
import chess.Position;
import java.util.List;

public abstract class Piece {

    private final Color color;

    public Piece(Color color) {
        this.color = color;
    }

    public abstract List<Position> calculateCanMovePosition(Position departure, Position arrival);

    public Color getColor() {
        return this.color;
    }

    public boolean isSameType(Piece piece) {
        return this.getClass() == piece.getClass();
    }
}
