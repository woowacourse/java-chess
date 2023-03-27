package chess.controller;

import java.util.Arrays;
import java.util.List;

public enum Command {

    START("start"),
    MOVE("move"),
    STATUS("status"),
    END("end");

    public static final int COMMAND_INDEX_IN_COMMANDLINE = 0;
    public static final int START_SOURCE_INDEX_IN_COMMANDLINE = 1;
    public static final int TARGET_SOURCE_INDEX_IN_COMMANDLINE = 2;

    private final String value;

    Command(String value) {
        this.value = value;
    }

    public static Command findCommandByCommandLine(List<String> input) {
        return Arrays.stream(values())
                .filter(command -> command.value.equals(input.get(COMMAND_INDEX_IN_COMMANDLINE)))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("일치하는 명령어가 없습니다"));
    }

    public static String findStartSource(List<String> commandLine) {
        return commandLine.get(START_SOURCE_INDEX_IN_COMMANDLINE);
    }

    public static String findTargetSource(List<String> commandLine) {
        return commandLine.get(TARGET_SOURCE_INDEX_IN_COMMANDLINE);
    }

}
