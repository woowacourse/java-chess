package chess.command;

import java.util.Arrays;

public enum Command {
    START("start", new Start()),
    END("end", new End());

    public static final String NOT_FOUND_MESSAGE = "존재하지 않는 명령어입니다.";

    private String command;
    private Runnable runnable;

    Command(String command, Runnable runnable) {
        this.command = command;
        this.runnable = runnable;
    }

    public static Command of(String command) {
        return Arrays.stream(values())
                .filter(value -> value.command.equals(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_MESSAGE));
    }

    public void execute() {
        runnable.run();
    }
}
