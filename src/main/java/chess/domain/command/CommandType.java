package chess.domain.command;

import java.util.stream.Stream;

public enum CommandType {
    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status")
    ;

    private final String command;

    CommandType(String command) {
        this.command = command;
    }

    public static CommandType from(String command) {
        return Stream.of(CommandType.values())
                .filter(value -> value.getCommand().equals(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 명령입니다."));
    }

    public String getCommand() {
        return command;
    }
}
