package chess.controller;

import java.util.Arrays;

public enum ChessGameCommand {

    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status"),
    ;

    private static final String WRONG_COMMAND_MESSAGE = "잘못된 명령어를 입력하였습니다.";
    private final String value;

    ChessGameCommand(String value) {
        this.value = value;
    }

    public static ChessGameCommand from(final String inputCommand) {
        if (inputCommand.startsWith("move")) {
            return MOVE;
        }
        return Arrays.stream(ChessGameCommand.values())
            .filter(it -> it.value.equalsIgnoreCase(inputCommand))
            .findFirst()
            .orElseThrow(()-> new IllegalArgumentException(WRONG_COMMAND_MESSAGE));
    }
}
