package chess.controller;

import java.util.Arrays;
import java.util.Objects;

import static java.lang.String.format;

public enum ChessGameCommand {

    START("start"),
    END("end"),
    MOVE("move");

    private final String input;

    ChessGameCommand(final String input) {
        this.input = input;
    }

    public static ChessGameCommand from(final String input) {
        if (Objects.nonNull(input) && input.contains(MOVE.input)) {
            return MOVE;
        }
        return Arrays.stream(ChessGameCommand.values())
                .filter(chessGameCommand -> chessGameCommand.input.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(format("%s, %s, %s 중 입력해야 합니다.", START, END, MOVE)));
    }

    @Override
    public String toString() {
        return input;
    }
}
