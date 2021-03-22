package chess.controller.dto;

import static chess.domain.game.type.Command.MOVE;
import static chess.domain.game.type.Command.of;

import chess.domain.game.type.Command;
import chess.domain.position.MoveRoute;

public class CommandDTO {
    private static final String COMMAND_DELIMITER = " ";
    private static final int COMMAND_INDEX = 0;
    private static final int MOVE_COMMAND_FACTORS_SIZE = 3;
    private static final int START_POSITION_INDEX = 1;
    private static final int DESTINATION_INDEX = 2;

    private final Command command;
    private final MoveRoute moveRoute;

    public CommandDTO(String commandLineInput) {
        String[] splitCommandLine = commandLineInput.split(COMMAND_DELIMITER);
        String commandInput = splitCommandLine[COMMAND_INDEX];
        command = of(commandInput);
        if (command == MOVE) {
            moveRoute = parseMoveRoute(splitCommandLine);
            return;
        }
        moveRoute = null;
    }

    private MoveRoute parseMoveRoute(String[] splitCommandLine) {
        if (splitCommandLine.length != MOVE_COMMAND_FACTORS_SIZE) {
            throw new IllegalArgumentException("명령어를 잘못 입력했습니다.");
        }
        String startPositionInput = splitCommandLine[START_POSITION_INDEX];
        String destinationInput = splitCommandLine[DESTINATION_INDEX];
        return new MoveRoute(startPositionInput, destinationInput);
    }

    public Command command() {
        return command;
    }

    public MoveRoute moveRoute() {
        return moveRoute;
    }
}
