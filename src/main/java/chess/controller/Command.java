package chess.controller;

import java.util.Arrays;

public enum Command {

    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status");

    private final String symbol;

    Command(final String symbol) {
        this.symbol = symbol;
    }

    public static Command from(final String symbol) {
       return Arrays.stream(values())
                .filter(command -> command.symbol.equals(symbol))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 명령어입니다."));
    }
}
