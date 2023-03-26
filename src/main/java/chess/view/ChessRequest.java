package chess.view;

import chess.controller.GameCommand;
import chess.domain.position.Position;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ChessRequest {
    private static final int MOVE_COMMAND_SIZE = 2;
    private static final int SOURCE_INDEX = 0;
    private static final int TARGET_INDEX = 1;
    private static final int POSITION_FILE = 0;
    private static final int POSITION_RANK = 1;

    private final GameCommand command;
    private final List<String> parameters;

    public ChessRequest(String command, List<String> parameters) {
        validateMoveCommand(command, parameters);
        this.command = parseToCommand(command);
        this.parameters = parameters;
    }

    private void validateMoveCommand(String command, List<String> parameters) {
        if ("move".equalsIgnoreCase(command)) {
            validateMoveCommandSize(parameters);
        }
    }

    private void validateMoveCommandSize(List<String> parameters) {
        if (parameters.size() != MOVE_COMMAND_SIZE) {
            throw new IllegalArgumentException("잘못된 명령어를 입력했습니다.");
        }
    }

    public GameCommand getCommand() {
        return command;
    }

    public Position getSource() {
        return createPosition(parameters.get(SOURCE_INDEX));
    }

    public Position getTarget() {
        return createPosition(parameters.get(TARGET_INDEX));
    }

    public GameCommand parseToCommand(String input) {
        if ("start".equalsIgnoreCase(input)) {
            return GameCommand.START;
        }
        if ("move".equalsIgnoreCase(input)) {
            return GameCommand.MOVE;
        }
        if ("end".equalsIgnoreCase(input)) {
            return GameCommand.END;
        }
        if ("status".equalsIgnoreCase(input)) {
            return GameCommand.STATUS;
        }
        if ("clear".equalsIgnoreCase(input)) {
            return GameCommand.CLEAR;
        }
        throw new IllegalArgumentException("잘못된 명령어를 입력했습니다.");
    }

    public Position createPosition(String input) {
        List<String> position = Arrays.stream(input.split(""))
                .collect(Collectors.toList());
        return parseToPosition(position);
    }

    private Position parseToPosition(List<String> position) {
        return new Position(FileCoordinateView.findBy(position.get(POSITION_FILE)),
                RankCoordinateView.findBy(position.get(POSITION_RANK)));
    }
}
