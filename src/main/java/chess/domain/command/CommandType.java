package chess.domain.command;

import java.util.Arrays;

public enum CommandType {

    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status");

    private final String command;

    CommandType(String command) {
        this.command = command;
    }

    protected static CommandType from(String command) {
        return Arrays.stream(values())
                .filter(ele -> ele.command.equalsIgnoreCase(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("%s는 잘못된 입력입니다.", command)));
    }
}
