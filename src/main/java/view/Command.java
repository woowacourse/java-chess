package view;

import java.util.Arrays;

public enum Command {
    START("start"),
    END("end"),
    MOVE("move");

    private final String name;

    Command(String name) {
        this.name = name;
    }

    public static Command findByName(String name) {
        return Arrays.stream(values())
                .filter(command -> command.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 명령어입니다."));
    }

    public boolean isNotEnd() {
        return this != END;
    }

    public String getName() {
        return name;
    }

    public boolean isMove() {
        return this == MOVE;
    }
}
