package chess.domain.piece;

import chess.domain.Color;

public abstract class Piece {
    private final Color color;

    public Piece(Color color) {
        this.color = color;
    }

    public String signature() {
        return color.correctSignature(baseSignature());
    }

    protected abstract String baseSignature();
}
