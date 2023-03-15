package chess.view;

import java.util.Arrays;

public enum Command {
    START("start"),
    END("end");

    private final String command;

    Command(final String command) {
        this.command = command;
    }

    public static Command of(String inputCommand) {
        return Arrays.stream(Command.values())
                .filter(command -> command.command.equals(inputCommand))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 명령입니다."));
    }
}
