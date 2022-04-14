package chess.domain;

import java.util.Arrays;

import chess.domain.piece.Color;

public enum GameTurn {
    READY(Color.NONE),
    WHITE(Color.WHITE),
    BLACK(Color.BLACK),
    FINISHED(Color.NONE);

    private static final String ERROR_MESSAGE_NO_SAVED_GAME = "헉.. 저장 안한거 아냐? 그런 게임은 없어!";

    private final Color color;

    GameTurn(Color color) {
        this.color = color;
    }

    public static GameTurn find(String name) {
        return Arrays.stream(values())
                .filter(gameTurn -> gameTurn.name().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ERROR_MESSAGE_NO_SAVED_GAME));
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
