package chess.controller;

import static java.lang.String.format;

import java.util.Arrays;

public enum ChessStartCommand {

    START("start"),
    END("end");

    private final String input;

    ChessStartCommand(final String input) {
        this.input = input;
    }

    public static ChessStartCommand from(final String input) {
        return Arrays.stream(ChessStartCommand.values())
                .filter(chessStartCommand -> chessStartCommand.input.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(format("%s나 %s 중 입력해야 합니다.", START, END)));
    }

    @Override
    public String toString() {
        return input;
    }
}
