package chess.controller.console.command;

import java.util.Arrays;

public enum Menu {
    END("end", 1),
    START("start", 1),
    MOVE("move", 3),
    STATUS("status", 1),
    SHOW("show", 2);

    private final String command;
    private final int parameterCount;

    Menu(final String command, final int parameterCount) {
        this.command = command;
        this.parameterCount = parameterCount;
    }

    public static Menu of(final String line) {
        final String[] splitLine = line.split(" ");
        return Arrays.stream(values())
                .filter(menu -> menu.isSameCommand(splitLine[0]) && menu.isSameParameterSize(splitLine.length))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 명령어 입력입니다."));
    }

    private boolean isSameCommand(final String input) {
        return this.command.equals(input);
    }

    private boolean isSameParameterSize(final int size) {
        return this.parameterCount == size;
    }

    public boolean isEnd() {
        return this.equals(END);
    }

    public boolean isMove() {
        return this.equals(MOVE);
    }

    public boolean isStatus() {
        return this.equals(STATUS);
    }

    public boolean isShow() {
        return this.equals(SHOW);
    }

    public boolean isStart() {
        return this.equals(START);
    }
}
