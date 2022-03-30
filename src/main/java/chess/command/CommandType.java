package chess.command;

import java.util.Arrays;

public enum CommandType {

    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status");

    private final String value;

    CommandType(String value) {
        this.value = value;
    }

    public static CommandType findByCommand(String command) {
        return Arrays.stream(values())
            .filter(command2 -> command2.value.equalsIgnoreCase(command))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("명령어를 잚못 입력 했습니다."));
    }
}
