package chess.controller.command;

import java.util.Arrays;

public enum GameCommand {

    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status");

    private final String symbol;

    GameCommand(final String symbol) {
        this.symbol = symbol;
    }

    public static GameCommand from(final String symbol) {
       return Arrays.stream(values())
                .filter(gameCommand -> gameCommand.symbol.equals(symbol))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 명령어입니다."));
    }
}
