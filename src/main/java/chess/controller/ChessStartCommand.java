package chess.controller;

import static java.lang.String.format;

import java.util.Arrays;

public enum ChessStartCommand {

    START("start");

    private final String input;

    ChessStartCommand(final String input) {
        this.input = input;
    }

    @Override
    public String toString() {
        return input;
    }

    public static ChessStartCommand from(final String input) {
        return Arrays.stream(ChessStartCommand.values())
                .filter(chessStartCommand -> chessStartCommand.input.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(format("%s를 입력해야 게임이 시작됩니다.", START)));
    }
}
