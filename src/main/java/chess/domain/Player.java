package chess.domain;

import chess.domain.piece.Color;

public class Player {
    private final Color color;

    public Player(Color color) {
        this.color = color;
    }

    public Color color() {
        return color;
    }

}
