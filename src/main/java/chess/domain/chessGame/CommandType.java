package chess.domain.chessGame;

import java.util.Arrays;

public enum CommandType {
    START("start"),
    MOVE("move"),
    END("end"),
    STATUS("status");

    private final String commandType;

    CommandType(final String commandType) {
        this.commandType = commandType;
    }

    public String getCommandType() {
        return commandType;
    }

    public static CommandType valueByCommandType(final String commandType) {
        return Arrays.stream(values())
                .filter(value -> value.commandType.equals(commandType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 이름의 CommandType 객체가 존재하지 않습니다."));
    }
}
