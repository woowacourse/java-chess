package chess.domain;

import java.util.Arrays;

import chess.exceptions.InvalidInputException;

public enum Command {
    START("start"),
    END("end"),
    MOVE("move [a-h][1-8] [a-h][1-8]"),
    STATUS("status");

    private final String value;

    Command(final String value) {
        this.value = value;
    }

    public boolean is(String value) {
        return value.matches(this.value);
    }

    public static void validate(String value) {
        Arrays.stream(Command.values())
            .filter(command -> command.is(value))
            .findAny()
            .orElseThrow(InvalidInputException::new);
    }
}
