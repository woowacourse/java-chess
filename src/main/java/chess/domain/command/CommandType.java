package chess.domain.command;

import java.util.Arrays;

public enum CommandType {

    START("start", 3),
    END("end", 3),
    MOVE("move", 5),
    STATUS("status", 3);

    private final String command;
    private int paramCount;

    CommandType(String command, int paramCount) {
        this.command = command;
        this.paramCount = paramCount;
    }

    static CommandType from(String command) {
        return Arrays.stream(values())
                .filter(ele -> ele.command.equalsIgnoreCase(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("%s는 잘못된 입력입니다.", command)));
    }

    void validateCommandSize(int inputSize) {
        if (paramCount != inputSize) {
            throw new IllegalArgumentException(String.format("%s의 파라미터 갯수는 %d개입니다.", command, paramCount));
        }
    }
}
