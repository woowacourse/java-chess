package command.start;

import domain.ChessGame;
import gameinitializer.InitialChessAlignment;
import java.util.List;

public final class StartCommand implements StartAction {

    private static final int START_COMMAND_PARAMETER_SIZE = 0;

    private final List<String> parameter;

    public StartCommand(final List<String> parameter) {
        validateParameter(parameter);
        this.parameter = parameter;
    }

    @Override
    public ChessGame init() {
        return ChessGame.initGame(new InitialChessAlignment());
    }

    private void validateParameter(final List<String> parameter) {
        if (parameter.size() == START_COMMAND_PARAMETER_SIZE) {
            return;
        }
        throw new IllegalArgumentException("시작 커맨드의 파라미터는 없습니다.");
    }
}
