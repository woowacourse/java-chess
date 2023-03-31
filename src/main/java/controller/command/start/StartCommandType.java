package controller.command.start;

import java.util.Arrays;

public enum StartCommandType {
    START("START", new StartCommand()),
    LOAD("LOAD", new LoadCommand()),
    END("END", new EndCommand());

    private final String commandHead;
    private final StartAction startAction;

    StartCommandType(final String commandHead, final StartAction startAction) {
        this.commandHead = commandHead;
        this.startAction = startAction;
    }

    public static StartAction from(String commandHead) {
        return Arrays.stream(StartCommandType.values())
                .filter(type -> type.commandHead.equals(commandHead.toUpperCase()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 시작 커맨드 입니다."))
                .startAction;
    }
}
