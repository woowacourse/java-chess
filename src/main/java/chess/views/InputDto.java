package chess.views;

import chess.domain.game.Command;
import chess.domain.position.Position;
import chess.domain.position.Positions;

public class InputDto {
    private static final String DELIMITER = " ";
    private static final int BASIC_INPUTS_SIZE = 1;
    private static final int MOVE_INPUTS_SIZE = 3;

    private static final int COMMAND_INDEX = 0;
    private static final int FROM_INDEX = 1;
    private static final int TO_INDEX = 2;

    private final Command command;
    private final String[] inputs;

    private Position from;
    private Position to;

    public InputDto(String input) {
        this.inputs = input.split(DELIMITER);
        this.command = Command.of(inputs[COMMAND_INDEX]);
        if (!validateSize()) {
            throw new IllegalArgumentException("잘못된 입력입니다. ");
        }
        if (command == Command.MOVE) {
            this.from = Positions.of(inputs[FROM_INDEX]);
            this.to = Positions.of(inputs[TO_INDEX]);
        }
    }

    public Command getCommand() {
        return command;
    }

    public Position getFrom() {
        return from;
    }

    public Position getTo() {
        return to;
    }

    private boolean validateSize() {
        if (command == Command.MOVE) {
            return inputs.length == MOVE_INPUTS_SIZE;
        }
        return inputs.length == BASIC_INPUTS_SIZE;
    }
}
