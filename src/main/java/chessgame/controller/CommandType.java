package chessgame.controller;

import java.util.Arrays;
import java.util.List;

public enum CommandType {

    START("start", 2),
    END("end", 1),
    MOVE("move", 3),
    STATUS("status", 1);

    private final String message;
    private final int commandCount;

    CommandType(final String message, final int commandCount) {
        this.message = message;
        this.commandCount = commandCount;
    }

    public static CommandType of(final List<String> targetMessage) {
        String commandLower = targetMessage.get(0)
                                           .toLowerCase();
        return Arrays.stream(values())
                     .filter(command -> command.message.equals(commandLower))
                     .filter(command -> targetMessage.size() == command.commandCount)
                     .findAny()
                     .orElseThrow(() -> new IllegalArgumentException("[ERROR] 유효하지 않은 명령입니다."));
    }

    public boolean canContinue() {
        return isStart() || isMove() || isStatus();
    }

    public boolean isStart() {
        return this.equals(CommandType.START);
    }

    public boolean isEnd() {
        return this.equals(CommandType.END);
    }

    public boolean isMove() {
        return this.equals(CommandType.MOVE);
    }

    public boolean isStatus() {
        return this.equals(CommandType.STATUS);
    }
}
