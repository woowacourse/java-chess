package controller.command.play;

import database.connection.ConnectionGenerator;
import domain.ChessGame;
import java.util.Map;

public final class EndCommand implements PlayAction {
    private static final int END_COMMAND_PARAMETER_SIZE = 0;

    @Override
    public boolean execute(final ChessGame chessGame, final Map<Integer, String> parameters,
                           final ConnectionGenerator connectionGenerator) {
        validateParameters(parameters);
        return false;
    }

    private void validateParameters(final Map<Integer, String> parameters) {
        if (parameters.size() == END_COMMAND_PARAMETER_SIZE) {
            return;
        }
        throw new IllegalArgumentException("종료 커맨드의 파라미터는 없습니다.");
    }
}
