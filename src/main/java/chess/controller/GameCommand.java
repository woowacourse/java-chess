package chess.controller;

import java.util.Arrays;
import java.util.List;

public enum GameCommand {
    EMPTY(""),
    CREATE("create"),
    START("start"),
    MOVE("move"),
    STATUS("status"),
    END("end");

    public static final int COMMAND_INDEX_IN_COMMANDLINE = 0;
    private final String commandString;

    GameCommand(final String commandString) {
        this.commandString = commandString;
    }

    public static final GameCommand of(List<String> commandLine) {
        return Arrays.stream(values())
                .filter(commandMapper -> commandMapper.commandString.equals(commandLine.get(COMMAND_INDEX_IN_COMMANDLINE)))
                .findFirst()
                .orElseThrow(()-> new IllegalArgumentException("일치하는 명령어가 없습니다"));
    }

}
