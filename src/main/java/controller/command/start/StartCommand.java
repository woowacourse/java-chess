package controller.command.start;

import domain.ChessGame;
import gameinitializer.InitialChessAlignment;
import java.util.Map;

public final class StartCommand implements StartAction {

    private static final int START_COMMAND_PARAMETER_SIZE = 0;

    @Override
    public ChessGame init(final Map<Integer, String> parameters) {
        validateParameters(parameters);
        return ChessGame.initGame(new InitialChessAlignment());
    }

    private void validateParameters(final Map<Integer, String> parameters) {
        if (parameters.size() == START_COMMAND_PARAMETER_SIZE) {
            return;
        }
        throw new IllegalArgumentException("시작 커맨드의 파라미터는 없습니다.");
    }
}
