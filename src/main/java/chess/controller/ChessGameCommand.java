package chess.controller;

import java.util.stream.Stream;

import static java.lang.String.format;

public enum ChessGameCommand {

    START("start"),
    END("end"),
    MOVE("move");

    private final String input;

    ChessGameCommand(final String input) {
        this.input = input;
    }

    public static ChessGameCommand generateExecuteCommand(final String input) {
        return Stream.of(START, END)
                .filter(chessGameCommand -> chessGameCommand.input.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(format("%s, %s 중 입력해야 합니다.", START, END)));
    }

    public static ChessGameCommand generateMoveCommand(final String input) {
        return Stream.of(MOVE, END)
                .filter(chessGameCommand -> input.contains(chessGameCommand.input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(format("%s, %s 중 입력해야 합니다.", MOVE, END)));
    }

    @Override
    public String toString() {
        return input;
    }
}
