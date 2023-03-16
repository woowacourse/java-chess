package chess.controller;

import java.util.Arrays;

public enum ChessCommand {
    START("start"),
    MOVE("move"),
    END("end"),
    ;

    private final String command;

    ChessCommand(String command) {
        this.command = command;
    }

    public static ChessCommand from(String inputCommand) {
        return Arrays.stream(ChessCommand.values())
                .filter(it -> it.command.equals(inputCommand))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 명령어는 존재하지 않습니다."));
    }

    public static ChessCommand getStart(String inputCommand) {
        ChessCommand chessCommand = from(inputCommand);
        if (chessCommand == START) {
            return chessCommand;
        }

        throw new IllegalArgumentException("게임 시작 명령어를 입력하세요.");
    }

    public boolean isEnd() {
        return this == END;
    }
}
