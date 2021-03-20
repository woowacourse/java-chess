package chess.manager;

import java.util.Arrays;

public enum Menu {
    START("start", 1),
    END("end", 1),
    MOVE("move", 3),
    STATUS("status", 1),
    SHOW("show", 2);

    private final String command;
    private final int parameterCount;

    Menu(final String command, int parameterCount) {
        this.command = command;
        this.parameterCount = parameterCount;
    }

    public static Menu of(final String line) {
        String[] splitLine = line.split(" ");
        return Arrays.stream(values())
                .filter(menu -> menu.isSameCommand(splitLine[0]) && menu.isSameParameterSize(splitLine.length))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    private boolean isSameCommand(String input) {
        return this.command.equals(input);
    }

    private boolean isSameParameterSize(int size){
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

    public boolean isStart() {
        return this.equals(START);
    }

    public boolean isShow() {
        return this.equals(SHOW);
    }

    public int getParameterCount() {
        return this.parameterCount;
    }
}
