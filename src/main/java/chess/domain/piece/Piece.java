package chess.domain.piece;

import chess.domain.Color;
import chess.domain.position.Position;

public abstract class Piece {
    private final Position position;
    private final Color color;

    public Piece(Position position, Color color) {
        this.position = position;
        this.color = color;
    }

    public String signature() {
        return color.correctSignature(baseSignature());
    }

    protected abstract String baseSignature();
}
