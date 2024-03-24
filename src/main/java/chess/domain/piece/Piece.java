package chess.domain.piece;

import chess.domain.position.Position;
import java.util.List;

public abstract class Piece {
    protected final Color color;

    public Piece(Color color) {
        this.color = color;
    }

    public abstract String identifyType();

    public abstract boolean canMove(Position source, Position target, Color color);

    public abstract List<Position> searchPath(Position source, Position target);

    public boolean isBlack() {
        return color == Color.BLACK;
    }

    public boolean isKing() {
        return this instanceof King;
    }

    public Color getColor() {
        return color;
    }
}
