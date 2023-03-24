package chess.view;

import java.util.Arrays;

public enum Command {
    START("start"),
    END("end"),
    MOVE("move");

    private final String command;

    Command(final String command) {
        this.command = command;
    }

    public static Command of(String inputCommand) {
        String value = inputCommand.toLowerCase();
        return Arrays.stream(Command.values())
                .filter(command -> command.command.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 명령입니다."));
    }
}
