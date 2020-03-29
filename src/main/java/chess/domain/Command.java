package chess.domain;

import chess.exceptions.InvalidInputException;

import java.util.stream.Stream;

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
        Stream.of(Command.values())
                .filter(command -> command.is(value))
                .findAny()
                .orElseThrow(InvalidInputException::new);
    }
}
