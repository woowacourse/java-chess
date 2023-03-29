package controller.command.play;

import database.connection.ConnectionGenerator;
import domain.ChessGame;
import java.util.Map;
import view.OutputView;

public final class StatusCommand implements PlayAction {
    private static final int STATUS_COMMAND_PARAMETER_SIZE = 0;

    @Override
    public boolean execute(final ChessGame chessGame, final Map<Integer, String> parameters,
                           final ConnectionGenerator connectionGenerator) {
        validateParameters(parameters);
        OutputView.printGameScoreStatus(chessGame);
        return true;
    }

    private void validateParameters(final Map<Integer, String> parameters) {
        if (parameters.size() == STATUS_COMMAND_PARAMETER_SIZE) {
            return;
        }
        throw new IllegalArgumentException("상태 커맨드의 파라미터는 없습니다.");
    }
}
