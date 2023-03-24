package chess.controller;

import static java.lang.String.format;

import java.util.Arrays;

public enum ChessGameCommand {

    START("start"),
    END("end"),
    MOVE("move");

    private final String input;

    ChessGameCommand(final String input) {
        this.input = input;
    }

    public static ChessGameCommand from(final String input) {
        if (input.contains(MOVE.input)) {
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
