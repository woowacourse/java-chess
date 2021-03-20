package chess.domain.command;

import java.util.Arrays;

public enum CommandType {
    START("start"),
    MOVE("move"),
    STATUS("status"),
    END("end");

    private final String description;

    CommandType(String description) {
        this.description = description;
    }

    public static CommandType from(String value) {
        validate(value);
        String command = value.split(" ")[0];
        return Arrays.stream(values())
            .filter(commandType -> commandType.description.equals(command))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 명령입니다."));
    }

    private static void validate(String value) {
        String[] splited = value.split(" ");
        String command = splited[0];
        if (command.equals("move")) {
            if (splited.length != 3) {
                throw new IllegalArgumentException("잘못된 좌표가 입력되었습니다.");
            }
        } else if (splited.length != 1) {
            throw new IllegalArgumentException("start end status만 입력해야 합니다.");
        }
    }

    public boolean isSame(CommandType command) {
        return equals(command);
    }
}
