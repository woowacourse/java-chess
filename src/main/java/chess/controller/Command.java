package chess.controller;

import chess.Exception.IllegalCommandException;

import java.util.Arrays;
import java.util.Objects;

public enum Command {
    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status");

    private final String commandValue;

    Command(String command) {
        this.commandValue = command;
    }

    public static Command of(String commandInput) {
        Objects.requireNonNull(commandInput);
        return Arrays.stream(values())
                .filter(command -> command.commandValue.equals(commandInput))
                .findFirst()
                .orElseThrow(IllegalCommandException::new);
    }
}
