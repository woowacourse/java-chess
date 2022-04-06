package chess.domain;

import java.util.Arrays;

import chess.domain.piece.Color;

public enum GameTurn {
    READY(Color.NONE),
    WHITE(Color.WHITE),
    BLACK(Color.BLACK),
    FINISHED(Color.NONE);

    private final Color color;

    GameTurn(Color color) {
        this.color = color;
    }

    public static GameTurn find(String name) {
        return Arrays.stream(values())
                .filter(gameTurn -> gameTurn.name().equals(name))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public GameTurn switchColor() {
        if (this == WHITE) {
            return BLACK;
        }
        return WHITE;
    }

    public Color getColor() {
        return this.color;
    }
}
