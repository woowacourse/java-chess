package chess.dto;

import chess.domain.board.position.Position;
import chess.view.Command;

public class CommandDto {
    private static final String NOT_MOVE_ERROR_MESSAGE = "move 커멘드가 아닙니다";
    private static final String SPLIT_REGEX = " ";
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;
    private final Command command;
    private final String input;

    public CommandDto(String input) {
        this.input = input;
        this.command = Command.from(input);
    }

    public Position toSourcePosition() {
        if (command == Command.MOVE) {
            return Position.from(input.split(SPLIT_REGEX)[SOURCE_INDEX]);
        }
        throw new IllegalStateException(NOT_MOVE_ERROR_MESSAGE);
    }

    public Position toTargetPosition() {
        if (command == Command.MOVE) {
            return Position.from(input.split(SPLIT_REGEX)[TARGET_INDEX]);
        }
        throw new IllegalStateException(NOT_MOVE_ERROR_MESSAGE);
    }

    public Command getCommand() {
        return command;
    }
}
