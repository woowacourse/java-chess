package chess.controller;

import static java.lang.String.format;

import java.util.Arrays;

public enum ChessExecuteCommand {

    MOVE("move"),
    STOP("end");

    private final String input;

    ChessExecuteCommand(final String input) {
        this.input = input;
    }

    public static ChessExecuteCommand from(final String input) {
        return Arrays.stream(ChessExecuteCommand.values())
                .filter(chessExecuteCommand -> chessExecuteCommand.input.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(format("%s나 %s 중 입력해야 합니다.", MOVE, STOP)));
    }

    @Override
    public String toString() {
        return input;
    }
}
