package command.play;

import domain.ChessGame;
import java.util.List;
import view.OutputView;

public final class StatusCommand implements PlayAction {
    private static final int STATUS_COMMAND_PARAMETER_SIZE = 0;
    private final List<String> parameter;

    public StatusCommand(final List<String> parameter) {
        validateParameter(parameter);
        this.parameter = parameter;
    }

    @Override
    public boolean execute(ChessGame chessGame) {
        OutputView.printGameScoreStatus(chessGame);
        return true;
    }

    private void validateParameter(final List<String> parameter) {
        if (parameter.size() == STATUS_COMMAND_PARAMETER_SIZE) {
            return;
        }
        throw new IllegalArgumentException("상태 커맨드의 파라미터는 없습니다.");
    }
}
