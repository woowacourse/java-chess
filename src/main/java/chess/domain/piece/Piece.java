package chess.domain.piece;

import chess.domain.Color;
import chess.domain.position.Position;
import java.util.List;

public abstract class Piece {

    protected final Color color;
    protected boolean isDisplaced;

    public Piece(Color color) {
        this.color = color;
        this.isDisplaced = false;
    }

    public String signature() {
        return color.correctSignature(baseSignature());
    }

    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    public boolean isNeverDisplaced() {
        return !isDisplaced;
    }

    public Piece displaced() {
        this.isDisplaced = true;
        return this;
    }

    protected abstract String baseSignature();

    public abstract boolean isMovable(Position source, Position target);

    public abstract List<Position> findRoute(Position source, Position target);

    public abstract double score();

    public abstract boolean isBlank();

    public abstract boolean isPawn();

    public abstract boolean isKing();

    public abstract boolean isRook();
}
