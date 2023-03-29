package command.start;

import domain.ChessGame;
import java.util.List;

public final class EndCommand implements StartAction {
    private static final int END_COMMAND_PARAMETER_SIZE = 0;

    public EndCommand(final List<String> parameter) {
        validateParameter(parameter);
    }

    @Override
    public ChessGame init() {
        return null;
    }

    private void validateParameter(final List<String> parameter) {
        if (parameter.size() == END_COMMAND_PARAMETER_SIZE) {
            return;
        }
        throw new IllegalArgumentException("종료 커맨드의 파라미터는 없습니다.");
    }
}
