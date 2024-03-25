package chess.view;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum Command {

    START("start"),
    END("end"),
    MOVE("move"),
    ;

    private final String command;

    Command(final String command) {
        this.command = command;
    }

    public static Command of(final String rawInput) {
        if (START.command.equals(rawInput)) {
            return START;
        }
        if (END.command.equals(rawInput)) {
            return END;
        }
        if (MOVE.command.equals(rawInput)) {
            return MOVE;
        }

        String candidates = Arrays.stream(values())
                .map(Command::toString)
                .collect(Collectors.joining(", "));

        throw new IllegalArgumentException(String.format("%s만 입력할 수 있습니다.", candidates));
    }

    @Override
    public String toString() {
        return this.command;
    }
}
