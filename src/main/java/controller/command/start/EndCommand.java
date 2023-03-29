package controller.command.start;

import database.connection.ConnectionGenerator;
import domain.ChessGame;
import java.util.Map;

public final class EndCommand implements StartAction {
    private static final int END_COMMAND_PARAMETER_SIZE = 0;

    @Override
    public ChessGame init(final Map<Integer, String> parameters, final ConnectionGenerator connectionGenerator) {
        validateParameter(parameters);
        return null;
    }

    private void validateParameter(final Map<Integer, String> parameters) {
        if (parameters.size() == END_COMMAND_PARAMETER_SIZE) {
            return;
        }
        throw new IllegalArgumentException("종료 커맨드의 파라미터는 없습니다.");
    }
}
