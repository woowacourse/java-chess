package chess.domain.command;

import chess.domain.position.Position;
import chess.exception.InvalidCommandException;

import java.util.Arrays;

public enum Command {
    START("start"),
    MOVE("move"),
    STATUS("status"),
    END("end");

    private final String description;

    Command(String description) {
        this.description = description;
    }

    public static Command from(String input) {
        String[] splittedInput = input.split(" ");

        return Arrays.stream(values())
                .filter(command -> command.description.equals(splittedInput[0]))
                .findFirst()
                .orElseThrow(InvalidCommandException::new);
    }

    public static Position[] positions(String command) {
        String[] splittedCommand = command.split(" ");

        Position[] positions = new Position[2];

        positions[0] = Position.from(splittedCommand[1]);
        positions[1] = Position.from(splittedCommand[2]);

        return positions;
    }
}
