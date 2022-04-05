package chess.domain;

import java.util.Arrays;
import java.util.List;

public enum Command {
    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status");

    private static final int COMMAND_INDEX = 0;
    private static final String COMMAND_NOT_FOUND_EXCEPTION = "[ERROR] 존재하지 않는 명령어입니다.";

    private final String type;

    Command(String type) {
        this.type = type;
    }

    public static Command of(List<String> inputs) {
        String inputCommand = inputs.get(COMMAND_INDEX);
        return Arrays.stream(values())
                .filter(it -> it.type.equals(inputCommand))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(COMMAND_NOT_FOUND_EXCEPTION));
    }

    public boolean isStart() {
        return this == START;
    }

    public boolean isEnd() {
        return this == END;
    }

    public boolean isStatus() {
        return this == STATUS;
    }

    public boolean isMove() {
        return this == MOVE;
    }
}
