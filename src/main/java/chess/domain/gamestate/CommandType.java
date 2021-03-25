package chess.domain.gamestate;

import java.util.Arrays;

public enum CommandType {
    START("start"),
    MOVE("move"),
    STATUS("status"),
    END("end");

    private final String decription;

    CommandType(String decription) {
        this.decription = decription;
    }

    public static CommandType from(String value) {
        validate(value);
        String command = value.split(" ")[0];
        return Arrays.stream(values())
            .filter(commandType -> commandType.decription.equals(command))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 명령입니다."));
    }

    private static void validate(String value) {
        String[] splitedValue = value.split(" ");
        String command = splitedValue[0];
        if (command.equals("move")) {
            validateLocationValue(splitedValue);
            return;
        }
        if (splitedValue.length != 1) {
            throw new IllegalArgumentException("[ERROR] move이외의 명령어는 한 단어로 입력해야 합니다.");
        }
    }

    private static void validateLocationValue(String[] value) {
        if (value.length != 3) {
            throw new IllegalArgumentException("[ERROR] 두 개의 좌표(시작 위치, 목적 위치)가 필요합니다.");
        }
    }
}
