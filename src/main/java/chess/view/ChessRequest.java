package chess.view;

import chess.controller.GameCommand;
import java.util.List;

public class ChessRequest {
    private static final int MOVE_COMMAND_SIZE = 2;

    private final GameCommand command;
    private final List<String> parameters;

    public ChessRequest(String command, List<String> parameters) {
        if ("move".equalsIgnoreCase(command)) {
            validateMoveCommandSize(parameters);
        }
        this.command = GameCommand.of(command);
        this.parameters = parameters;
    }

    private void validateMoveCommandSize(List<String> parameters) {
        if (parameters.size() != MOVE_COMMAND_SIZE) {
            throw new IllegalArgumentException("잘못된 명령어를 입력했습니다.");
        }
    }

    public GameCommand getCommand() {
        return command;
    }

    public List<String> getParameter() {
        return parameters;
    }
}
