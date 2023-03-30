package chess.dto;

import chess.controller.GameCommand;
import java.util.List;

public class ChessRequest {
    private static final int SOURCE_INDEX = 0;
    private static final int TARGET_INDEX = 1;

    private final GameCommand command;
    private final List<String> parameters;

    public ChessRequest(GameCommand command, List<String> parameters) {
        validateCommandParameter(command, parameters);
        this.command = command;
        this.parameters = parameters;
    }

    private void validateCommandParameter(GameCommand command, List<String> parameters) {
        if (command.getParameterLength() != parameters.size()) {
            throw new IllegalArgumentException("잘못된 명령어를 입력했습니다.");
        }
    }

    public GameCommand getCommand() {
        return command;
    }

    public String getSource() {
        return parameters.get(SOURCE_INDEX);
    }

    public String getTarget() {
        return parameters.get(TARGET_INDEX);
    }
}
