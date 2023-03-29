package command.play;

import domain.ChessGame;
import java.util.List;

public final class EndCommand implements PlayAction {
    private static final int END_COMMAND_PARAMETER_SIZE = 0;

    public EndCommand(final List<String> parameter) {
        validateParameter(parameter);
    }

    @Override
    public boolean execute(ChessGame chessGame) {
        return false;
    }

    private void validateParameter(final List<String> parameter) {
        if (parameter.size() == END_COMMAND_PARAMETER_SIZE) {
            return;
        }
        throw new IllegalArgumentException("종료 커맨드의 파라미터는 없습니다.");
    }
}
