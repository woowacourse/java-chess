package chess.controller.command;

import java.util.Arrays;

public enum RoomCommand {

    NEW("new"),
    ENTER("enter");

    private final String symbol;

    RoomCommand(final String symbol) {
        this.symbol = symbol;
    }

    public static RoomCommand from(final String symbol) {
        return Arrays.stream(values())
                .filter(roomCommand -> roomCommand.symbol.equals(symbol))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 명령어입니다."));
    }
}
