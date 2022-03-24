package chess.domain.piece;

import chess.domain.Color;
import chess.domain.position.Position;

import java.util.List;

public abstract class Piece {
    protected final Color color;

    public Piece(Color color) {
        this.color = color;
    }

    public String signature() {
        return color.correctSignature(baseSignature());
    }

    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    protected abstract String baseSignature();

    public abstract boolean isBlank();

    public abstract boolean isMovable(Position source, Position target);

    public abstract List<Position> findRoute(Position source, Position target);

    public abstract double score();

    public abstract boolean isPawn();
}
