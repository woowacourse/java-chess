package chess.domain.piece;

import chess.domain.Color;

public abstract class Piece implements Movable {
    protected final Color color;

    Piece(final Color color) {
        this.color = color;
    }
}
