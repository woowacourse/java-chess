package utils;

import java.util.Objects;

public class CommandParser {
    private CommandParser() {
    }

    public static String[] parseCommand(String command) {
        Objects.requireNonNull(command, "유효하지 않은 명령 입니다.");
        return command.split(" ");
    }
}
