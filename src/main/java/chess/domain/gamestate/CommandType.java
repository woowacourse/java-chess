package chess.domain.gamestate;

import java.util.Arrays;

public enum CommandType {
    START("start"),
    MOVE("move"),
    STATUS("status"),
    END("end");

    private static final int COMMAND_INDEX = 0;
    private static final int COMMAND_LENGTH_EXCEPT_MOVE = 1;
    private static final int COMMAND_LENGTH_MOVE = 3;
    private final String decription;

    CommandType(String decription) {
        this.decription = decription;
    }

    public static CommandType from(String value) {
        validate(value);
        String command = value.split(" ")[COMMAND_INDEX];
        return Arrays.stream(values())
            .filter(commandType -> commandType.decription.equals(command))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 명령입니다."));
    }

    private static void validate(String value) {
        String[] splitedValue = value.split(" ");
        String command = splitedValue[COMMAND_INDEX];
        if (command.equals("move")) {
            validateLocationValue(splitedValue);
            return;
        }
        if (splitedValue.length != COMMAND_LENGTH_EXCEPT_MOVE) {
            throw new IllegalArgumentException("[ERROR] move이외의 명령어는 한 단어로 입력해야 합니다.");
        }
    }

    private static void validateLocationValue(String[] value) {
        if (value.length != COMMAND_LENGTH_MOVE) {
            throw new IllegalArgumentException("[ERROR] 두 개의 좌표(시작 위치, 목적 위치)가 필요합니다.");
        }
    }
}
