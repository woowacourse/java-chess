package chess.domain.player;

import chess.domain.piece.Color;

public class Player {
    private final Color color;

    public Player(final Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
