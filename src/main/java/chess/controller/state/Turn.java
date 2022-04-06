package chess.controller.state;

import chess.domain.Color;
import java.util.Arrays;

public enum Turn {
    START("start", Color.WHITE),
    WHITE_TURN("white_turn", Color.WHITE),
    BLACK_TURN("black_turn", Color.BLACK),
    END("end", Color.EMPTY),
    ;

    private final String value;
    private final Color color;

    Turn(String value, Color color) {
        this.value = value;
        this.color = color;
    }

    public static Turn from(String input) {
        return Arrays.stream(values())
                .filter(turn -> turn.value.equals(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 기물입니다."));
    }

    public Turn nextTurn() {
        if (this == START) {
            return BLACK_TURN;
        }
        if (this == WHITE_TURN) {
            return BLACK_TURN;
        }
        if (this == BLACK_TURN) {
            return WHITE_TURN;
        }
        return END;
    }

    public String getValue() {
        return value;
    }

    public Color getColor() {
        return color;
    }
}
