package view.util;

import java.util.Arrays;

public enum Command {

    START("start"),
    MOVE("move"),
    END("end");

    private final String identifier;

    Command(String identifier) {
        this.identifier = identifier;
    }

    public static Command from(String identifier) {
        return Arrays.stream(values())
                .filter(command -> command.identifier.equals(identifier))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당되는 식별자가 존재하지 않습니다."));
    }
}
