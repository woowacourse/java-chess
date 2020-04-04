package chess.controller.dto;

import chess.Exception.IllegalInputException;
import chess.domain.game.Command;
import chess.domain.position.Position;
import chess.domain.position.Positions;
import spark.Request;

public class RequestDto {
    private static final String DELIMITER = " ";
    private static final int BASIC_INPUTS_SIZE = 1;
    private static final int MOVE_INPUTS_SIZE = 3;
    private static final int COMMEND_INDEX = 0;
    private static final int FROM_INDEX = 1;
    private static final int TO_INDEX = 2;

    private final Command command;
    private String[] inputs;

    private Position from;
    private Position to;

    public RequestDto(String input) {
        this.inputs = input.split(DELIMITER);
        this.command = Command.of(inputs[COMMEND_INDEX]);

        if (!validateSize()) {
            throw new IllegalInputException("잘못된 입력입니다. ");
        }

        if (command == Command.MOVE) {
            this.from = Positions.of(inputs[FROM_INDEX]);
            this.to = Positions.of(inputs[TO_INDEX]);
        }
    }

    public RequestDto(Command command) {
        this.command = command;
    }

    public RequestDto(Command command, Request request) {
        this.command = command;
        if (command == Command.MOVE) {
            this.from = Positions.of(request.queryParams("from"));
            this.to = Positions.of(request.queryParams("to"));
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
