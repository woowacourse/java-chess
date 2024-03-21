package chess.view;

import java.util.Arrays;

public enum Command {
    START("start"),
    MOVE("move"),
    END("end");

    private final String input;

    Command(String input) {
        this.input = input;
    }

    private boolean isMatchedWith(String input) {
        return this.input.equals(input);
    }

    public static Command findBy(String input) {
        return Arrays.stream(Command.values())
                .filter(command -> command.isMatchedWith(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 명령어입니다."));
    }
}
